///  Copyright 2018 Felipe de Campos Santos

#ifndef STRUCTURES_ARRAY_QUEUE_H
#define STRUCTURES_ARRAY_QUEUE_H

#include <cstdint>  // std::size_t
#include <stdexcept>  // C++ Exceptions

namespace structures {

template<typename T>

///  classe
class ArrayQueue {
 public:
    ///  construtor sem parametros
    ArrayQueue();

    ///  construtor com parametros
    explicit ArrayQueue(std::size_t max);

    ///  deleta a fila
    ~ArrayQueue();

    /// insere um elemento na fila
    void enqueue(const T& data);

    /// retira um elemento da fila
    T dequeue();

    /// retorna o ultimo elemento
    T& back();

    ///  limpa a fila
    void clear();

    ///  retorna o tamanho da fila
    std::size_t size();

    ///  retorna o tamanho maximo da fila
    std::size_t max_size();

    ///  retorna true se a fila estiver vazia
    bool empty();

    ///  retorna true se a fila estiver cheia
    bool full();

 private:
    T* contents;
    std::size_t size_;
    std::size_t max_size_;

    static const auto DEFAULT_SIZE = 10u;
};

}  ///  namespace structures

template<typename T>
structures::ArrayQueue<T>::ArrayQueue() {
  size_ = -1;
  max_size_ = DEFAULT_SIZE;
  contents = new T[max_size_];
}

template<typename T>
structures::ArrayQueue<T>::ArrayQueue(size_t max) {
  size_ = -1;
  max_size_ = max;
  contents = new T[max_size_];
}

template<typename T>
structures::ArrayQueue<T>::~ArrayQueue() {
    delete[] contents;
}

template<typename T>
void structures::ArrayQueue<T>::enqueue(const T& data) {
  if (full()) {
    throw std::out_of_range("Queue is full.");
  } else {
    size_ += 1;
    contents[size_] = data;
  }
}

template<typename T>
T structures::ArrayQueue<T>::dequeue() {
  T aux = contents[0];
  if (empty()) {
      throw std::out_of_range("Queue is already empty.");
  } else {
    for (int i = 0; i < size_; i++) {
        contents[i] = contents[i+1];
    }
  }
  size_ -= 1;
  return aux;
}

template<typename T>
T& structures::ArrayQueue<T>::back() {
  if (empty()) {
    throw std::out_of_range("Out of range.");
  } else {
    return contents[size_];
  }
}

template<typename T>
void structures::ArrayQueue<T>::clear() {
  size_ = -1;
}

template<typename T>
size_t structures::ArrayQueue<T>::size() {
  return size_ + 1;
}

template<typename T>
size_t structures::ArrayQueue<T>::max_size() {
  return max_size_;
}

template<typename T>
bool structures::ArrayQueue<T>::empty() {
  if (size_ == -1) {
    return true;
  } else {
    return false;
  }
}

template<typename T>
bool structures::ArrayQueue<T>::full() {
  if (size_ + 1 == max_size_) {
    return true;
  } else {
    return false;
  }
}


#endif
