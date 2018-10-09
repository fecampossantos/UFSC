#ifndef IMAGE_ANALYSER
#define IMAGE_ANALYSER

#include "Location.cpp"
#include "linked_queue.h"
#include <string>
#include <iostream>

using namespace std;

namespace structure {

  /**
  *   classe que cria e altera imagens
  **/
  class Image {
      public:

        /**
        *   construtor vazio
        **/
          Image() {}


          /**
          *   construtor com parametros
          *
          *   @param string name, int width, int height, string data
          *         nome da imagem, largura, altura e o conteudo
          *
          *   @return Image
          *       retorna imagem
          **/
          Image(std::string name, int width, int height, std::string data) {
              this->name = name;
              this->width = width;
              this->height = height;
              this->data = data;
          }


          /**
          *   metodo que retorna o nome da imagem
          *
          *   @return string
          *       retorna name
          **/
          std::string getName() {
            return this->name;
          }

          /**
          *   metodo que retorna a largura da imagem
          *
          *   @return int
          *       retorna width
          **/
          int getWidth() {
              return this->width;
          }

          /**
          *   metodo que retorna a altura da imagem
          *
          *   @return int
          *       retorna height
          **/
          int getHeight() {
              return this->height;
          }

          /**
          *   metodo que retorna o conteudo da imagem
          *
          *   @return string
          *       retorna data
          **/
          std::string getData() {
              return this->data;
          }
      private:
          std::string name;
          int width;
          int height;
          std::string data;
  };


  /**
  *  classe com metodos que analisam o conteudo da imagem
  **/
    class Analyser {
        public:

            /**
            *   construtor vazio
            **/
            Analyser() {}

            /**
            *   construtor com parametros
            *
            *   @param Image* image
            *       recebe um ponteiro para objeto imagem
            **/
            void setImage(structure::Image * image) {
                this->width1 = image->getWidth();
                this->height1 = image->getHeight();
                this->data = image->getData();
                this->rotulo = 1;
            }

            /**
            *   metodo que altera a matriz R a partir da linha seguinte
            *
            *   @param Location*, int*, int*
            *       recebe um ponteiro para objeto location, um Ponteiro
            *       para a matriz E e um ponteiro para linha
            **/
            void lineAfter(coord::Location * aux, int * clone, int * auxLinha) {
                    int line = aux->getX() + 1;
                    if (line < this->height1) {
                        int pos = *auxLinha + this->width1 + aux->getY();
                        this->editMatrixE(clone, line, aux->getY(), pos);
                    }
            }


            /**
            *   metodo que altera a matriz R a partir da linha anterior
            *
            *   @param Location*, int*, int*
            *       recebe um ponteiro para objeto location, um Ponteiro
            *       para a matriz E e um ponteiro para linha
            **/
            void lineBefore(coord::Location * aux, int * clone, int * auxLinha) {
                    int novaLinha = aux->getX() - 1;
                    if (novaLinha > -1) {
                        int pos = *auxLinha - this->width1 + aux->getY();
                        this->editMatrixE(clone, novaLinha, aux->getY(), pos);
                    }
            }


            /**
            *   metodo que altera a matriz R a partir da coluna seguinte
            *
            *   @param Location*, int*, int*
            *       recebe um ponteiro para objeto location, um Ponteiro
            *       para a matriz E e um ponteiro para linha
            **/
            void columnAfter(coord::Location * aux, int * clone, int * auxLinha) {
                    int col = aux->getY() + 1;
                    if (col < this->width1) {
                        int pos = *auxLinha + col;
                        this->editMatrixE(clone, aux->getX(), col, pos);
                    }
            }


            /**
            *   metodo que altera a matriz R a partir da coluna anterior
            *
            *   @param Location*, int*, int*
            *       recebe um ponteiro para objeto location, um Ponteiro
            *       para a matriz E e um ponteiro para linha
            **/
            void columnBefore(coord::Location * aux, int * clone, int * auxLinha) {
                    int col = aux->getY() - 1;
                    if (col > -1) {
                        int pos = *auxLinha + col;
                        this->editMatrixE(clone, aux->getX(), col, pos);
                    }
            }


            /**
            *   metodo que altera a matriz R com base na matriz E lida
            *
            *   @param int*, int, int, int
            *       recebe um ponteiro e as posicoes
            **/
            void editMatrixE(int * clone, int i, int j, int pos) {
                if (this->data.at(pos) == *"1" && clone[pos] == 0) {
                    this->queue.enqueue(*(new coord::Location(i, j)));
                    clone[pos] = this->rotulo;
                }
            }

            /**
            *   metodo que verifica os vizinhos
            *
            *   @param int*
            *       recebe um ponteiro para a matriz R
            **/
            void checkNeighbors(int * clone) {
                while(!this->queue.empty()) {
                    coord::Location aux = this->queue.dequeue();
                    int auxLinha = aux.getX() * this->width1;

                    this->lineAfter(&aux, clone, &auxLinha);
                    this->lineBefore(&aux, clone, &auxLinha);
                    this->columnAfter(&aux, clone, &auxLinha);
                    this->columnBefore(&aux, clone, &auxLinha);

                }
            }

            /**
            *   metodo que checa se o "pixel" é branco ou preto (1 ou 0)
            *
            *   @param int*, int, int, int
            *       recebe um objeto location
            **/
            void checkPixel(int * clone, int coordenadaAtual, int i, int j) {
                if (data.at(coordenadaAtual) == *"1") {
                    if (clone[coordenadaAtual] == 0) {
                        this->queue.enqueue(*(new coord::Location(i, j)));
                        clone[coordenadaAtual] = this->rotulo;
                        checkNeighbors(clone);
                        this->rotulo++;
                    }
                }
            }

            /*
            *   metodo que conta quantos elementos conexos existem
            *
            *   @return int
            *       retorna o numero de elementos conexos
            */
            int amountConected() {
                int * clone = new int[this->width1 * this->height1]{};

                int coordenadaAtual = 0;
                for (int i = 0; i < this->height1; i++) {

                    for (int j = 0; j < this->width1; j++) {
                        coordenadaAtual = (i * this->width1) + j;
                        checkPixel(clone, coordenadaAtual, i, j);
                    }
                }

                delete [] clone;

                return this->rotulo - 1;
            }

        private:
            structures::LinkedQueue<coord::Location> queue;
            int width1;
            int height1;
            int rotulo;
            std::string data;
    };
}

#endif
