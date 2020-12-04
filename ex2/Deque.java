package ex2;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	private int num;
	private Node first;
	private Node last;
	
	private class Node{
		Item content;
		Node next;
		Node prev;
	}
	
    // construct an empty deque
    public Deque()
    {
    	num = 0;
    	first = new Node();
    	first = null;
    	
    	last = new Node();
    	last = null;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
    	return size() == 0;
    }

    // return the number of items on the deque
    public int size()
    {
    	return num;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
    	if (item == null)
    		throw new IllegalArgumentException();
    	
    	num++;
    	Node oldFirst = first;
    	Node newItem = new Node();
    	newItem.content = item;
    	
    	if (num == 1)
    	{
    		newItem.prev = null;
    		newItem.next = null;
    		last = newItem;
    	}
    	else
    	{
    		newItem.next = oldFirst;
    		newItem.next.prev = newItem;
    		newItem.prev = null;
    	}
    	
    	first = newItem;    	
    }

    // add the item to the back
    public void addLast(Item item)
    {
    	if (item == null)
    		throw new IllegalArgumentException();
    	
    	num++;
    	Node oldLast = last;
    	Node newItem = new Node();
    	newItem.content = item;
    	
    	if (num == 1)
    	{
    		newItem.prev = null;
    		newItem.next = null;
    		first = newItem;
    	}
    	else
    	{
    		newItem.prev = oldLast;
    		newItem.prev.next = newItem;
    		newItem.next = null;
    	}
    	
    	last = newItem;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
    	if (num == 0)
    		throw new NoSuchElementException();
    	
    	num--;
    	Node removedItem = new Node();
    	removedItem = first;
    	first = first.next;
    	
    	return removedItem.content;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
    	if (num == 0)
    		throw new NoSuchElementException();
    	
    	num--;
    	Node removedItem = new Node();
    	removedItem = last;
    	last = last.prev;
    	
    	return removedItem.content;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
    	return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>
    {
    	private Node current = first;
    	
    	public boolean hasNext()
    	{
    		return current != null;
    	}
    	
    	public void remove() 
    	{
    		throw new UnsupportedOperationException();
    	}
    	
    	public Item next()
    	{
    		if (!hasNext())
    			throw new NoSuchElementException();
    		Item item = current.content;
    		current = current.next;    		
    		return item;
    	}
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	Deque<Integer> d = new Deque<>();
		d.addFirst(1);
		d.addLast(2);
		
		Iterator<Integer> iterator = d.iterator();
		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		System.out.println(d.isEmpty());

		System.out.println(d.size());
    }
}