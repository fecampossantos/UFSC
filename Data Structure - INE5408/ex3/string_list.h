// Copyright 2018 Felipe de Campos Santos

#ifndef STRUCTURES_STRING_LIST_H
#define STRUCTURES_STRING_LIST_H

#include <cstdint>
#include <stdexcept>  // C++ exceptions
#include <cstring>

namespace structures {

//! ...
template<typename T>
class ArrayList {
 public:
    //! ...
    ArrayList();
    //! ...
    explicit ArrayList(std::size_t max_size);
    //! ...
    ~ArrayList();

    //! ...
    void clear();
    //! ...
    void push_back(const T& data);
    //! ...
    void push_front(const T& data);
    //! ...
    void insert(const T& data, std::size_t index);
    //! ...
    void insert_sorted(const T& data);
    //! ...
    T pop(std::size_t index);
    //! ...
    T pop_back();
    //! ...
    T pop_front();
    //! ...
    void remove(const T& data);
    //! ...
    bool full() const;
    //! ...
    bool empty() const;
    //! ...
    bool contains(const T& data) const;
    //! ...
    std::size_t find(const T& data) const;
    //! ...
    std::size_t size() const;
    //! ...
    std::size_t max_size() const;
    //! ...
    T& at(std::size_t index);
    //! ...
    T& operator[](std::size_t index);
    //! ...
    const T& at(std::size_t index) const;
    //! ...
    const T& operator[](std::size_t index) const;

 protected:
    //! ...
    T* contents;

    //! ...
    std::size_t size_;

    //! ...
    std::size_t max_size_;

    //! ...
    static const auto DEFAULT_MAX = 10u;
};


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



//-------------------------------------



//! ...
//! ArrayListString e' uma especializacao da classe ArrayList
class ArrayListString : public ArrayList<char *> {
 public:
    //! ...
    ArrayListString() : ArrayList() {}
    //! ...
    explicit ArrayListString(std::size_t max_size) : ArrayList(max_size) {}
    //! ...
    ~ArrayListString();

    //! ...
    void clear();
    //! ...
    void push_back(const char *data);
    //! ...
    void push_front(const char *data);
    //! ...
    void insert(const char *data, std::size_t index);
    //! ...
    void insert_sorted(const char *data);
    //! ...
    char *pop(std::size_t index);
    //! ...
    char *pop_back();
    //! ...
    char *pop_front();
    //! ...
    void remove(const char *data);
    //! ...
    bool contains(const char *data);
    //! ...
    std::size_t find(const char *data);
};

// template<typename T>
// explicit structures::ArrayListString::ArrayListString(){
//   size_ = 0;
//   max_size_ = DEFAULT_MAX;
//   contents = new ArrayListString(size);
// };

// template<typename T>
// structures::ArrayListString::ArrayListString(max_size){
//   size_ = 0;
//   contents = new ArrayListString(max_size);
// };


structures::ArrayListString::~ArrayListString() {
  for (std::size_t i = 0; i < size_; i++) {
    delete[] contents[i];
  }
  delete[] contents;
}

void structures::ArrayListString::clear() {
  for (std::size_t i = 0; i < size_; i++) {
    delete[] contents[i];
  }
  size_ = 0;
}


void structures::ArrayListString::push_back(const char *data) {
  if (full()) {
    std::out_of_range("Full");
  } else {
    contents[size_] = const_cast<char*>(data);
  }
  size_ += 1;
}


void structures::ArrayListString::push_front(const char *data) {
  if (full()) {
    std::out_of_range("Full");
  } else {
    for (std::size_t i = size_; i > 0; i--) {
      contents[i+1] = contents[i];
    }
    contents[0] = const_cast<char*>(data);
  }
  size_++;
}


void structures::ArrayListString::insert(const char *data, std::size_t index) {
  if (full()) {
    std::out_of_range("Full");
  } else {
    for (std::size_t i = size_; i  > index; i--) {
      contents[i+1] = contents[i];
    }
    contents[index] = const_cast<char*>(data);
    size_++;
  }
}


void structures::ArrayListString::insert_sorted(const char *data) {
  std::size_t index;
  if (full()) {
    std::out_of_range("Full");
  } else {
    for (std::size_t i = 0; i < size_; i ++) {
      if (contents[i] >= data) {
        index = i;
        break;
      }
    }
    for (std::size_t j = max_size_; j > index; j--) {
      contents[j+1] = contents[j];
    }
    contents[index] = const_cast<char*>(data);
    size_++;
  }
}


char* structures::ArrayListString::pop(std::size_t index) {
  if (empty()) {
    std::out_of_range("Empty");
  } else {
    char* aux = contents[index];
    for (std::size_t i = index; i < size_; i++) {
      contents[i] = contents[i+1];
    }
    size_--;
    return aux;
  }
  return 0;
}


char* structures::ArrayListString::pop_back() {
  if (empty()) {
    std::out_of_range("Empty");
  } else {
    size_--;
    return contents[size_+1];
  }
  return 0;
}


char* structures::ArrayListString::pop_front() {
  if (empty()) {
    std::out_of_range("Empty");
  } else {
    char* aux = contents[0];
    for (std::size_t i = 1; i < size_; i++) {
      contents[i-1] = contents[i];
    }
    size_--;
    return aux;
  }
  return 0;
}


void structures::ArrayListString::remove(const char *data) {
  if (empty()) {
    std::out_of_range("Empty");
  } else {
    std::size_t index = find(data);
    pop(index);
  }
}


bool structures::ArrayListString::contains(const char *data) {
  bool cont = false;
  if (empty()) {
    std::out_of_range("Empty");
  } else {
    for (std::size_t i = 0; i < size_; i++) {
      if (contents[i] == data) {
        cont = true;
        break;
      }
    }
  }
  return cont;
}


std::size_t structures::ArrayListString::find(const char *data) {
  std::size_t index = size_;
  for (std::size_t i = 0; i < size_; i++) {
    if (contents[i] == data) {
      index = i;
      break;
    }
  }
  return index;
}

}  // namespace structures

#endif
