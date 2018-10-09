#ifndef STRUCTURES_LINKED_QUEUE_H
#define STRUCTURES_LINKED_QUEUE_H

#include <stdexcept>

namespace structures {

//! ...
template<typename T>
class LinkedQueue {
 public:
    //! ...
    LinkedQueue() {}
    //! ...
    ~LinkedQueue() {
        this->clear();
    }
    //! ...
    void clear() {
        Node * aux;
        for (int i = 0; i < this->size_; i++) {
            aux = this->head->next();
            delete this->head;
            this->head = aux;
        }
        this->size_ = 0;
    }
    //! ...
    void enqueue(const T& data) {
        if (this->size_ == 0) {
            this->head = new Node(data);
            this->tail = this->head;
            this->size_ += 1;
            return;
        }

        Node * aux = this->head;
        for (int i = 0; i < this->size_ - 1; i++) {
            if (!aux->next()) {
                break;
            }
            aux = aux->next();
        }
        aux->next(new Node(data));
        this->tail = aux->next();
        this->size_ += 1;
        return;
    }
    //! ...
    T dequeue() {
        if (this->size_ == 0) {
            throw std::out_of_range("Lista Vazia");
        }
        T value = this->head->data();
        Node * aux = this->head->next();
        delete this->head;
        this->head = aux;
        this->size_ += -1;
        return value;
    }
    //! ...
    T& front() const {
        if (this->size_ == 0) {
            throw std::out_of_range("Lista Vazia");
        }
        return this->head->data();
    }
    //! ...
    T& back() const {
        if (this->size_ == 0) {
            throw std::out_of_range("Lista Vazia");
        }
        return this->tail->data();
    }
    //! ...
    bool empty() const {
        return (this->size_ == 0);
    }
    //! ...
    std::size_t size() const {
        return this->size_;
    }

 private:
    class Node {  // Elemento
     public:
        explicit Node(const T& data): data_{data} {}

        Node(const T& data, Node* next): data_{data}, next_{next} {}

        T& data() {  // getter: dado
            return data_;
        }

        const T& data() const {  // getter const: dado
            return data_;
        }

        Node* next() {  // getter: próximo
            return next_;
        }

        const Node* next() const {  // getter const: próximo
            return next_;
        }

        void next(Node* node) {  // setter: próximo
            next_ = node;
        }

     private:
        T data_;
        Node* next_{nullptr};
    };

    Node* head{nullptr};  // nodo-cabeça
    Node* tail{nullptr};  // nodo-fim
    std::size_t size_{0u};  // tamanho
};

}  // namespace structures

#endif
