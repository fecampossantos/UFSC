#include <iostream>
#include <ifstream>
#include <string>

using namespace std;

int main() {
  char xmlfilename[100];

  std::cin >> xmlfilename;  // entrada

  /*COLOQUE SEU CODIGO AQUI*/
  //  open file
  ifstream file;
  file.open("xmlfilename");

  //  start reading
  std::string tag;
  std::string[] stack = new string[256];

  //  reading logic
  bool opening_tag = false, closing_tag = false;
  for (int i = 0; i < file.size(); i++) { //stack iterator
    read_char = //reads char
    if (read_char == '<') {

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

        /* If the last tag read is a closing tag, the tag befor should be its opening tag.
            CASE 1: It is the opening tag: both tag should be removed from the stack.
            CASE 2: It it not the opening tag-> ACUSE ERROR
          If it is a opening tag, it should just be pushed to the stack */

        if (opening_tag) {
          /*just insert into the stack */
          stack[i] = tag;
        } else if (closing_tag) {
          string last_tag = tags[i-1];
          if ('/'+last_tag = tag) {
            /* The closing tag corresponds to the last opening tag,
            so both of them should be removed. */
          } else {
            /* The closing tag doesn't corresponds to the last opening
            tag, so there is an error! */
          }
        }

        continue_reading = false; //exits while, tag ended

      } //  end while

      //  out of the while
      //  compares last read string

      //  if both the tags are equal, continue program
      //  if not, shows error

    } else {
      //  does nothing
    }
}
  std::cout << xmlfilename << std::endl;  // esta linha deve ser removida

  file.close();
  return 0;
}
