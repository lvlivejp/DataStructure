package com.lvlivejp.datastructure.tree;

import java.util.LinkedList;

/**
 * 利用前序遍历法构造二叉树，null表示无子节点，不再递归
 * 采用单链表存储数据，并从单链表中取出数据，构造二叉树
 * @param <E>
 */
public class BinaryTree<E> {

    private TNode<E> root;

    private static class TNode<E>{

        private E e;
        private TNode<E> leftChild;
        private TNode<E> rightChild;

        public TNode(E e) {
            this.e = e;
        }

        public TNode(E e, TNode<E> leftChild, TNode<E> rightChild) {
            this.e = e;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public TNode<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TNode<E> leftChild) {
            this.leftChild = leftChild;
        }

        public TNode<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(TNode<E> rightChild) {
            this.rightChild = rightChild;
        }
    }

    /*
    每次从链表中取出一个元素，
    判断是否为空，如果为空则返回空节点
    不为空则初始化子节点，并构造左右子节点
     */
    public TNode createNode(LinkedList<E> linkedList){
        E e=linkedList.poll();
        if(e == null ){
            return null;
        }else{
            TNode tNode = new TNode<>(e);
            tNode.leftChild = createNode(linkedList);
            tNode.rightChild =  createNode(linkedList);
            return tNode;
        }
    }

    public void preOrderTraverse(){
        preOrderTraverse(this.root,1);
        System.out.println();
    }
    private void preOrderTraverse(TNode<E> tNode,int level){
        if(tNode == null){
            return;
        }
        System.out.print(tNode.e);
        preOrderTraverse(tNode.leftChild,level+1);
        preOrderTraverse(tNode.rightChild,level+1);
    }
    public static void main(String[] args){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add(null);
        linkedList.add("D");
        linkedList.add(null);
        linkedList.add(null);
        linkedList.add("C");
        linkedList.add("E");
        linkedList.add(null);
        linkedList.add(null);
        linkedList.add("F");
        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.root = binaryTree.createNode(linkedList);
        binaryTree.preOrderTraverse();
    }
}
