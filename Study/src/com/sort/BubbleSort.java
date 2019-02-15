package com.sort;

public class BubbleSort {
    public static void bubbleSort(Integer [] a)
    {
        for (int i=0;i<a.length-1;i++)
        {
            boolean flag=true;
            for(int j=0;j<a.length-1-i;j++)
            {
                if (a[j]<a[j+1]) {
                    int t= a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }

    public static void main(String[] args) {
        Integer [] a={3,2,5,8,4,7,6,9};
        bubbleSort(a);
        for(Integer in :a)
        {
            System.out.println(in);
        }
    }
}
