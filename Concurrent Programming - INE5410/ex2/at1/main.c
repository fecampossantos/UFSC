#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>


//       (pai)
//         |
//    +----+----+
//    |         |
// filho_1   filho_2


// ~~~ printfs  ~~~
// pai (ao criar filho): "Processo pai criou %d\n"
//    pai (ao terminar): "Processo pai finalizado!\n"
//  filhos (ao iniciar): "Processo filho %d criado\n"

// Obs:
// - pai deve esperar pelos filhos antes de terminar!


int main(int argc, char** argv) {

    int pid1 = fork();  //criado filho 1
    printf("Processo pai criou %d\n", pid1);


    if (pid1 > 0) { //processo pai
      int status1;
      wait(&status1);


      //cria outro filho
      int pid2 = fork();  //criado filho 2
      printf("Processo pai criou %d\n", pid2);

      if (pid2 > 0) { //processo pai
        int status2;
        wait(&status2);

        printf("Processo pai finalizado!");

      } else if (pid2 == 0) { //processo filho
        printf("Processo filho %d criado\n", getpid());

      } else { //erro na criacao do filho
        printf("Erro %d na criacao do processo filho\n",pid2);
        return 0;
      }

    } else if (pid1 == 0) { //processo filho
      printf("Processo filho %d criado\n", getpid());

    } else { //erro na criacao de processo filho
      printf("Erro %d na criacao do processo filho\n",pid1);
      return 0;
    }

    return 0;
}
