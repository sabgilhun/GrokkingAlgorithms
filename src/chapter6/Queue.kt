package chapter6

class Queue<E>(private val _elements: MutableList<E> = mutableListOf()) : MutableList<E> by _elements {

    fun pushAll(elements: Collection<E>) {
        _elements.addAll(elements)
    }

    fun pop() = _elements.removeAt(0)
}