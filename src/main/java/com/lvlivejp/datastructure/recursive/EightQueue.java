package com.lvlivejp.datastructure.recursive;

/**
 * 1.将皇后递归放入每一行的每一个格子。
 * 2.判断该格子是否安全，安全则进入下一层，不安全就放入该行下一个格子。
 * 3.该行的格子全部循环完，将上一层的皇后放入下一个格子中。
 */
public class EightQueue {

    private static int count;


    public static void setChess(Integer[][] chessborad,Integer row){

        /*
        当row为第n+1行时，表示完成了前面所有皇后棋子的摆放，解答数+1，并打印，然后返回
         */
        if(row == chessborad.length){
            count++;
            print(chessborad);
            return ;
        }
        /*
        复制副本
         */
        Integer[][] chess = chessborad.clone();

        /*
        循环该行的每一个棋格，将皇后放入。
         */
        for (int i = 0; i < chessborad.length; i++) {
            //将皇后放入
            chess[row][i]=1;
            //判断该位置的皇后是否安全
            if(isSafe(chess,row,i)){
                //如果安全，则进入下一层
                setChess(chess,row+1);
            }
            //该位置不安全或下一层循环完后，将该位置的皇后清空，继续放入下一个位置
            chess[row][i]=0;
        }


    }

    private static boolean isSafe(Integer[][] chess, Integer row, int col) {
        int step = 1;
        while (step <=row){
            if(chess[row - step][col] == 1){
                return false;
            }
            if(col - step>=0 && chess[row - step][col-step] == 1){
                return false;
            }
            if(col+step<chess.length && chess[row - step][col+step] == 1){
                return false;
            }
            step++;
        }
        return true;
    }

    private static void print(Integer[][] chessborad) {
        System.out.println("第" + count + "种算法");
        for (int i = 0; i < chessborad.length; i++) {
            for (int j = 0; j < chessborad.length; j++) {
                System.out.print(chessborad[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args){
        Integer size = 8;
        Integer[][] chess = new Integer[size][size];
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                chess[i][j]=0;
            }
        }
        setChess(chess,0);
    }
}
