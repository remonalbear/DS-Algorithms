
public class InsertionSort {

	public static void sort(Comparable [] arr) {
		
		for (int i = 1; i < arr.length; i++ ) {
			for (int j = i; j > 0; j--) {
				if (less(arr, j, j-1))  {
					swap(arr,j,j-1);
				}
				else {
					break;
				}
			}
		}
	}
	
	private static void swap(Comparable [] arr, int i, int j) {
		Comparable temp=arr[j];
		arr[j]=arr[i];
		arr[i]=temp;
	}
	
	private static boolean less(Comparable [] arr, int i, int j) {
		return arr[i].compareTo(arr[j]) < 0;
	}
	
	public static void main(String [] args) {
		String []  arr= {"remon","albear","filly","hebesh"};
		InsertionSort.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
