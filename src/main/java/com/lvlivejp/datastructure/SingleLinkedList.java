package com.lvlivejp.datastructure;

public class SingleLinkedList<E> {

    // 保存链表中第一个节点
    transient Node<E> first;
    // 保存链表中最后一个节点
    transient Node<E> last;

    private static class Node<E>{
        private E e;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }
    }

    public boolean add(E e){
        Node<E> node = new Node<E>(e,null);
        if(first == null){
            first = node;
            last = node;
        }else{
            last.next = node; //新插入节点链到已知的尾结点的后面
            last = node; //将新插入的节点作为尾结点
        }
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
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        singleLinkedList.add(0);
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        singleLinkedList.add(5);
        System.out.println(singleLinkedList);
    }
}
