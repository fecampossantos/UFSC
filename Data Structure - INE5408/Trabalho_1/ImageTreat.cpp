#ifndef IMAGE_TREAT
#define IMAGE_TREAT

#include <string>
#include <iostream>
#include <stdexcept>

#include "linked_stack.h"
#include "ImageAnalyser.cpp"

using namespace std;

namespace file {

  class JoinImage {
      public:

          JoinImage() {}


          void setNumberImages(int total) {
              this->numberImages = total;
          }


          void setImage(structure::Image image[]) {
              this->image = image;
          }

          structure::Image * getImage() {
              return this->image;
          }


          int getNumberImages() {
              return this->numberImages;
          }
      private:
          structure::Image * image;
          int numberImages;
        };

    class Parser {
        public:

            Parser() {}

            int countTags(std::string tag, std::string * content) {
              cout << "Counting how many " << tag << " are there in file content..." << endl;
              int count = 0;
              int position = content->find(tag, 0);  //  returns -1 if not found
              while(position >= 0) {
                  count++;
                  position = content->find(tag, position + 1);
              }
              return count;
            }


            std::string getBetweenTags(std::string tag,std::string * content) {
              cout << "Getting what is between the " << tag << " tags..." << endl;

              int startIndex = content->find(tag) + tag.length();
              int endIndex = content->find(tag.insert(1, "/"));

              return content->substr(startIndex, endIndex - startIndex);
            }

            structure::Image * serializeImage(std::string * buffer) {
                std::string tag = getBetweenTags("<img>", buffer);
                std::string name = getBetweenTags("<name>", &tag);
                std::string data = getBetweenTags("<data>", &tag);
                int width =  std::stoi(getBetweenTags("<width>", &tag));
                int height = std::stoi(getBetweenTags("<height>", &tag));
                cout << "Creating Image(name, width, height, data) with attributes ("<<name<<", "<<width<<", "<<height<<", data)"<<endl;
                return new structure::Image(name, width, height, data);
            }


            file::JoinImage * parseFile(std::string * content) {
              cout << "Starting parse process..." << endl;

              this->checkValid(content);

              cout << "Counting how many images are there in the file" << endl;
              int number_images = countTags("<img>", content);
              cout<<"There are "<<number_images<<" images on the file."<<endl;

              cout << "Creating array of images..." << endl;
              structure::Image * images = new structure::Image[number_images];

              int i = 0;
              std::string aux = *content;

              int position = content->find("<img>", 0);  //  returns -1 if not found
              while(position >= 0) {
                  aux = content->substr(position, content->length() - position);
                  images[i] = *serializeImage(&aux);
                  position = content->find("<img>", position + 1);
                  i++;
              }
              //cout<<"Exited while"<<endl;   //LINE FOR TEST, REMOVE!

              file::JoinImage * finalImage = new file::JoinImage();
              finalImage->setImage(images);
              finalImage->setNumberImages(number_images);
              return finalImage;
            }


            bool checkValid(std::string * content) {
              cout << "Checking if file is valid (if all open tags were closed) ..." << endl;

              structures::LinkedStack<std::string> stack;

              bool open = false;
              std::string aux;
              for (int i = 0; i < content->length(); i++) {
                  char read = content->at(i);
                  if (read == *"<") {
                      open = true;
                      continue;
                  }

                  if (read == *">") {
                      open = false;

                      // closing tag
                      if (aux.at(0) == *"/") {
                          std::string last_tag = stack.pop();

                          if (last_tag.compare(aux.substr(1, aux.length() - 1)) != 0) {
                              throw std::invalid_argument("Invalid file! Some tag is missing.");
                          }

                      }
                      if (aux.at(0) != *"/") {
                          stack.push(aux);
                      }
                      aux = "";
                  }

                  if (open == true) {
                      aux = aux.append(content->substr(i, 1));
                  }
              }

              if (stack.empty()) {
                cout << "Valid file! Continuing..." << endl;
                return true;
              } else {
                return false;
              }
          }
    };
}

#endif
