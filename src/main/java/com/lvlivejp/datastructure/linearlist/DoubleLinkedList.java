package com.lvlivejp.datastructure.linearlist;

public class DoubleLinkedList<E> {

    // 保存链表中第一个节点
    transient Node<E> first;
    // 保存链表中最后一个节点
    transient Node<E> last;

    private static class Node<E>{
        private E e;
        private Node<E> next;
        private Node<E> pre;

        public Node(E e, Node<E> pre, Node<E> next) {
            this.e = e;
            this.next = next;
            this.pre = pre;
        }
    }

    public boolean add(E e){
        Node<E> node = new Node<E>(e,last,null);
        if(first == null){
            first = node;
        }else{
            last.next = node; //新插入节点链到已知的尾结点的后面
        }
        last = node; //将新插入的节点作为尾结点
        return true;
    }

    @Override
    public String toString() {
        if(first==null){
            return "";
        }else{
            Node<E> node=first;
            String result = "";
            while (node != null){
                result +=node.e+",";
                node = node.next;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<Integer>();
        doubleLinkedList.add(0);
        doubleLinkedList.add(1);
        doubleLinkedList.add(2);
        doubleLinkedList.add(3);
        doubleLinkedList.add(4);
        doubleLinkedList.add(5);
        doubleLinkedList.add(6);
        System.out.println(doubleLinkedList);

    }
}
