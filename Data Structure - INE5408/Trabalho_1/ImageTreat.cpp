#ifndef IMAGE_TREAT
#define IMAGE_TREAT

#include <string>
#include <iostream>
#include <stdexcept>

#include "linked_stack.h"
#include "ImageAnalyser.cpp"

using namespace std;

namespace file {

            /**
            *   Classe para receber as imagens e armazenar os arquivos png e sua quantidade
            **/
  class JoinImage {
      public:
	          /**
            *   Construtor vazio
            **/
          JoinImage() {}

	    /**
      *   Método set para o número de imagens
	    *
	    * @param int total
	    * 	número de imagens
            **/
          void setNumberImages(int total) {
              this->numberImages = total;
          }

	    /**
      *   Método set para a imagem
	    *
	    * @param structure::Image image[]
	    * 	define um array de imagens como o atributo imagem de JoinImage
            **/
          void setImage(structure::Image image[]) {
              this->image = image;
          }
	  
	          /**
            *   Método retorna a imagem de JoinImage
            **/
          structure::Image* getImage() {
              return this->image;
          }

	          /**
            *   Método retorna o número de imagens em JoinImage
            **/
          int getNumberImages() {
              return this->numberImages;
          }
      private:
          structure::Image* image;
          int numberImages;
        };
    
            /**
            *   Classe responsável pela validação dos datasets e contagem de sua quantidade
            *   de imagens através da análise das tags
            **/
    class Parser {
        public:
            /**
            *   Construtor vazio
            **/
            Parser() {}
            
            /**
            *   
            **/
            int countTags(std::string tag, std::string * content) {
              int count = 0;
              int position = content->find(tag, 0);  //  returns -1 if not found
              while(position >= 0) {
                  count++;
                  position = content->find(tag, position + 1);
              }
              return count;
            }

            /**
            *   Método que retorna a string de dados entre uma tag e seu fechamento </tag>
            **/
            std::string getBetweenTags(std::string tag,std::string * content) {

              int startIndex = content->find(tag) + tag.length();
              int endIndex = content->find(tag.insert(1, "/"));

              return content->substr(startIndex, endIndex - startIndex);
            }
            
            /**
            *   Método para analisar a imagem e receber seus dados principais
            **/
            structure::Image * serializeImage(std::string * buffer) {
                std::string tag = getBetweenTags("<img>", buffer);
                std::string name = getBetweenTags("<name>", &tag);
                std::string data = getBetweenTags("<data>", &tag);
                int width =  std::stoi(getBetweenTags("<width>", &tag));
                int height = std::stoi(getBetweenTags("<height>", &tag));
                return new structure::Image(name, width, height, data);
            }

            /**
            *   Método parse para validar um arquivo, e analisar seu conteúdo para retornar um 
            *   arquivo JoinImage com a quantidade de imagens e as próprias imagens
            **/
            file::JoinImage * parseFile(std::string * content) {
              //cout << "Starting parse process..." << endl;

              this->checkValid(content);

              int number_images = countTags("<img>", content);
              //cout<<"There are "<<number_images<<" images on the file."<<endl;

              structure::Image* images = new structure::Image[number_images];

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

            /**
            *   Método de validação de arquivos através da manipulação de tags em uma pilha (ArrayStack)
            **/
            bool checkValid(std::string * content) {
              //cout << "Checking if file is valid (if all open tags were closed) ..." << endl;

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
                                std::cout << "error" << std::endl;
                                exit(1);
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

              if (!stack.empty()) {
                //cout << "Valid file! Continuing..." << endl;
                return true;
              } else {
                //throw std::out_of_range("error");
                std::cout << "error" << std::endl;
                //printf("error");
                exit;
                //return 1;
                //return false;
              }
          }
    };
}

#endif
