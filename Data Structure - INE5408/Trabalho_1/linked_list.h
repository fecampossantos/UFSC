//! Copyright [2018] Felipe de Campos Santos

#ifndef STRUCTURES_LINKED_LIST_H
#define STRUCTURES_LINKED_LIST_H

#include <cstdint>
#include <stdexcept>


namespace structures {

//! ...
template<typename T>
class LinkedList {
 public:
    //! ...
    LinkedList();  // construtor padrão
    //! ...
    ~LinkedList();  // destrutor
    //! ...
    void clear();  // limpar lista
    //! ...
    void push_back(const T& data);  // inserir no fim
    //! ...
    void push_front(const T& data);  // inserir no início
    //! ...
    void insert(const T& data, std::size_t index);  // inserir na posição
    //! ...
    void insert_sorted(const T& data);  // inserir em ordem
    //! ...
    T& at(std::size_t index);  // acessar um elemento na posição index
    //! ...
    T pop(std::size_t index);  // retirar da posição
    //! ...
    T pop_back();  // retirar do fim
    //! ...
    T pop_front();  // retirar do início
    //! ...
    void remove(const T& data);  // remover específico
    //! ...
    bool empty() const;  // lista vazia
    //! ...
    bool contains(const T& data) const;  // contém
    //! ...
    std::size_t find(const T& data) const;  // posição do dado
    //! ...
    std::size_t size() const;  // tamanho da lista

 private:
    class Node {  // Elemento
     public:
        explicit Node(const T& data):
            data_{data}
        {}

        Node(const T& data, Node* next):
            data_{data},
            next_{next}
        {}

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

    Node* end() {  // último nodo da lista
        auto it = head;
        for (auto i = 1u; i < size(); ++i) {
            if (it->next() == nullptr) {
                throw std::out_of_range("Erro - end");
            }
            it = it->next();
        }
        return it;
    }

    Node* head{nullptr};  //  ponteiro pro inicio lista
    std::size_t size_{0u};  //  tamanho da lista
};

}  // namespace structures


//! ...
template<typename T>
structures::LinkedList<T>::LinkedList() {
    head = nullptr;
    size_ = 0;
}


//! ...
template<typename T>
structures::LinkedList<T>::~LinkedList() {
    clear();
}

//! ...
template<typename T>
void structures::LinkedList<T>::clear() {
    while (!empty()) {
        pop_front();
    }
    head = nullptr;
    size_ = 0u;
}

//! ...
template<typename T>
void structures::LinkedList<T>::push_back(const T& data) {
    insert(data, size());
}


//! ...
template<typename T>
void structures::LinkedList<T>::push_front(const T& data) {
    if (empty()) {
        head = new Node(data, nullptr);
        size_+=1;
    } else {
        Node* nd = new Node(data, head);    //!!!
        if (nd == nullptr) {
            throw std::out_of_range("Nodo criado é nulo");
        }
        head = nd;
        size_+=1;
    }
}


//! ...
template<typename T>
void structures::LinkedList<T>::insert(const T& data, std::size_t index) {
    Node *novo, *previous;
        if (index > size_) {
            throw std::out_of_range("ERROPOSICAO-insert");
        } else if (index == 0) {
            push_front(data);
        } else {
            novo = new Node(data);
            if (novo == nullptr) {
                throw std::out_of_range("ERROLISTACHEIA-insert");
            } else {
                previous = head;
                for (auto i = 1u; i < index; ++i) {
                    previous = previous->next();
                }
                novo->next(previous->next());
                previous->next(novo);
                size_+=1;
            }
        }
}


//! ...
template<typename T>
void structures::LinkedList<T>::insert_sorted(const T& data) {
    Node* actual;
        std::size_t index;
        if (empty()) {
            return push_front(data);
        } else {
            actual = head;
            index = 0;
            while (actual->next() != nullptr && data > actual->data()) {
                actual = actual->next();
                ++index;
            }
            if (data > actual->data()) {
                return insert(data, index +1);
            } else {
                return insert(data, index);
            }
        }
}


//! ...
template<typename T>
T& structures::LinkedList<T>::at(std::size_t index) {
    Node* nd = head;
    if (index < 0 || index > size()) {
        throw std::out_of_range("Not valid");
    } else {
        for (int i = 0; i < index; i++) {
            if (nd->next() == nullptr) {
                throw std::out_of_range("Erro - at");
            }
            nd = nd->next();
        }
    }
    return nd->data();
}


//! ...
template<typename T>
T structures::LinkedList<T>::pop(std::size_t index) {
    if (empty() || index > size() - 1) {
        throw std::out_of_range("Lista vazia");
    } else {
        Node *anterior, *retorno;
        T dado;
        if (index == 0) {
            return pop_front();
        } else {
            anterior = head;
            for (auto i = 0; i < index - 1; i++) {
                anterior = anterior->next();
            }
            retorno = anterior->next();
            dado = retorno->data();
            anterior->next(retorno->next());
            size_--;
            delete(retorno);
            return dado;
        }
    }
}


//! ...
template<typename T>
T structures::LinkedList<T>::pop_back() {
    return pop(size() - 1);
}


//! ...
template<typename T>
T structures::LinkedList<T>::pop_front() {
    Node* left;
    T volta;
    if (empty()) {
        throw std::out_of_range("Erro - pop_front");
    } else {
        left = head;
        volta = left->data();
        head = left->next();
        size_--;
        delete(left);
        return volta;
    }
}


//! ...
template<typename T>
void structures::LinkedList<T>::remove(const T& data) {
    std::size_t index = find(data);
    pop(index);
}

//! ...
template<typename T>
bool structures::LinkedList<T>::empty() const {
    return head == nullptr;
}


//! ...
template<typename T>
bool structures::LinkedList<T>::contains(const T& data) const {
    return !(find(data) == size());
}

//! ...
template<typename T>
std::size_t structures::LinkedList<T>::find(const T& data) const {
    Node* nd = head;
    std::size_t index = 0u;
    while (1) {
        if (index == size()) {
            break;
        }
        if (nd->data() == data) {
            break;
        }
        nd = nd->next();
        index++;
    }
    return index;
}



//! ...
template<typename T>
std::size_t structures::LinkedList<T>::size() const {
    return size_;
}

#endif
