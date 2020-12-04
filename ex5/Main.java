
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import ex3.FastCollinearPoints;
import ex3.Point;
import ex4.Board;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KdTree p = new KdTree();
//		p.insert(new Point2D(1.0,0.5));
//		p.insert(new Point2D(0.5,0.0));
//		p.insert(new Point2D(1.0,0.5));
//		p.insert(new Point2D(0.5,1.0));
//		p.insert(new Point2D(0.0,0.5));
//		RectHV r = new RectHV(0.75,0.25,1.0,0.75); 
//		System.out.println(p.range(r));
		p.nearest(new Point2D(0.0,0.0));
	}

}
