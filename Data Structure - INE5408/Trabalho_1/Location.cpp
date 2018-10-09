#ifndef MODEL_COORDENADA
#define MODEL_COORDENADA

namespace coord {

    /**
    *   classe para criação de um objeto com dois ints
    *   representando uma coordenada
    **/
    class Location {
        public:
            /**
            *   Construtor vazio
            **/
            Location() {}

            /**
            *   construtor com pametros
            *
            *   @param x int, y int
            *       representam as coordenadas
            **/
            Location(int x, int y) {
                this->x = x;
                this->y = y;
            }

            /**
            *   metodo que retorna a coordenada x
            *
            *   @return int
            *       retorna x
            **/
            int getX() {
                return this->x;
            }

            /**
            *   metodo que retorna a coordenada y
            *
            *   @return int
            *       retorna y
            **/
            int getY() {
                return this->y;
            }
        private:
            int x;
            int y;
    };
}

#endif
