package com.hateapple.myspringboot.base.sort;

public class HeapSort {

    public static void initHeap(int[] array){
        int lastParentIndex = array.length/2 - 1;
        for(int index = lastParentIndex; index>=0; index--){
                reBuild(index, array, array.length);
        }
    }

    //构建大顶堆/小顶堆
    public static void reBuild(int parentIndex, int[] array, int arrayLength){
        int L = parentIndex*2 + 1;
        int R = L + 1;
        int maxNodeIndex = -1;

        //没有子节点
        if(L >= arrayLength && R>=arrayLength){
            return ;
        }

        //只有左孩子
        if(L < arrayLength && R>=arrayLength){
            maxNodeIndex = L;
        }

        //只有右孩子
        if(L >= arrayLength && R < arrayLength){
            maxNodeIndex = R;
        }

        //左右孩子都有,取大的一个
        if(L < arrayLength && R < arrayLength){
            maxNodeIndex = array[L] < array[R] ? R : L;
        }

        //父节点大于左右孩子,不交换
        if(array[parentIndex] >= array[maxNodeIndex]){
            return ;
        }else{
            swap(parentIndex, maxNodeIndex, array);
            reBuild(maxNodeIndex, array, arrayLength);
        }


    }

    private static void swap(int index1, int index2, int[] array){
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    //array已经是一个大顶堆/小顶堆
    public static void headSort(int[] array){
        for(int index=array.length -1; index>=0; index--){
            System.out.println("最大值：" + array[0] );
            swap(0, index, array);
            reBuild(0, array, index );
        }
    }

    public static void main(String[] args) {
        int[] array = {7, 5, 2, 8, 3, 1, 6, 9};
        HeapSort.initHeap(array);
        HeapSort.headSort(array);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
