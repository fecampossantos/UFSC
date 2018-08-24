///  Copyright 2018 Felipe de Campos Santos

#ifndef STRUCTURES_ARRAY_STACK_H
#define STRUCTURES_ARRAY_STACK_H

#include <cstdint>  // std::size_t
#include <stdexcept>  // C++ exceptions

namespace structures {

template<typename T>

/// classe
class ArrayStack {
 public:
        /// Construtor sem parametro
        ArrayStack();

        /// Construtor com parametro
        explicit ArrayStack(std::size_t max);

        /// Destrutor
        ~ArrayStack();

        ///  Metodo que coloca um elemento no topo da pilha
        void push(const T& data);

        ///  Metodo que tira o elemento do topo da pilha
        T pop();

        ///  Metodo que retorna o index do topo da pilha
        T& top();

        ///  Metodo que retira todos os elementos da pilha
        void clear();

        ///  Metodo que retorna o tamanho da pilha
        std::size_t size();

        ///  Metodo que retorna o máximo de
        ///  elementos na pilha
        std::size_t max_size();

        ///  Metodo que retorna se pilha esta vazia
        bool empty();

        ///  Metodo que retorna se pilha esta cheia
        bool full();

 private:
        T* contents;
        int top_;
        std::size_t max_size_;
    static const auto DEFAULT_SIZE = 10u;
};

}  /// namespace structures

template<typename T>
structures::ArrayStack<T>::ArrayStack() {
  top_ = -1;
  max_size_ = DEFAULT_SIZE;
  contents = new T[max_size_];
}

template<typename T>
structures::ArrayStack<T>::ArrayStack(std::size_t max) {
  top_ = -1;
  max_size_ = max;
  contents = new T[max_size_];
}


template<typename T>
structures::ArrayStack<T>::~ArrayStack() {
  delete[] contents;
}


template<typename T>
void structures::ArrayStack<T>::push(const T& data) {
  if (full()) {
    throw std::out_of_range("Out of range.");
  } else {
    top_ += 1;
    contents[top_] = data;
  }
}


template<typename T>
T structures::ArrayStack<T>::pop() {
  if (empty()) {
    throw std::out_of_range("Out of range.");
  } else {
    top_ -= 1;
    return contents[top_ + 1];
  }
}


template<typename T>
T& structures::ArrayStack<T>::top() {
  if (empty()) {
    throw std::out_of_range("Out of range.");
  } else {
    return contents[top_];
  }
}


template<typename T>
void structures::ArrayStack<T>::clear() {
  top_ = -1;
}


template<typename T>
size_t structures::ArrayStack<T>::size() {
  return top_ + 1;
}


template<typename T>
size_t structures::ArrayStack<T>::max_size() {
  return max_size_;
}


template<typename T>
bool structures::ArrayStack<T>::empty() {
  if (top_ == -1) {
    return true;
  } else {
    return false;
  }
}


template<typename T>
bool structures::ArrayStack<T>::full() {
  if (top_ + 1 == max_size_) {
    return true;
  } else {
    return false;
  }
}





#endif
