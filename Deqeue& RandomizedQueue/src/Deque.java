import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>  {

	private DequeNode head; 
	private DequeNode tail;
	private int size;
	private class DequeNode{
		Item data;
		DequeNode next;
		DequeNode prev;
	}
	   // construct an empty deque
    public Deque() {
    	head=null;
    	tail=null;
    	size=0;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
    	return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	DequeNode newItem= new DequeNode();
    	newItem.data= item;
    	newItem.next= head;
    	newItem.prev= null;
    	if (head != null) // corner case when only one element in the queue
    	{
    		head.prev=newItem;
    	}
    	head= newItem;
    	if (size == 0) //corner case when only one element in the queue
    	{
    		tail=newItem;
    	}
    	size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	DequeNode newItem= new DequeNode();
    	newItem.data= item;
    	newItem.next= null;
    	newItem.prev= tail;
    	if (tail != null) // corner case when only one element in the queue
    	{
    		tail.next= newItem;
    	}
    	
    	tail= newItem;
    	if (size == 0) // corner case when only one element in the queue
    	{
    		head=newItem;
    	}
    	size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
    	if (head == null )
    	{
    		throw new NoSuchElementException();
    	}
    	DequeNode oldItem= head;
    	head= oldItem.next;
    	if (size > 1)//corner case when only one element in the queue
    	{
    		head.prev= null;	
    	}
    	
    	//
    	oldItem.next=null;
    	size--;
    	if (size == 0) // corner case when only no element in the queue
    	{
    		tail=head;
    	}
    	return oldItem.data;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
    	if (tail == null )
    	{
    		throw new NoSuchElementException();
    	}
    	DequeNode oldItem= tail;
    	tail= oldItem.prev;	
    	if (size > 1)//corner case when only one element in the queue
    	{
    		tail.next=null;	
    	}
    	
    	//
    	oldItem.prev=null;
    	size--;
    	if (size == 0) // corner case when only no element in the queue
    	{
    		head=tail;
    	}
    	return oldItem.data;
    }
    
    public String toString() {
    	String data="";
    	DequeNode current=head;
    	
    	
    	while(current != null)
    	{
    		data=data+" data -> "+ current.data;
    		current=current.next;
    	}
    	return data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
    	return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>
    {
    	DequeNode current=head;

		@Override
		public boolean hasNext()
		{
			
			return current != null;
		}

		@Override
		public Item next()
		{
			if ( current == null)
			{
				throw new NoSuchElementException();
			}
			
			Item item= current.data;
			current=current.next;
			return item;
			
		}
		
		@Override public void remove() 
		{
			throw new UnsupportedOperationException();
		}
    	
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	Deque<Integer> q=new Deque<Integer>();
    	q.addFirst(10);
    	q.addLast(20);
    	q.addFirst(1);
    	q.addLast(2);
    	System.out.println(q.removeFirst());
    	System.out.println(q);
    	System.out.println(q.removeLast());
    	System.out.println(q);
    	System.out.println(q.removeLast());
    	System.out.println("q: "+q);
    	System.out.println(q.removeLast());
    	System.out.println("q: "+q);
    	q.addFirst(10);
    	q.addLast(20);
    	q.addFirst(1);
    	q.addLast(2);
    	for(Integer i:q )
    	{
    		System.out.println("data -> "+i);
    	}
    	
    	
    	
    }
}
