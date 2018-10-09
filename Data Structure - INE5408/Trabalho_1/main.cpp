//Copyright [2018]
//Alunos: Arthur Machado Capaverde, Felipe de Campos Santos

#include <iostream>
#include <fstream>
#include <string>
#include <stdexcept>

#include "ImageTreat.cpp"
#include "ImageAnalyser.cpp"

using namespace std;


/**
*   metodo abre o arquivo e transforma seu conteudo numa string
*
*   @param string
*       recebe o nome do arquivo
*
*   @return string
*       retorna o conteudo do arquivo
**/
string readFile(string * name) {
    ifstream file;
    file.open(*name);

    if (file.is_open()) {
        //cout << "File opened successfully" << endl;
    } else {
      throw invalid_argument("Error opening file.");
    }

    string content;
    string line;
    //cout << "Reading content..." << endl;
    while (getline(file, line)) {
        content += line;
    }
    //cout << "Content read!" << endl;
    return content;
}


/**
*   metodo principal do programa, coordena o funcionamento
*
*   @return int
*       retorna 0, para fechar o programa
**/
int main() {
  cout << "Enter with the file name: " << endl;
  string file_name;
  cin >> file_name;
  string content = readFile(&file_name);

  file::Parser parser;
  file::JoinImage* join_image = parser.parseFile(&content);
  //cout<<"File parsed! Continuing..."<<endl;

  //cout<<"Getting image"<<endl;
  structure::Image* images = join_image->getImage();

  //cout<<"Analisying image"<<endl;
  structure::Analyser* analyser = new structure::Analyser();

  for (auto i = 0; i < join_image->getNumberImages(); i++) {
      analyser->setImage(&images[i]);
      cout << images[i].getName() << " " << analyser->amountConected() << endl;
      //cout << "Resultado da imagem " << i << ": " << analyser->amountConected() << endl;
  }

  delete analyser;

  return 0;
}
