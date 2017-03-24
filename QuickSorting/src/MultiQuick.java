import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import BaseUtil.StdRandom;

public class MultiQuick extends RecursiveAction{
	 final int[] array;  
     final int lo;  
      final int hi;  
      private int THRESHOLD = 5000; 
  

      public MultiQuick(int[] array) {  
    	  this.array = array;  
          this.lo = 0;  
          this.hi = array.length - 1;  
    }  
      
      public MultiQuick(int[] array, int lo, int hi) {  
          this.array = array;  
         this.lo = lo;  
         this.hi = hi;  
    }  

	protected void compute() {  
         if (hi - lo < THRESHOLD) {  
        	 quicksort(array, lo, hi );
            
        } else {  
             int pivot = partition(array, lo, hi);  
            invokeAll(new MultiQuick(array, lo, pivot - 1), new MultiQuick(array, pivot + 1, hi));  
        }  
     }  
  
    private static int partition(int[] array, int lo, int hi) {  
          long x = array[hi];  
         int i = lo - 1;  
          for (int j = lo; j < hi; j++) {  
            if (array[j] <= x) {  
               i++;  
               swap(array, i, j);  
              }  
         }  
         swap(array, i + 1, hi);  
         return i + 1;  
    }  
  
     private static void swap(int[] array, int i, int j) {  
        if (i != j) {  
             int temp = array[i];  
            array[i] = array[j];  
            array[j] = temp;  
        }  
    }  
  
    public static void quicksort(int[] array,int beg,int end){  
        if(beg >= end || array == null)  
            return;  
        int p = partition(array, beg, end);  
        quicksort(array, beg, p-1);  
        quicksort(array, p+1, end);  
    } 
    public static void main(String... args) throws InterruptedException {  
    	int[] a=StdRandom.getIntegerArray(100000000);
    	long begin = System.currentTimeMillis();
       MultiQuick sort = new MultiQuick(a);  
       //sort.quicksort(a, 0, a.length-1);
      ForkJoinPool fjpool = new ForkJoinPool();  
      fjpool.execute(sort);  
      fjpool.shutdown();  
      fjpool.awaitTermination(30, TimeUnit.SECONDS);  
      long end = System.currentTimeMillis();
      long costTime = end - begin;
		System.out.println("test"  + ":" + costTime + "ms");
		for(int i=0;i<1000;i++){
        	System.out.print(a[i]);
        	System.out.print(" ");
		}
   }  
}
