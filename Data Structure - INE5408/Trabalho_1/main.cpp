#include <iostream>
#include <ifstream>

using namespace std;

int main() {
  char xmlfilename[100];

  std::cin >> xmlfilename;  // entrada

  /*COLOQUE SEU CODIGO AQUI*/
  //open file

  //start reading
  std::string tag_atual;   // <- include
  bool tag_abre = false, tag_fecha = false;
  while (NAO CHEGA O FIM) {
    if (char_lido == '<'){
      bool sai = false;
      while(!sai) {

        if (proximo char = `/`) {
          tag_fecha = true;
          tag_abre = false;
        } else {
          tag_abre = true;
          tag_fecha = false;
        }

        do {
          tag_atual->push(char_lido);
        } while (char_lido != '>');

        // insere tag_atual na pilha

        sai = true; //sai do while, pois acabou a tag
      }

      //saiu do while
      //compara com a string anterior da pilha (compara retirando o /)

      //se for igual, continua
      //se nao for igual, acusa erro

    } else {
      //volta a ler
    }
}
  std::cout << xmlfilename << std::endl;  // esta linha deve ser removida

  return 0;
}
