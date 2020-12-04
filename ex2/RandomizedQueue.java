package ex2;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int current;
	private int n;
	private int size;
	private Object[] queue;
	
	private void resize(int m)
	{
		Object[] newQueue = new Object[m];
		for (int i=0;i<size;i++)
		{
			newQueue[i] = queue[i];
		}
		queue = newQueue;
	}
	
    // construct an empty randomized queue
    public RandomizedQueue()
    {
    	n = 5;
    	size = 0;
    	queue = new Object[n];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
    	return size == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
    	return size;
    }

    // add the item
    public void enqueue(Item item)
    {
    	if (item == null)
    		throw new IllegalArgumentException();
    	
    	size++;
    	if (size >= n)
    	{
    		resize(2*n);
    		n *= 2;
    	}
    		
    	queue[size-1] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
    	if (size == 0)
    		throw new NoSuchElementException();
    	
    	size--;
    	if (size == 0)
    		return (Item) queue[0];
    	int i = StdRandom.uniform(size);
    	Item removedItem = (Item) queue[i];
    	for (int j = i;j < size; j++)
    	{
    		queue[j] = queue[j+1];
    	}
    	
    	if (size < n / 4)
    	{
    		resize(n/2);
    		n /= 2;
    	}
    		  	
    	return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
    	if (size == 0)
    		throw new NoSuchElementException();
    	
    	int i = StdRandom.uniform(size);
    	Item sampleItem = (Item) queue[i];
    	return sampleItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
    	return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>
    {   
    	int i;
    	Object[] array = new Object[size];
    		
    	public ArrayIterator()
    	{
    		for (int k=0;k<size;k++)
    			array[k] = queue[k];
    		StdRandom.shuffle(array);
    	}
        
    	public boolean hasNext()
    	{
    		return i < size;
    	}
    	
    	public void remove() 
    	{
    		throw new UnsupportedOperationException();
    	}
    	
    	public Item next()
    	{
    		if (!hasNext())
    			throw new NoSuchElementException();
    		Item nextItem = (Item) array[i];
    		i++;
    		return nextItem;
    	}
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	RandomizedQueue<Integer> r = new RandomizedQueue<>();
		r.enqueue(3);
		r.enqueue(4);
		r.enqueue(5);
		
		System.out.println(r.size());
		System.out.println(r.isEmpty());
		System.out.println(r.sample());

		System.out.println(r.dequeue());
		System.out.println(r.dequeue());
		
		Iterator<Integer> iterator = r.iterator();
		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
    }

}
