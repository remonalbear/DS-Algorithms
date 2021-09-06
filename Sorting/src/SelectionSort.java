
public class SelectionSort{

	private static void swap(Comparable[] arr,int i,int j)
	{
		Comparable temp=arr[j];
		arr[j]=arr[i];
		arr[i]=temp;
	}
	public static void  sort(Comparable[] arr)
	{
		assert !isSorted(arr);
		
		for(int i= 0 ; i < arr.length ; i++)
		{
			int min= i;
			for(int j= i; j < arr.length; j++)
			{
				if (less(arr[j],arr[i]))
				{
					min= j;
					swap(arr,i,min);
				}
				
			}
		}
	}
	
	private static boolean less(Comparable a,Comparable b )
	{
		return a.compareTo(b) <  0; 	
	}
	
	private static boolean isSorted(Comparable [] arr)
	{
		for(int i= 1 ;i < arr.length ; i++ )
		{
			if(less(arr[i],arr[i-1]))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String [] args)
	{
		Integer [] arr= {1,2,3,8,5,6,7,7};
		SelectionSort.sort(arr);
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
		
	}
}
