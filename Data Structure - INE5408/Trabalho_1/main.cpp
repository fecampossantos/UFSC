#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

/*This method, when called, opens the file and gets the information
on it, turning into a string and returning that.*/
string fileToString(string xmlfilename){
  string content;
  char c;

  ifstream file;
  file.open(*xmlfilename);

  if (file.is_open()) {
    while (file.get(c)) {
      content += c;
    }
  } else {
    cout << "Error opening file";
    exit(1);
  }

  file.close();
  return content;
}



int main() {
  char xmlfilename[100];

  std::cin >> xmlfilename;  // entrada

  /*COLOQUE SEU CODIGO AQUI*/
  //  open file
  ifstream file;
  file.open(*xmlfilename);
  char read_char;
  if (file.is_open()) {
    //  start reading
    std::string tag;
    std::vector<string>* stack = new vector<string>[50]; //should we use malloc?

    //  reading logic
    bool opening_tag = false, closing_tag = false;
    while (file.get(read_char)) { //stack iterator
      if (read_char == '<') {

        bool continue_reading = true;
        while(continue_reading) {

          if (read_char == '/') {
            closing_tag = true;
            opening_tag = false;
          } else {
            opening_tag = true;
            closing_tag = false;
          }

           while (read_char != '>') {
            tag += read_char;
          }

          /* If the last tag read is a closing tag, the tag before should be its opening tag.
              CASE 1: It is the opening tag: both tag should be removed from the stack.
              CASE 2: It it not the opening tag-> ACUSE ERROR
            If it is a opening tag, it should just be pushed to the stack */

          if (opening_tag) {
            /*just insert into the stack */
            stack->insert(tag);
          } else if (closing_tag) {
            string last_tag = stack->back();
            if ('/'+last_tag == tag) {
              /* The closing tag corresponds to the last opening tag,
              so both of them should be removed. */
            } else {
              /* The closing tag doesn't corresponds to the last opening
              tag, so there is an error! The program exits */
              printf("No opening tag found for %s", tag);
              exit(2);
            }
          }

          continue_reading = false; //exits while, tag ended

        } //  end while

        //  out of the while
        //  compares last read string

        //  if both the tags are equal, continue program
        //  if not, shows error

      } else {
        /*If the read char is not a '<', keep reading*/
      }
    }
  } else {
    printf("Error opening the file");
  }

  //std::cout << xmlfilename << std::endl;  // esta linha deve ser removida

  file.close();
  stack->clear();
  return 0;
}
