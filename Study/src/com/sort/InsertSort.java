package com.sort;

public class InsertSort {
    public static <T extends Comparable<? super T>> void insertSort(T []a)
    {
        for (int i=1;i<a.length;i++)
        {
            T tem=a[i];
            int j;
            for(j=i;j>0&&tem.compareTo(a[j-1])<0;j--)
            {
                a[j]=a[j-1];
            }
            a[j]=tem;
        }
    }
    public static void main(String []args)
    {
        Integer []a={3,2,5,8,4,7,6,9};
        insertSort(a);
        for(Integer in :a)
        {
            System.out.println(in);
        }
    }

}
