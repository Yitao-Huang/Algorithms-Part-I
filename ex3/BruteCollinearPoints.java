package ex3;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private int n;
	private ArrayList<LineSegment> seg;
	private int num;
	
	public BruteCollinearPoints(Point[] points)
	{
		if (points == null)
			throw new IllegalArgumentException();
		
		n = points.length;
		
		for (int a = 0; a < n; a++)
		{
			if (points[a] == null)
				throw new IllegalArgumentException();
		}	
		
		Point[] tPoints = points.clone();
        Arrays.sort(tPoints);
        for (int b = 0; b < n - 1; b++) {
        	if (tPoints[b].compareTo(tPoints[b+1]) == 0) 
        		throw new IllegalArgumentException();
        }
        
        num = 0;
		seg = new ArrayList<>();
        
		for (int i=0;i<n-3;i++)
		{
			for (int j=i+1;j<n-2;j++)
			{
				for (int k=j+1;k<n-1;k++)
				{
					for (int m=k+1;m<n;m++)
					{
						if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
							&& points[j].slopeTo(points[k]) == points[k].slopeTo(points[m]))
						{
							Point max = points[i];
							Point min = points[i];
							if (points[j].compareTo(min) < 0)
								min = points[j];
							if (points[j].compareTo(max) > 0)
								max = points[j];
							if (points[k].compareTo(min) < 0)
								min = points[k];
							if (points[k].compareTo(max) > 0)
								max = points[k];
							if (points[m].compareTo(min) < 0)
								min = points[m];
							if (points[m].compareTo(max) > 0)
								max = points[m];
							seg.add(new LineSegment(max,min));
							num++;
						}
					}
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
