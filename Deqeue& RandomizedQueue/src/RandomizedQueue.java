import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int N=0;
	// construct an empty randomized queue
    public RandomizedQueue()
    {
    	queue=(Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
    	return N == 0;
    }
    // return the number of items on the randomized queue
    public int size()
    {
    	return N;
    }
    //resizing the array
    private void resize(int size)
    {
    	Item[] newQueue=(Item[]) new Object[size];
    	for(int i= 0; i < N; i++)
    	{
    		newQueue[i]=queue[i];
    	}
    	queue=newQueue;
    }
    // add the item
    public void enqueue(Item item)
    {
    	if ( item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	if (N == queue.length)
    	{
    		resize(2 * queue.length);
    	}
    	queue[N]= item;
    	N++;
    		
    	
    }
    //swap the index by the last element in the array
    private void swap(int index)
    {
    	Item temp=queue[index];
    	queue[index]=queue[N-1];
    	queue[N-1]=temp;
    }
    // remove and return a random item
    public Item dequeue()
    {
    	if(N <1)
    	{
    		throw new NoSuchElementException();
    	}
    	Item removed=null;
    	if (N == 1) //corner case when one element left
    	{
        	removed= queue[0];
    	}
    	else
    	{
        	int randIndex= StdRandom.uniform(1,N+1);
        	swap(randIndex-1);
        	removed= queue[N-1];    		
    	}

    	N--;
    	queue[N]= null;
    	if (N>0 && N == queue.length / 4)
    	{
    		resize(queue.length/2);
    	}
    	
    	return removed;
    	
    }
    // return a random item (but do not remove it)
    public Item sample()
    {
    	if(N < 1)
    	{
    		throw new NoSuchElementException();
    	}
    	if (N == 1) //corner case when one element left
    	{
    		return queue[0];
    	}
    	int randIndex= StdRandom.uniform(1,N+1);
    	return queue[randIndex-1];
    	
    }
    public String toString()
    {
    	String result="";
    	int i=0;
    	while(i < N)
    	{
    		result =result + queue[i]+" ";
    		i++;
    	}
    	return result;
    }
    private class RandomIterator implements Iterator<Item>
    {
    	private int i=N;
    	private Item[] shuffled;
    	public RandomIterator()
    	{
    		shuffled=(Item[]) new Object[i];
    		for(int j=0;j<N ;j++)
    		{
    			shuffled[j]=queue[j];
    		}
    		StdRandom.shuffle(shuffled);
    	}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			
			return i > 0;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
	    	if(i < 1)
	    	{
	    		throw new NoSuchElementException();
	    	}
			return shuffled[--i];
		}
    	@Override
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    	
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
    	return new RandomIterator();
    }
    
    
    // unit testing (required)
    public static void main(String[] args)
    {
    	RandomizedQueue<Integer> q= new RandomizedQueue<Integer>();
    	q.enqueue(10);
    	q.enqueue(11);
    	q.enqueue(15);
    	q.enqueue(17);
	    for(int i =0;i< 1000000;i++)
	    {
	    	q.enqueue(i);
	
	    }
	    for(int i =0;i< 1000000;i++)
	    {
	    	System.out.println(q.dequeue());
	
	    }
    }
}
