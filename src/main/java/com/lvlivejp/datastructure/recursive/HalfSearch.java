package com.lvlivejp.datastructure.recursive;

/**
 * 递归二分法查找
 */
public class HalfSearch {
    public static void main(String[] args) {

        Integer[] searchs = new Integer[]{1, 3, 5, 7, 9, 11, 14, 23, 25, 27, 34};
        Integer target = 30;
        int end = searchs.length-1;
        System.out.println(searchByRecursive(searchs, target, 0, end));
    }

    private static int searchByRecursive(Integer[] searchs, Integer target, int begin, int end) {
        if(target>searchs[end] || target<searchs[begin]){
            return -1;
        }
        int mid = (end + begin) / 2;
        if (searchs[mid] == target) {
            return mid;
        } else if (target > searchs[mid]) {
            return searchByRecursive(searchs, target, mid+1, end);
        } else {
            return searchByRecursive(searchs, target, begin, mid-1);
        }
    }
}
