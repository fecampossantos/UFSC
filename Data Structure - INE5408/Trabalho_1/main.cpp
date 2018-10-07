#include <iostream>
#include <fstream>
#include <string>
#include <stdexcept>

#include "ImageTreat.cpp"
#include "ImageAnalyser.cpp"

using namespace std;


string readFile(string * name) {
    ifstream file;
    file.open(*name);

    if (file.is_open()) {
        cout << "File opened successfully" << endl;
    } else {
      throw invalid_argument("Error opening file.");
    }

    string content;
    string line;
    cout << "Reading content..." << endl;
    while (getline(file, line)) {
        content += line;
    }
    cout << "Content read!" << endl;
    return content;
}

int main() {
  cout << "Enter with the file name: " << endl;
  string file_name;
  cin >> file_name;
  string content = readFile(&file_name);

  file::Parser parser;
  file::JoinImage* join_image = parser.parseFile(&content);
  cout<<"File parsed! Continuing..."<<endl;

  cout<<"Getting image"<<endl;
  structure::Image* images = join_image->getImage();

  cout<<"Analisying image"<<endl;
  structure::Analyser* analyser; //= new structure::Analyser();

  cout << "Number of images: " << join_image->getNumberImages() << endl ;
  for (auto i = 0; i < join_image->getNumberImages(); i++) {
      cout << "i: " << i << endl;
      analyser->setImage(images);
      cout << "Resultado da imagem " << i << ": " << analyser->qtdFormaConvexa() << endl;
  }

  //delete analyser;

  return 0;
}
