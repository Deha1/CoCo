package com.sort;

public class SelectionSort {
    public static void SelectionSort(int a[]) //选择排序
    {
        int mix,temp;
        for(int i=0;i<a.length-1;i++) //每次循环数组，找出最小的元素，放在前面，前面的即为排序好的
        {
            mix=i; //假设最小元素的下标
            for(int j=i+1;j<a.length;j++) //将上面假设的最小元素与数组比较，交换出最小的元素的下标
                if(a[j]<a[mix])
                    mix=j;
            //若数组中真的有比假设的元素还小，就交换
            if(i!=mix)
            {
                temp=a[i];
                a[i]=a[mix];
                a[mix]=temp;
            }
        }
    }
    public static void main(String[] args) {
        int [] arr ={3,2,5,8,4,7,6,9,1};
        System.out.println("交换之前：");
        for (int num:arr)
        {
            System.out.print(num+" ");
        }
        SelectionSort(arr);
        System.out.println();
        System.out.println("交换之后：");
        for (int num1:arr)
        {
            System.out.print(num1+" ");
        }
    }
}
