
public class MaxPQ<Item extends Comparable<Item>> {
	private Item [] arr;
	private int N;
	public MaxPQ(int capacity) {
		arr = (Item[]) new Comparable[capacity+1];
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
				swap(k/2,k);
				k=k/2;
		}
	}
	
	private void sink(int k) {
	
		while(2*k <= N) {
			int j = k * 2; // get the first child
			if(j < N && less(j,j+1)) { // get the bigger child
				j++;
			}
			if(!less(k,j)) { // check if the parent is bigger than the bigger child
				break;
			}
			swap(k,j); // swap the smaller parent with the bigger child
			k=j; // update the node to be the bigger child
			
		}
		
	}
	
	public void insert(Item item) {
		arr[++N]=item;
	}
	
	public Item delMax() {
		Item max=arr[1];
		swap(1,N);
		N--;
		sink(1);
		arr[N+1]=null;
		return max;
	}
	
	private void swap(int i,int j)
	{
		Item temp=arr[j];
		arr[j]=arr[i];
		arr[i]=temp;
	}
	
	private boolean less(int i, int j )
	{
		return arr[i].compareTo(arr[j]) <  0; 	
	}
	
	public String toString() {
		String q="";
		for(int i = 1; i <= N; i++) {
			q=q+arr[i]+" ";
		}
		return q;
 	}
	
	public static void main(String [] args) {
		MaxPQ<String> pq=new MaxPQ<String>(2);
		pq.insert("remon");
		pq.insert("albear");
		System.out.println(pq);
		System.out.println(pq.delMax());
		System.out.println(pq);
	}
	
}
