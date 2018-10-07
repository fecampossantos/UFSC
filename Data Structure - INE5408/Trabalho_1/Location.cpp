#ifndef MODEL_COORDENADA
#define MODEL_COORDENADA

namespace coord {

    class Location {
        public:

            Location() {}


            Location(int x, int y) {
                this->x = x;
                this->y = y;
            }


            int getX() {
                return this->x;
            }


            int getY() {
                return this->y;
            }
        private:
            int x;
            int y;
    };
}

#endif
