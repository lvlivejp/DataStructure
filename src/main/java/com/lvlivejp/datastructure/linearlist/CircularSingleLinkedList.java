package com.lvlivejp.datastructure.linearlist;

/**
 * 循环单链表实现约瑟夫环，可以随机生成下一个数字
 * @param <E>
 */
public class CircularSingleLinkedList<E> {

    // 保存链表中第一个节点
    transient Node<E> first;
    // 保存链表中最后一个节点
    transient Node<E> last;

    transient int cnt;
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
        last.next=first;
        cnt++;
        return true;
    }

    public E remove(int i){
        Node<E> node = first;
        Node<E> deleteNode;
        if(i == 0){
            //删除首节点时，将首节点下一个节点变为首节点
            first = node.next;
            //删除首节点时，将尾结点变为前置节点
            node = last;
        }else {
            //非首节点时，从首节点依次循环到i-1处，获取删除节点的前置节点
            for (int j = 0; j < i-1; j++) {
                node = node.next;
            }
        }
        //将前置节点的下一节点赋值给删除节点
        deleteNode = node.next;
        //如果删除节点为尾结点，则将前置节点变为尾结点
        if(deleteNode == last){
            last = node;
        }

        //删除节点的下一个节点赋值为前置节点的下一个节点
        node.next=deleteNode.next;
        cnt--;
        return deleteNode.e;
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
//                System.out.println(result);
            }
            return result;
        }

    }

    public static void main(String[] args) {
        JosephusExplain(41,3,false);

    }

    /**
     * 约瑟夫环，可以随机生成下一个数字
     * @param length
     * @param cnt
     * @param isChangeCnt
     */
    private static void JosephusExplain(int length,int cnt,boolean isChangeCnt) {
        CircularSingleLinkedList<Integer> circularLinkedList = new CircularSingleLinkedList<Integer>();
        for (int i = 1; i <= length; i++) {
            circularLinkedList.add(i);
        }
        Node node = circularLinkedList.first;
        Node deleteNode;
        while(node.next!=node){
            for (int i = 1; i < cnt-1; i++) {
                node = node.next;
            }
            deleteNode = node.next;
            System.out.println(deleteNode.e);
            node.next=deleteNode.next;
            if (isChangeCnt) {
                cnt= (int) (Math.random()*10)+1;
                System.out.println("cnt:"+cnt);
            }
            node = node.next;
        }
        System.out.println(node.e);
    }
}
