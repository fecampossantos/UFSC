//! Copyright [2018] Felipe de Campos Santos

#include <cstdint>
#include <stdexcept>

namespace structures {

//! Linked queue
template<typename T>
class LinkedQueue {
 public:
    LinkedQueue();

    ~LinkedQueue();

    //! clear
    void clear();

    //! push
    void enqueue(const T& data);

    //! pop
    T dequeue();

    //! returns the first data
    T& front() const;

    //! returns the last data
    T& back() const;

    //! returns true if empty, false otherwise
    bool empty() const;

    //! returns size
    std::size_t size() const;

 private:
    class Node {
     public:
        explicit Node(const T& data):
            data_{data}
        {}
        Node(const T& data, Node* next):
            data_{data},
            next_{next}
        {}

        Node* next() {
            return next_;
        }
        const Node* next() const {
            return next_;
        }

        void next(Node* next) {
            next_ = next;
        }

        T& data() {
            return data_;
        }
        const T& data() const {
            return data_;
        }

     private:
        T data_;
        Node* next_;
    };

    //! head
    Node* head = nullptr;

    //! tail
    Node* tail = nullptr;

    //! size
    std::size_t size_{0u};
};

    //!!  constructor
    template <typename T>
    LinkedQueue<T>::LinkedQueue() {
        size_ = 0;
        head = nullptr;
        tail = nullptr;
    }

    //!!  deletes the queue
    template  <typename T>
    LinkedQueue<T>::~LinkedQueue() {
        if (!empty()) {
            clear();
        }
    }

    //!!  clears the queue
    template <typename T>
    void LinkedQueue<T>::clear() {
        size_ = 0;
        head = nullptr;
        tail = nullptr;
    }

    //!!  push
    template <typename T>
    void LinkedQueue<T>::enqueue(const T &data) {
        Node *nd = new Node(data, nullptr);
        if (empty()) {
            head = nd;
            tail = head;
        }
        tail ->next(nd);
        tail= tail->next();
        size_++;
    }

    //!!  pop
    template  <typename T>
    T LinkedQueue<T>::dequeue() {
        if (empty()) {
            throw std::out_of_range("Fila vazia");
        }
        Node *out;
        T ret;
        out = head;
        ret = out->data();
        head = out->next();
        if (size_ == 1) {
            tail = nullptr;
        }
        size_--;
        delete out;
        return ret;
    }

    //!!  returns the first element
    template  <typename T>
    T& LinkedQueue<T>::front() const {
        if (empty()) {
            throw std::out_of_range("Fila vazia");
        }
        return head->data();
    }

    //!!  returns the last element
    template <typename T>
    T& LinkedQueue<T>::back() const {
        if (empty()) {
            throw std::out_of_range("Fila vazia");
        }
        return tail->data();
    }

    //!!  returns true if empty
    template  <typename T>
    bool LinkedQueue<T>::empty() const {
        if (head == nullptr && tail == nullptr) {
            return true;
        } else {
            return false;
        }
    }

    //!!  returns size
    template <typename T>
    std::size_t LinkedQueue<T>::size() const {
        return size_;
    }


}  //! namespace structures
