package com.sort;

public class QuickSort {
    public static void quickSort(int data[],int left,int right)
    {
        int base;
        if(right>left)
        {
            base=sort(data,left,right);
            quickSort(data,left,base-1);//小于基准
            quickSort(data,base+1,right);//大于基准
        }
    }
    public static int sort(int [] data,int left,int right)
    {
        int i=data[left];
        return 1;
    }

    public static void main(String[] args) {

    }
}
