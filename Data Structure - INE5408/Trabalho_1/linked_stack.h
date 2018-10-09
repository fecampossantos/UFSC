//! Copyright [2018] Felipe de Campos Santos

#include <cstdint>
#include <stdexcept>

namespace structures {

//!
template<typename T>

//!!  linked stack
class LinkedStack {
 public:
        LinkedStack();

        ~LinkedStack();

        //! clear
        void clear();

        //! push
        void push(const T& data);

        //! pop
        T pop();

        //! top
        T& top() const;

        //! empty
        bool empty() const;

        //! ize
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

            //! get data
            T& data() {
                return data_;
            }

            //!  get data const
            const T& data() const {
                return data_;
            }

            //!  get next
            Node* next() {
                return next_;
            }

            //!  get next const
            const Node* next() const {
                return next_;
            }

            //!  set next
            void next(Node* next) {
                next_ = next;
            }

         private:
            T data_;
            Node* next_;
        };

         //!  top
        Node* top_ = nullptr;

        //!! size
        std::size_t size_{0u};
};
    //!!
    template <typename T>
    LinkedStack<T>::LinkedStack() {
        top_ = nullptr;
        size_ = 0;
    }

    //!!
    template <typename T>
    LinkedStack<T>::~LinkedStack() {
        if (!empty()) {
            clear();
        }
    }

    //!!
    template <typename T>
    void LinkedStack<T>::clear() {
        size_= 0;
    }

    //!!
    template <typename T>
    void LinkedStack<T>::push(const T &data) {  //!
        Node* nd = new Node(data, nullptr);
        nd->next(top_);
        top_ = nd;
        size_++;
    }

    //!!
    template <typename T>
    T LinkedStack<T>::pop() {  //!
        if (empty()) {
            throw std::out_of_range("Empty");
        }
        auto out = top_;
        T data = out->data();
        top_ = out->next();
        size_--;
        delete out;
        return data;
    }

    //!!
    template  <typename T>
    T& LinkedStack<T>::top() const {
        if (empty()) {
            throw std::out_of_range("Empty ");
        }
        return top_->data();
    }

    //!!
    template <typename T>
    bool LinkedStack<T>::empty() const {
        if (size_ == 0) {
            return true;
        } else {
            return false;
        }
    }

    //!!
    template <typename T>
    std::size_t LinkedStack<T>::size() const {
        return size_;
    }

}  //! namespace structures
