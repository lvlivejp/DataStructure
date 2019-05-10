package com.lvlivejp.datastructure;

/**
 * 汉诺塔递归
 */
public class Hanoi {
    public static void main(String[] args){

        moveHanoi(2,"a","b","c");
    }

    private static void moveHanoi(int i, String a, String b, String c) {
        if(i==1){
            System.out.println(a + ">" + c);
        }else{
            //将N-1层从A柱移到B柱
            moveHanoi(i-1,a,c,b);
            //将N层从A柱移到C柱
            System.out.println(a + ">" + c);
            //将N-1层从B柱移到C柱
            moveHanoi(i-1,b,a,c);
        }
    }
}
