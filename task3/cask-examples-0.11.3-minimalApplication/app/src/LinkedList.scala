package app

sealed trait LinkedList[+A]
case object Nil extends LinkedList[Nothing]

case class Node[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

object LinkedList:
    def apply[A](next: A*): LinkedList[A] =
        if next.isEmpty then Nil
        else Node(next.head, apply(next.tail: _*))