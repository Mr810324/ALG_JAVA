package quicksort;

public class quicksorting {
	public static void quicksort(int[] array,int beg,int end){  
        if(beg >= end || array == null)  
            return;  
        int p = partition(array, beg, end);  
        quicksort(array, beg, p-1);  
        quicksort(array, p+1, end);  
    }  
	private static int partition(int[] array, int beg, int end) {  
        int first = array[beg];  
        int i = beg, j = end;  
        while (i < j) {  
            while (array[i] <= first && i < end) {  
                i++;  
            }  
            while (array[j] > first && j >= beg) {  
                j--;  
            }  
            if (i < j) {  
                array[i] = array[i] ^ array[j];  
                array[j] = array[i] ^ array[j];  
                array[i] = array[i] ^ array[j];  
            }  
        }  
        if (j != beg) {  
            array[j] = array[beg] ^ array[j];  
            array[beg] = array[beg] ^ array[j];  
            array[j] = array[beg] ^ array[j];  
        }  
        return j;  
    }  
	
	public static void quicksort_multi_Thread(int a[],int left,int right) {
		if(left>right)
			return ;
		int temp=a[left];
		int  i=left;
		int j=right;
		while(i!=j)
		{
			while(a[j]>=temp&&i<j)
				j--;
			while(a[j]<=temp&&i<j)
				i++;
			if(i<j)
			{
				int t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
		}
		a[left]=a[i];
		a[i]=temp;
		final int  h=i;
		
		
			Thread t1=new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	quicksort(a,left,h-1);
	            	
	            }  
	        });
			t1.start(); 
			Thread t2=new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	
	            	quicksort(a,h+1,right);
	            }  
	        });
			t2.start();
			try {
				t1.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
