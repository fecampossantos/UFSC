#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <errno.h>
#include <string.h>
#include "pedido.h"
#include "cozinha.h"
#include <pthread.h>

static struct option cmd_opts[] = {
    {"cozinheiros", required_argument, 0, 'c'},
    {"bocas",       required_argument, 0, 'b'},
    {"fogoes",      required_argument, 0, 'f'},
    {"frigideiras", required_argument, 0, 'r'},
    {"garcons",     required_argument, 0, 'g'},
    {"balcao",      required_argument, 0, 'a'},
    {"help",        no_argument,       0, 'h'},
    {0, 0, 0, 0}
};

int parse_gt_zero(const char* buf, const char* name, int* res) {
    errno = 0;
    *res = strtol(buf, NULL, 10);
    if (errno) {
        fprintf(stderr, "Impossível parsear argumento da opção %s, \"%s\" como "
                "um inteiro: %s\n", name, buf, strerror(errno));
        return 0;
    }
    if (*res <= 0) {
        fprintf(stderr, "Esperava um valor maior que zero para %s, leu: %d\n",
                name, *res);
        return 0;
    }
    return 1;
}
void check_missing(int value, const char* name) {
    if (value > 0) return;
    fprintf(stderr, "%s faltando!\n", name);
    abort();
}

int main(int argc, char** argv) {
    int bocas_total = 0, bocas = 4, frigideiras = 2, fogoes = 2,
        cozinheiros = 6, garcons = 1, balcao = 5, c = 0;
    while (c >= 0) {
        int long_idx;
        c = getopt_long(argc, argv, "cbfrga", cmd_opts, &long_idx);
        if (c == 0) c = cmd_opts[long_idx].val;

        switch (c) {
        case 'c':
            if (!parse_gt_zero(optarg, argv[optind-1], &cozinheiros)) abort();
            break;
        case 'b':
            if (!parse_gt_zero(optarg, argv[optind-1], &bocas      )) abort();
            break;
        case 'f':
            if (!parse_gt_zero(optarg, argv[optind-1], &fogoes     )) abort();
            break;
        case 'r':
            if (!parse_gt_zero(optarg, argv[optind-1], &frigideiras)) abort();
            break;
        case 'g':
            if (!parse_gt_zero(optarg, argv[optind-1], &garcons    )) abort();
            break;
        case 'a':
            if (!parse_gt_zero(optarg, argv[optind-1], &balcao     )) abort();
            break;
        case -1:
            break;
        default:
            abort();
        }
    }

    bocas_total = bocas*fogoes;
    check_missing(cozinheiros, "cozinheiros");
    check_missing(bocas, "bocas");
    check_missing(fogoes, "fogoes");
    check_missing(frigideiras, "frigideiras");
    check_missing(garcons, "garcons");
    check_missing(balcao, "balcao");

    cozinha_init(cozinheiros, bocas_total, frigideiras,
                 garcons, balcao);

    char* buf = (char*)malloc(4096);
    int next_id = 1;
    int ret = 0;
    while((ret = scanf("%4095s", buf)) > 0) {
        index++;
        pedido_t p = {next_id++, pedido_prato_from_name(buf)};
        if (!p.prato)
            fprintf(stderr, "Pedido inválido descartado: \"%s\"\n", buf);
        else {
          /*Criada uma variavel local na memoria para poder passar o
          valor sem que ele se perca*/
          pedido_t* p2 = malloc(sizeof(pedido_t));
          *p2 = p;
          pthread_t thread;
          pthread_create(&thread, NULL, processar_pedido, p2);
        }
            //processar_pedido(p);
    }
    if (ret != EOF) {
        perror("Erro lendo pedidos de stdin:");
    }
    //aqui fazer algo para impedir que o programa acabe antes
    //de terminar os pedidos

    //linked list de semaforos
    //pthread detach***
    free(buf);

    cozinha_destroy();

    return 0;

    /*
      Garantir que, após o fim do main():
        Não haja nenhuma outra thread em execução;
        Não exista nenhum pedido em um estágio intermediário de produção ou já produzido mas não entregue;
        Não podem haver memory leaks (use o AddressSanitizer para confirmar).
    */
}
