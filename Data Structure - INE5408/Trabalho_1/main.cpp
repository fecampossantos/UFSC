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
  std::string tag;
  std::string[] pilha = new string[256];

  //reading logic
  bool opening_tag = false, closing_tag = false;
  while (NAO CHEGA O FIM) {
    read_char = //reads char
    if (read_char == '<'){
      bool continue_reading = true;
      while(continue_reading) {

        if (proximo char = '/') {
          closing_tag = true;
          opening_tag = false;
        } else {
          opening_tag = true;
          closing_tag = false;
        }

        do {
          tag += read_char;
        } while (read_char != '>');

        // insert read tag on the stack

        continue_reading = false; //exits while, tag ended
      }

      //out of the while
      //compares last read string

      //if both the tags are equal, continue program
      //if not, shows error

    } else {
      //does nothing
    }
}
  std::cout << xmlfilename << std::endl;  // esta linha deve ser removida

  file.close();
  return 0;
}
