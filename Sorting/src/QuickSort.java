import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

	
	public static Comparable select(Comparable [] arr,int k) {
		if(k >= arr.length) {
			throw new IllegalArgumentException("index k out of boundry");
		}
		int lo=0;
		int hi= arr.length - 1;
		StdRandom.shuffle(arr);
		
		while(lo < hi ) {
			int index = partition(arr,lo,hi);
			if (index > k) {
				hi = index - 1;
			}
			else if (index  < k) {
				lo = index + 1;
			}
			else {
				return arr[index];
			}
		}
		return arr[k];
	}
	public static void sort(Comparable [] arr,int lo, int hi) {
		if(lo >= hi) {
			return;
		}
		int index=partition(arr,lo,hi);
		sort(arr,lo,index-1);
		sort(arr,index+1,hi);
	}
	public static void sort(Comparable [] arr) {
		StdRandom.shuffle(arr);
		sort(arr,0,arr.length - 1);
	}
	
	private static int partition(Comparable [] arr, int lo, int hi) {
		int i = lo; 
		int j = hi + 1 ;
		while(true) {
			while(less(arr,++i,lo)) {
				if(i == hi) {
					break;
				}
			}
			while(less(arr,lo,--j)) {
				if(j == lo) {
					break;
				}
			}
			
			if(i >= j) {
				break;
			}
			swap(arr,i,j);
		}
		
		swap(arr,lo,j);
		return j;
	}
	private static void swap(Comparable[] arr,int i,int j)
	{
		Comparable temp=arr[j];
		arr[j]=arr[i];
		arr[i]=temp;
	}
	private static boolean less(Comparable [] arr, int i, int j) {
		return arr[i].compareTo(arr[j]) < 0;
	}
	
	public static void main(String [] args) {
		String []  arr= {"albear","hebesh","filly","remon","kanas","soliman"};
//		QuickSort.sort(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]+" ");
//		}
		System.out.println(QuickSort.select(arr, 6));
	}
}
