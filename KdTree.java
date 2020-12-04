
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;


public class KdTree {
	private int num;
	private TreeNode root;
	private Point2D min;
	private double dis;
	
	 private class TreeNode {
	        private final Point2D key;
	        private final RectHV rect;
	        private TreeNode leftdown;
	        private TreeNode rightup;
	        private final boolean isVertical;

	        TreeNode(Point2D point, RectHV rec, boolean vertical) {
	            key = point;
	            rect = rec;
	            isVertical = vertical;
	        }

	    }
	
	public KdTree()
	{
		// construct an empty set of points 
		num = 0;
		root = null;
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
		RectHV rect = new RectHV(0,0,1,1);
		root = push(root,p,true,rect);
	}
	
	private TreeNode push(TreeNode node, Point2D p, boolean vertical, RectHV rect)
	{
		if (node == null)
		{
			num ++;
			return new TreeNode(p,rect,vertical);
		}
		
		if (node.key.compareTo(p) == 0)
			return node;
		
		if (node.isVertical)
		{
			if (p.x() < node.key.x())
			{
				RectHV rectleft = new RectHV(rect.xmin(),rect.ymin(),node.key.x(),rect.ymax());
				node.leftdown = push(node.leftdown,p,false,rectleft);
			}
			else
			{
				RectHV rectright = new RectHV(node.key.x(),rect.ymin(),rect.xmax(),rect.ymax());
				node.rightup = push(node.rightup,p,false,rectright);
			}
		}
		else
		{
			if (p.y() < node.key.y())
			{
				RectHV rectdown = new RectHV(rect.xmin(),rect.ymin(),rect.xmax(),node.key.y());
				node.leftdown = push(node.leftdown,p,false,rectdown);
			}
			else
			{
				RectHV rectup = new RectHV(rect.xmin(),node.key.y(),rect.xmax(),rect.ymax());
				node.rightup = push(node.rightup,p,false,rectup);
			}
		}
		return node;
	}
	
	public boolean contains(Point2D p)            
	{
		if (p == null)
			throw new IllegalArgumentException();
		// does the set contain point p? 
		TreeNode current = root;
		while (current != null)
		{
			if (p.equals(current.key))
				return true;
			if (current.isVertical)
			{
				if (p.x() < current.key.x())
				{
					current = current.leftdown;
				}
				else
				{
					current = current.rightup;
				}
			}
			else
			{
				if (p.y() < current.key.y())
				{
					current = current.leftdown;
				}
				else
				{
					current = current.rightup;
				}
			}
		}
		
		return false;
	}
	
	public void draw()                         
	{
		draw(root);
	}
	
	private void draw(TreeNode node)
	{
		if (node == null) return;

        StdDraw.setPenRadius(0.005);
        if (node.isVertical) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.key.x(), node.rect.ymin(), node.key.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(node.rect.xmin(), node.key.y(), node.rect.xmax(), node.key.y());
        }

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLACK);
        node.key.draw();

        draw(node.rightup);
        draw(node.leftdown);
	}
	
	public Iterable<Point2D> range(RectHV rect)             
	{
		if (rect == null)
			throw new IllegalArgumentException();
		// all points that are inside the rectangle (or on the boundary)
		Queue<Point2D> queue = new Queue<>();
		range(root,rect,queue);
		return queue;
	}
	
	private void range(TreeNode node, RectHV rect, Queue<Point2D> queue)
	{
		if (node == null) return;
		if (rect.contains(node.key))
			queue.enqueue(node.key);
		if (node.leftdown != null && node.leftdown.rect.intersects(rect))
			range(node.leftdown,rect,queue);
		if (node.rightup != null && node.rightup.rect.intersects(rect))
			range(node.rightup,rect,queue);
	}
	
	public Point2D nearest(Point2D p)             
	{
		if (root == null)
			throw new IllegalArgumentException();
		if (p == null)
			throw new IllegalArgumentException();
		// a nearest neighbor in the set to point p; null if the set is empty
		min = null;
		dis = Double.MAX_VALUE;
		nearest(root, p);
		return min;
	}
	
	private void nearest(TreeNode node, Point2D p)
	{
		if (node.key.distanceTo(p) < dis)
		{
			min = node.key;
			dis = node.key.distanceTo(p);
		}
		
		TreeNode first = null;
        TreeNode second = null;
		
		if (node.isVertical)
		{
			if (p.x() < node.key.x())
			{
				first = node.leftdown;
                second = node.rightup;
			}
			else
			{
				first = node.rightup;
                second = node.leftdown;
			} 
		}
		else
		{
			if (p.y() < node.key.y()) {
                first = node.leftdown;
                second = node.rightup;
            } 
			else {
                first = node.rightup;
                second = node.leftdown;
            }
		}
		
		if (first != null && dis > first.rect.distanceSquaredTo(p)) {
			nearest(first, p);
        }
        if (second != null && dis > second.rect.distanceSquaredTo(p)) {
            nearest(second, p);
        }
	}

	public static void main(String[] args)
	{
		
	}

}

