#ifndef IMAGE_MODEL_CLASS
#define IMAGE_MODEL_CLASS

#include <string>

namespace model {
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
}

#endif
