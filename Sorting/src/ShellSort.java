
public class ShellSort {
	
	public static void sort(Comparable [] arr) {
		int h = 1;
		int N = arr.length;
		while (h < N/3) {
			h=3*h+1; // get the max h
		}
		while (h >= 1) {
			for(int i = 1; i < N; i++) {
				for(int j = h; j >= h; j -= h) {
					if(less(arr,j,j-h)) {
						swap(arr,j,j-h);
					}
					else {
						break;
					}
				}
			}
			h = h/3; // update the sorting step 
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
		String []  arr= {"remon","albear","soliman","kanas","filly","hebesh"};
		InsertionSort.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
