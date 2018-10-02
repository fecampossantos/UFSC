#include "doubly_linked_list.h"
#include <iostream>

int main() {
  auto lista = structures::DoublyLinkedList<int>();

  lista.push_back(0);
  lista.push_back(1);
  lista.push_front(3);

  std::cout << "Lista começou com [3,0,1]\n\n";

  std::cout<<"Testing contains\n";
  std::cout<<"list contains 3? ";
  std::cout<<lista.contains(3);
  std::cout<<"\n";

  std::cout<<"list contains 0? ";
  std::cout<<lista.contains(0);
  std::cout<<"\n";

  std::cout<<"list contains 1? ";
  std::cout<<lista.contains(1);
  std::cout<<"\n";

  std::cout<<"list contains 10? ";
  std::cout<<lista.contains(10);
  std::cout<<"\n";

}
