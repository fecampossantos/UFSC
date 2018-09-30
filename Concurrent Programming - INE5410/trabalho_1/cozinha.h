#ifndef _COZINHA_H_
#define _COZINHA_H_

#include "pedido.h"
#include "tarefas.h"
#include <semaphore.h>

sem_t sem_cozinheiros;
sem_t sem_bocas;
sem_t sem_frigideiras;
sem_t sem_garcons;

/**/
extern void cozinha_init(int cozinheiros, int bocas, int frigideiras, int garcons, int tam_balcao);
extern void cozinha_destroy();
extern void* processar_pedido(void* p);
void preparar_sopa(pedido_t p);
void preparar_carne(pedido_t p);
void preparar_spaghetti(pedido_t p);

void cozinha_init(int cozinheiros, int bocas, int frigideiras, int garcons, int tam_balcao){
  //Cria os buffers
  sem_init(&sem_cozinheiros, 0, cozinheiros);
  sem_init(&sem_bocas, 0, bocas);
  sem_init(&sem_frigideiras, 0, frigideiras);
  sem_init(&sem_garcons, 0, garcons);

}

void* processar_pedido(void* p){
  /**/
  pedido_t pp = *((pedido_t*)p);
  const char* name = pedido_prato_to_name(pp.prato);
  if (strcmp(name,"SOPA") == 0){
    preparar_sopa(pp);
  }
  if (strcmp(name, "CARNE") == 0){
    preparar_carne(pp);

  }
  if (strcmp(name, "SPAGHETTI") == 0){
    preparar_spaghetti(pp);
  }
  return NULL;
}

void cozinha_destroy(){
  //Free buffers, delete threads and destroy semaphores
  sem_destroy(&sem_cozinheiros);
  sem_destroy(&sem_bocas);
  sem_destroy(&sem_frigideiras);
  sem_destroy(&sem_garcons);

}

void preparar_carne(pedido_t p){
  /**/
  carne_t* carne = create_carne();

  sem_wait(&sem_cozinheiros);                 //espera por um cozinheiro livre

  cortar_carne(carne);    //coloca cozinheiro como ocupado (DE)

  temperar_carne(carne);  //coloca cozinheiro como ocupado (DE)

  sem_wait(&sem_bocas);
  sem_wait(&sem_frigideiras);
  grelhar_carne(carne);         //coloca cozinheiro como ocupado (DE)
  sem_post(&sem_frigideiras);     //NESSE MOMENTO, TOMAR CUIDADO COM DEADLOCK
  sem_post(&sem_bocas);


  prato_t* prato = create_prato(p);
  empratar_carne(carne, prato);      //coloca cozinheiro como ocupado (DE)
  notificar_prato_no_balcao(prato);

  //inserir prato no buffer do balcao

  sem_post(&sem_cozinheiros);

  sem_wait(&sem_garcons);
  entregar_pedido(prato);
  sem_post(&sem_garcons);

}

void preparar_spaghetti(pedido_t p){
  /**/
  molho_t* molho = create_molho();
  agua_t* agua = create_agua();
  spaghetti_t* spaghetti = create_spaghetti();
  bacon_t* bacon = create_bacon();

  sem_wait(&sem_cozinheiros);

  sem_wait(&sem_bocas);
  esquentar_molho(molho);  //cozinheiro esperando
  sem_post(&sem_bocas);

  sem_wait(&sem_bocas);
  ferver_agua(agua);      //cozinheiro esperando
  sem_post(&sem_bocas);

  sem_wait(&sem_bocas);//inserir prato no buffer do balcao
  cozinhar_spaghetti(spaghetti, agua);      //cozinheiro esperando
  sem_post(&sem_bocas);

  sem_wait(&sem_bocas);
  dourar_bacon(bacon);              //
  sem_post(&sem_bocas);


  prato_t* prato = create_prato(p);
  empratar_spaghetti(spaghetti, molho, bacon, prato);   //cozinheiro ocupado

  //inserir prato no buffer do balcao
  notificar_prato_no_balcao(prato);

  sem_post(&sem_cozinheiros);

  //espera um garcom ficar livre
  sem_wait(&sem_garcons);
  entregar_pedido(prato);
  sem_post(&sem_garcons);
}

void preparar_sopa(pedido_t p){
  /**/
  agua_t* agua = create_agua();
  legumes_t* legumes = create_legumes();

  sem_wait(&sem_cozinheiros);

  sem_wait(&sem_bocas);
  ferver_agua(agua);   //cozinheiro esperando
  sem_post(&sem_bocas);

  cortar_legumes(legumes);  //cozinheiro ocupado

  sem_wait(&sem_bocas);
  caldo_t* caldo = preparar_caldo(agua);  //cozinheiro esperando
  sem_post(&sem_bocas);

  sem_wait(&sem_bocas);
  cozinhar_legumes(legumes, caldo);    //cozinheiro esperando
  sem_post(&sem_bocas);

  prato_t* prato = create_prato(p);
  notificar_prato_no_balcao(prato);
  //inserir prato no buffer do balcao

  sem_post(&sem_cozinheiros);


  sem_wait(&sem_garcons);
  entregar_pedido(prato);
  sem_post(&sem_garcons);
}



#endif /* _COZINHA_H_ */
