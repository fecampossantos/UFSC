#include <iostream>
#include <ifstream>
#include <string>

using namespace std;

int main() {
  char xmlfilename[100];

  std::cin >> xmlfilename;  // entrada

  /*COLOQUE SEU CODIGO AQUI*/
  //open file
  ifstream file;
  file.open("xmlfilename");

  //start reading
  std::string tag_atual;
  std::string[] pilha = new string[256];

  //reading logic
  bool opening_tag = false, closing_tag = false;
  while (NAO CHEGA O FIM) {
    if (char_lido == '<'){
      bool continue_reading = true;
      while(continue_reading) {

        if (proximo char = '/') {
          tag_fecha = true;
          tag_abre = false;
        } else {
          tag_abre = true;
          tag_fecha = false;
        }

        do {
          tag_atual += char_lido;
        } while (char_lido != '>');

        // insert read tag on the stack

        continue_reading = false; //sai do while, pois acabou a tag
      }

      //out of the while
      //compares last read string

      //if both the tags are equal, continue program
      //if not, shows error

    } else {
      //volta a ler
    }
}
  std::cout << xmlfilename << std::endl;  // esta linha deve ser removida

  file.close();
  return 0;
}
