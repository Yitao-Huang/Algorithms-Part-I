package ex3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
	private int n;
	private ArrayList<LineSegment> seg;
	private int num;
	
   public FastCollinearPoints(Point[] points)
   {
	   if (points == null)
			throw new IllegalArgumentException();
	   
	   n = points.length;
	   
	   for (int a = 0; a < n; a++)
			if (points[a] == null)
				throw new IllegalArgumentException();
		
	   Point[] tPoints = points.clone();
       Arrays.sort(tPoints);
       for (int b = 0; b < n - 1; b++) {
       	if (tPoints[b].compareTo(tPoints[b+1]) == 0) 
       		throw new IllegalArgumentException();
       }
       
       num = 0;
       seg = new ArrayList<>();
		
	   for (int i=0;i<n;i++)
	   {
			Arrays.sort(tPoints,points[i].slopeOrder());
			int cur = 1;
			double rate = Double.NEGATIVE_INFINITY;
			
			int j;
			
			for (j = 1;j < n;j ++)
			{
				if (tPoints[0].slopeTo(tPoints[j]) != rate)
				{
					if (j-cur >= 3)
					{
						ArrayList<Point> re = new ArrayList<>();
						re.add(tPoints[0]);
						for (int k=cur;k<j;k++)
							re.add(tPoints[k]);
						Collections.sort(re);
						LineSegment newLine = new LineSegment(re.get(0),re.get(re.size()-1));
						if (re.get(0).compareTo(tPoints[0])==0)
						{
							seg.add(newLine);
							num++;
						}
					}
					cur = j;
					rate = tPoints[0].slopeTo(tPoints[j]);
				}						
			}
			if (j-cur >= 3)
			{
				ArrayList<Point> re = new ArrayList<>();
				re.add(tPoints[0]);
				for (int k=cur;k<j;k++)
					re.add(tPoints[k]);
				Collections.sort(re);
				LineSegment newLine = new LineSegment(re.get(0),re.get(re.size()-1));
				if (re.get(0).compareTo(tPoints[0])==0)
				{
					seg.add(newLine);
					num++;
				}				
			}
			
	   }
   }
   
   public int numberOfSegments()
   {
	   return num;
   }
   
   public LineSegment[] segments()                
   {
	   LineSegment[] segment = new LineSegment[seg.size()];
		for (int i=0;i<seg.size();i++)
			segment[i]=seg.get(i);
		return segment;
   }
}