#ifndef DATASET_MODEL_CLASS
#define DATASET_MODEL_CLASS

#include "Image.cpp"

namespace model {
    /**
     *  Dataset class.
     *  Classe que mapeia o conteudo do XML
     */
    class Dataset {
        public:
            /**
             * Construtor vazio
             */
            Dataset() {}

            /**
             * Setar quantidade de imagens no XML
             * @param qtd int
             *      Quantidade de imagens
             */
            void setQtdImages(int qtd) {
                this->qtdImages = qtd;
            }

            /**
             * Setar array com os dados das imagens contidas no XML
             * @param image
             *      Ponteiro para uma estrutura com vários Objetos Image
             */
            void setImage(Image image[]) {
                this->image = image;
            }

            /**
             * Get array com os dados das imagens contidas no XML
             * @return
             *      Pointer to array of Images
             */
            Image * getImage() {
                return this->image;
            }

            /**
             * Get quantidade de imagens no XML
             * @return int
             *      Quantidade de imagens
             */
            int getQtdImages() {
                return this->qtdImages;
            }
        private:
            Image * image;  /**< Array com as imagens contidas no XML */
            int qtdImages;  /**< Informação da qtdImages contidas no XML para auxiliar em LOOPs */
    };
}

#endif