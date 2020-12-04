
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;


public class PointSET {
	private int num;
	private SET<Point2D> set;
	
	public PointSET()
	{
		// construct an empty set of points 
		num = 0;
		set = new SET<>();
	}
	
	public boolean isEmpty()                      
	{
		// is the set empty? 
		return num == 0;
	}
	
	public int size()                         
	{
		// number of points in the set 
		return num;
	}
	
	public void insert(Point2D p)              
	{
		if (p == null)
			throw new IllegalArgumentException();
		// add the point to the set (if it is not already in the set)
		if (set.contains(p))
			return ;
		set.add(p);
		num ++;
	}
	
	public boolean contains(Point2D p)            
	{
		// does the set contain point p?
		if (p == null)
			throw new IllegalArgumentException();
		
		return set.contains(p);
	}
	public void draw()                         
	{
		if (set == null)
			throw new IllegalArgumentException();
		// draw all points to standard draw
		for (Point2D p : set)
			p.draw();
	}
	
	public Iterable<Point2D> range(RectHV rect)             
	{
		if (rect == null)
			throw new IllegalArgumentException();
		// all points that are inside the rectangle (or on the boundary)
		Stack<Point2D> stack = new Stack<>();
		for (Point2D p : set)
			if (rect.contains(p))
				stack.push(p);
		return stack;
	}
	
	public Point2D nearest(Point2D p)             
	{
		if (p == null)
			throw new IllegalArgumentException();
		// a nearest neighbor in the set to point p; null if the set is empty
		double dis = Double.MAX_VALUE;
		Point2D min = null;
		for (Point2D pt : set)
			if (p.distanceSquaredTo(pt) < dis)
			{
				dis = p.distanceSquaredTo(pt);
				min = pt;
			}
		return min;
	}

	public static void main(String[] args)
	{
		
	}

}
