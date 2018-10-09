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
  *   classe que junta uma imagem com o numero de imagens do arquivo
  **/
  class JoinImage {
      public:
        /**
        *   construtor vazio
        **/
          JoinImage() {}

          /**
          *   metodo que altera o numero de imagens
          *
          *   @param int
          *     int com o total de imagens
          **/
          void setNumberImages(int total) {
              this->numberImages = total;
          }

          /**
          *   metodo que altera a imagen
          *
          *   @param Image
          *     objeto Image
          **/
          void setImage(structure::Image image[]) {
              this->image = image;
          }

          /**
          *   metodo que retorna a imagem
          *
          *   @return Image*
          *     retorna um ponteiro para a imagem
          **/
          structure::Image* getImage() {
              return this->image;
          }

          /**
          *   metodo que retorna o numero de imagens
          *
          *   @return int
          *     retorna uint com o numero de imagens
          **/
          int getNumberImages() {
              return this->numberImages;
          }
      private:
          structure::Image* image;
          int numberImages;
        };


      /**
      *   classe que faz a leitura e avaliacao do arquivo
      **/
    class Parser {
        public:

            /**
            *   construtor vazio
            **/
            Parser() {}

            /**
            *   metodo que conta quantas especificas tags tem no arquivo
            *
            *   @param string tag, string* content
            *     recebe uma tag (no formato "<xxx>") e um ponteiro para o conteudo
            *
            *   @return int
            *       retorna o numero de tags
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
            *   metodo que coleta o que existe entre tags especificas
            *
            *   @param string, string*
            *       recebe a tag (no formato "<xxx>") e um ponteiro para o conteudo do arquivo
            *
            *   @return string
            *     retorna uma string com o conteudo da tag
            **/
            std::string getBetweenTags(std::string tag,std::string * content) {

              int startIndex = content->find(tag) + tag.length();
              int endIndex = content->find(tag.insert(1, "/"));

              return content->substr(startIndex, endIndex - startIndex);
            }

            /**
            *   metodo que coleta toda informacao da imagem
            *
            *   @param string*
            *       recebe um ponteiro para o conteudo

            *   @return Image*
            *     retorna um ponteiro para a imagem ja com as informacoes
            **/
            structure::Image * serializeImage(std::string * content) {
                std::string tag = getBetweenTags("<img>", content);
                std::string name = getBetweenTags("<name>", &tag);
                std::string data = getBetweenTags("<data>", &tag);
                int width =  std::stoi(getBetweenTags("<width>", &tag));
                int height = std::stoi(getBetweenTags("<height>", &tag));
                return new structure::Image(name, width, height, data);
            }

            /**
            *   metodo que faz o parse (pedido no exercicio)
            *
            *   @param string*
            *      recebe um ponteiro para o conteudo da tag
            *
            *   @return JoinImage*
            *     retorna um ponteiro para um objeto JoinImage, ja com a image e o numero total de imagens
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
            *   metodo que faz a verificacao da abertura e fechamento das tags
            *
            *   @param string*
            *       recebe um ponteiro para o conteudo do arquivo
            *
            *   @return bool
            *     retorna TRUE se o arquivo for valido, FALSE caso contrario
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
                std::cout << "error" << std::endl;
              }
          }
    };
}

#endif
