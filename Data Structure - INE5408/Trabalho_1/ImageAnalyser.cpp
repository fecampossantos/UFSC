#ifndef IMAGE_ANALYSER
#define IMAGE_ANALYSER

#include "Location.cpp"
#include "linked_queue.h"
#include <string>
#include <iostream>

using namespace std;

namespace structure {

  class Image {
      public:

          Image() {}


          Image(std::string name, int width, int height, std::string data) {
              this->name = name;
              this->width = width;
              this->height = height;
              this->data = data;
          }


          std::string getName() {
            return this->name;
          }


          int getWidth() {
              return this->width;
          }


          int getHeight() {
              return this->height;
          }


          std::string getData() {
              return this->data;
          }
      private:
          std::string name;
          int width;
          int height;
          std::string data;
  };



    class Analyser {
        public:

            Analyser() {}


            void setImage(structure::Image * image) {
                this->largura = image->getWidth();
                this->altura = image->getHeight();
                this->data = image->getData();
                this->qtd = 1;
            }


            void nextLine(coord::Location * aux, int * clone, int * auxLinha) {
                    int line = aux->getX() + 1;
                    if (line < this->altura) {
                        int pos = *auxLinha + this->largura + aux->getY();
                        this->alteraClone(clone, line, aux->getY(), pos);
                    }
            }


            void beforeLine(coord::Location * aux, int * clone, int * auxLinha) {
                    int novaLinha = aux->getX() - 1;
                    if (novaLinha > -1) {
                        int pos = *auxLinha - this->largura + aux->getY();
                        this->alteraClone(clone, novaLinha, aux->getY(), pos);
                    }
            }


            void nextColumn(coord::Location * aux, int * clone, int * auxLinha) {
                    int col = aux->getY() + 1;
                    if (col < this->largura) {
                        int pos = *auxLinha + col;
                        this->alteraClone(clone, aux->getX(), col, pos);
                    }
            }


            void previousColumn(coord::Location * aux, int * clone, int * auxLinha) {
                    int col = aux->getY() - 1;
                    if (col > -1) {
                        int pos = *auxLinha + col;
                        this->alteraClone(clone, aux->getX(), col, pos);
                    }
            }


            void alteraClone(int * clone, int i, int j, int pos) {
                if (this->data.at(pos) == *"1" && clone[pos] == 0) {
                    this->fila.enqueue(*(new coord::Location(i, j)));
                    clone[pos] = this->qtd;
                }
            }

            void verificaArredores(int * clone) {
                while(!this->fila.empty()) {
                    coord::Location aux = this->fila.dequeue();
                    int auxLinha = aux.getX() * this->largura;

                    // Linha de cima/baixo
                    this->nextLine(&aux, clone, &auxLinha);
                    this->beforeLine(&aux, clone, &auxLinha);

                    // Coluna da frente/trás
                    this->nextColumn(&aux, clone, &auxLinha);
                    this->previousColumn(&aux, clone, &auxLinha);

                }
            }

            void verificaSePixelPreto(int * clone, int coordenadaAtual, int i, int j) {
                if (data.at(coordenadaAtual) == *"1") {
                    if (clone[coordenadaAtual] == 0) {
                        this->fila.enqueue(*(new coord::Location(i, j)));
                        clone[coordenadaAtual] = this->qtd;
                        verificaArredores(clone);
                        this->qtd++;
                    }
                }
            }


            int qtdFormaConvexa() {
                int * clone = new int[this->largura * this->altura]{};

                int coordenadaAtual = 0;
                for (int i = 0; i < this->altura; i++) {

                    for (int j = 0; j < this->largura; j++) {
                        coordenadaAtual = (i * this->largura) + j;
                        verificaSePixelPreto(clone, coordenadaAtual, i, j);
                    }
                }

                delete [] clone;

                return this->qtd - 1;
            }

        private:
            structures::LinkedQueue<coord::Location> fila;
            int largura;
            int altura;
            int qtd;
            std::string data;
    };
}

#endif
