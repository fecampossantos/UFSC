// Copyright 2018 Felipe de Campos Santos

#ifndef STRUCTURES_ARRAY_LIST_H
#define STRUCTURES_ARRAY_LIST_H

#include <cstdint>
#include <stdexcept>  // C++ Exceptions


namespace structures {

/// classe arraylist
template<typename T>
class ArrayList {
 public:
    /// construtor
    ArrayList();

    /// construtor com parametro
    explicit ArrayList(std::size_t max_size);

    /// destrutor
    ~ArrayList();

    /// limpa a lista
    void clear();

    /// coloca um elemento no fim
    void push_back(const T& data);

    /// coloca um elemento no comeco
    void push_front(const T& data);

    /// coloca um elemento na posicao pedida
    void insert(const T& data, std::size_t index);

    /// coloca um elemento de forma ordenada
    void insert_sorted(const T& data);

    /// retorna o elemento do final
    T pop(std::size_t index);

    /// retorna o elemento do final
    T pop_back();

    /// retorna o primeoro elemento
    T pop_front();

    /// remove o elemento dado
    void remove(const T& data);

    /// retorna se a lista esta cheia ou nao
    bool full() const;

    /// retorna se a lista estaa vazia ou nao
    bool empty() const;

    /// retorna se a lista contem o elemento dado
    bool contains(const T& data) const;

    /// retorna o local do elemento addo
    std::size_t find(const T& data) const;

    /// retorna o tamanho atual da lista
    std::size_t size() const;

    /// retorna o maximo de elementos da lista
    std::size_t max_size() const;

    /// retorna o elemento da posicao dada
    T& at(std::size_t index);

    /// retorna o elemento da posicao dada
    T& operator[](std::size_t index);

    /// retorna o elemento da posicao dada
    const T& at(std::size_t index) const;

    /// retorna o elemento da posicao dada
    const T& operator[](std::size_t index) const;

 private:
    T* contents;
    std::size_t size_;
    std::size_t max_size_;

    static const auto DEFAULT_MAX = 10u;
};

}  // namespace structures

template<typename T>
structures::ArrayList<T>::ArrayList() {
  size_ = 0;
  contents = new T[DEFAULT_MAX];
}

template<typename T>
structures::ArrayList<T>::ArrayList(std::size_t max_size) {
  size_ = 0;
  max_size_ = max_size;
  contents = new T[max_size_];
}

template<typename T>
structures::ArrayList<T>::~ArrayList() {
  delete[] contents;
}

template<typename T>
void structures::ArrayList<T>::clear() {
    size_ = 0;
}

template<typename T>
void structures::ArrayList<T>::push_back(const T& data) {
  if (full()) {
    throw std::out_of_range("A lista esta cheia.");
  } else {
      contents[size_] = data;
      size_ += 1;
  }
}

template<typename T>
void structures::ArrayList<T>::push_front(const T& data) {
  int i = size_;
  if (full()) {
    throw std::out_of_range("A lista esta cheia.");
  } else {
    while (i > 0) {
      contents[i] = contents[i - 1];
      i--;
    }
    contents[0] = data;
    size_++;
  }
}

template<typename T>
void structures::ArrayList<T>::insert(const T& data, std::size_t index) {
    if (full()) {
        throw std::out_of_range("A lista esta cheia");
    } else if (index < 0 || index > size_) {
        throw std::out_of_range("O index está fora do escopo");
    } else if (index == size_) {
        contents[size_] = data;
        size_ += 1;
    } else {
        int i = size_;
        while (i > index) {
            contents[i] = contents[i-1];
            i -= 1;
        }
        contents[index] = data;
        size_ += 1;
    }
}

template<typename T>
void structures::ArrayList<T>::insert_sorted(const T& data) {
  int i = 0;
  if (full()) {
    throw std::out_of_range("A lista esta cheia.");
  } else {
    while (i < size_ && data > contents[i]) {
      i++;
    }
    insert(data, i);
  }
}

template<typename T>
T structures::ArrayList<T>::pop(std::size_t index) {
  if (index >= size_) {
    throw std::out_of_range("O index esta fora do escopo.");
  } else {
      std::size_t i = index;
      T value = contents[index];
      while (i < size_-1) {
          contents[i] = contents[i+1];
          i++;
      }
      size_--;
      return value;
  }
}

template<typename T>
T structures::ArrayList<T>::pop_back() {
  if (empty()) {
    throw std::out_of_range("A lista esta vazia.");
  } else {
      size_ -= 1;
      return contents[size_];
  }
}


template<typename T>
T structures::ArrayList<T>::pop_front() {
  if (empty()) {
    throw std::out_of_range("A lista esta vazia.");
  } else {
    T aux = contents[0];
    int i = 0;
    while (i < size_ - 1) {
        contents[i] = contents[i+1];
        i++;
    }
    size_--;
    return aux;
  }
}

template<typename T>
void structures::ArrayList<T>::remove(const T& data) {
    std::size_t index = find(data);
    if (index < size_) {
        pop(index);
    }
}

template<typename T>
bool structures::ArrayList<T>::full() const {
  if (size_ == max_size_) {
    return true;
  }  else {
    return false;
  }
}

template<typename T>
bool structures::ArrayList<T>::empty() const {
  if (size_ == 0) {
    return true;
  } else {
    return false;
  }
}

template<typename T>
bool structures::ArrayList<T>::contains(const T& data) const {
  int i = 0;
  int con = false;
  if (empty()) {
    throw std::out_of_range("A lista esta vazia.");
    return false;
  } else {
    while (i < size_) {
      if (contents[i] == data) {
        con = true;
      }
      i++;
    }
  }
  return con;
}

template<typename T>
std::size_t structures::ArrayList<T>::find(const T& data) const {
  int i = 0;
  if (empty()) {
    throw std::out_of_range("A lista esta vazia.");
  } else {
    while (i < size_) {
      if (contents[i] == data) {
        return i;
      }
      i++;
    }
  }
  // se nao achar, retorna o tamanho da lista
  return size_;
}

template<typename T>
std::size_t structures::ArrayList<T>::size() const {
  return size_;
}

template<typename T>
std::size_t structures::ArrayList<T>::max_size() const {
  return max_size_;
}

template<typename T>
T& structures::ArrayList<T>::at(std::size_t index) {
  if (index < 0 || index > size_) {
      throw std::out_of_range("O index esta fora dos limites");
  } else {
      return contents[index];
  }
}

template<typename T>
T& structures::ArrayList<T>::operator[](std::size_t index) {
  return contents[index];
}


#endif
