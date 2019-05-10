package com.lvlivejp.datastructure;

public class CircularDoubleLinkedList<E> {

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
        Node<E> l = last;
        if(first == null){
            first = node;
        }else{
            l.next = node; //新插入节点链到已知的尾结点的后面
        }
        last = node; //将新插入的节点作为尾结点
        first.pre = last;
        last.next=first;
        return true;
    }

    @Override
    public String toString() {
        if(first==null){
            return "";
        }else{
            Node<E> node=first;
            String result = "";
            while (true){
                result +=node.e+",";
                node = node.next;
                if(node == first){
                    break;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        moveEnglishLetter(29);

    }

    /**
     * 用双向循环链表实现左右移动26个字母
     * @param cnt
     */
    private static void moveEnglishLetter(int cnt) {
        CircularDoubleLinkedList<Character> doubleLinkedList = new CircularDoubleLinkedList<Character>();
        for (int i = 0; i < 26; i++) {
            doubleLinkedList.add((char) (65+i));
        }
        System.out.println(doubleLinkedList);

        if(cnt<0){
            for (int i = 0; i < 3; i++) {
                doubleLinkedList.first = doubleLinkedList.first.pre;
                doubleLinkedList.last = doubleLinkedList.last.pre;
            }
        }else if(cnt > 0 ){
            for (int i = 0; i < 3; i++) {
                doubleLinkedList.first = doubleLinkedList.first.next;
                doubleLinkedList.last = doubleLinkedList.last.next;
            }
        }

        System.out.println(doubleLinkedList);
    }
}
