
public class HeapSort {

	public static void sort(Comparable [] arr) {
		int N=arr.length;
		// build the heap
		for(int i = N/2; i >= 1; i--) { 
			sink(arr,i,N);
		}
		
		 // sort the heap
		while(N > 1) {
			swap(arr,1,N);
			sink(arr,1,--N);
		}
	}
	
	private static void sink(Comparable [] arr,int k, int N) {

		while(2*k <= N) {
			int j = k * 2; // get the first child
			if(j < N && less(arr,j,j+1)) { // get the bigger child
				j++;
			}
			if(!less(arr,k,j)) { // check if the parent is bigger than the bigger child
				break;
			}
			swap(arr,k,j); // swap the smaller parent with the bigger child
			k=j; // update the node to be the bigger child
			
		}
	}
	
	private static void swap(Comparable[] arr,int i,int j)
	{
		Comparable temp=arr[j-1];
		arr[j-1]=arr[i-1];
		arr[i-1]=temp;
	}
	
	private static boolean less(Comparable [] arr,int i, int j )
	{
		return arr[i-1].compareTo(arr[j-1]) <  0; 	
	}
	
	public static void main(String [] args) {
		String []  arr= {"remon","albear","soliman","kanas","filly","hebesh"};
		HeapSort.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
