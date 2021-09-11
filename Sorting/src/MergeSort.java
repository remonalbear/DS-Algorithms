
public class MergeSort {

	public static void sort(Comparable [] arr,Comparable [] aux,int lo, int hi) {		
		if(lo >= hi) {
			return;
		}
		int mid=lo+(hi-lo)/2;
		// sort left side
		sort(arr,aux,lo,mid);
		//sort right side
		sort(arr,aux,mid+1,hi);
		//merge two sides
		merge(arr,aux,lo,mid,hi);
	}
	public static void sort(Comparable [] arr) {	
		Comparable [] aux=new Comparable[arr.length]; 
		sort(arr,aux,0,arr.length - 1);
	}
	
	private static void merge(Comparable [] arr,Comparable [] aux,int lo, int mid, int hi) {
		
		for(int i = lo; i <= hi;i++) {
			aux[i]=arr[i];
		}
		
		int left=lo;
		int right=mid+1;
		for(int k = lo; k <= hi; k++) {
			
			if(left > mid) {
				arr[k]=aux[right];
				right++;	
			}
			else if(right > hi) {
				arr[k]=aux[left];
				left++;	
			}
			
			else if(less(aux,left,right)) {
				arr[k]=aux[left];
				left++;
			}
			else {
				arr[k]=aux[right];
				right++;
			}
		}
		
	}
	
	private static boolean less(Comparable [] arr, int i, int j) {
		return arr[i].compareTo(arr[j]) < 0;
	}
	
	public static void main(String [] args) {
		String []  arr= {"remon","albear","soliman","kanas","filly","hebesh"};
		MergeSort.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
}
