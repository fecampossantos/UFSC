#ifndef __COZINHA_H__
#define __COZINHA_H__

#include "pedido.h"
#include "tarefas.h"

extern void cozinha_init(int cozinheiros, int bocas, int frigideiras, int garcons, int tam_balcao);
extern void cozinha_destroy();
extern void processar_pedido(pedido_t p);

void *cozinha_init(){
  /**/

}

void *processar_pedido(p){
  /**/
  char* name = pedido_prato_to_name(p.prato);
  switch(name){
    case "PEDIDO_SOPA":
      cozinhar_sopa();
      break;
    case "PEDIDO_CARNE":
      cozinhar_carne();
      break;
    case "PEDIDO_SPAGHETTI":
      cozinhar_spaghetti();
      break;
  }

}

void *cozinha_destroy(){
  /*Free buffers, delete threads and destroy semaphores*/

}

void* cozinhar_carne(){
  /**/
  carne_t carne = create_carne();
  cortar_carne(carne);
  temperar_carne(carne);
  grelhar_carne(carne);

  prato_t prato = create_prato();
  empratar_carne(carne, prato);

  notificar_prato_no_balcao(prato);
  //inserir prato no buffer do balcao

  //espera um garcom ficar livre e pegar o prato
  entregar_pedido(prato);
  destroy_carne(carne);
  //destroy_prato(prato);

}

void* cozinhar_spaghetti(){
  /**/
  molho_t molho = create_molho();
  agua_t agua = create_agua();
  spaghetti_t spaghetti = create_spaghetti();
  bacon_t bacon = create_bacon();
  prato_t prato = create_prato();

  esquentar_molho(molho);
  ferver_agua(agua);
  cozinhar_spaghetti(spaghetti);
  dourar_bacon(bacon);
  empratar_spaghetti(spaghetti, molho, bacon, prato);

  notificar_prato_no_balcao(prato);
  //inserir prato no buffer do balcao

  //espera um garcom ficar livre
  entregar_pedido(prato);

  destroy_agua(agua);
  //destroy_prato(prato);
  destroy_molho(molho);
  destroy_bacon(bacon);
  destroy_spaghetti(spaghetti);
}

void* cozinhar_sopa(){
  /**/
  agua_t agua = create_agua();
  legumes_t legumes = create_legumes();

  ferver_agua(agua);
  cortar_legumes(legumes);
  preparar_caldo(agua);
  cozinhar_legumes(legumes);

  notificar_prato_no_balcao(prato);
  //inserir prato no buffer do balcao

  //espera um garcom ficar livre
  entregar_pedido(prato);

  //destroy_agua(agua);
  //destroy_prato(prato);
  destroy_legumes(legumes);
  destroy_caldo(caldo);
}



#endif /*__COZINHA_H__*/
