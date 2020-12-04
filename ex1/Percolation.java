package ex1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF p;
	private WeightedQuickUnionUF p2;
	private boolean[] o;
	private int num;
	private int len;
	private int top;
	private int bottom;
	
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
    	if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }
    	p = new WeightedQuickUnionUF(n*n + 2);
    	p2 = new WeightedQuickUnionUF(n*n + 1);
    	o = new boolean[n*n];
    	num = 0;
    	len = n;
    	top = n*n;
    	bottom = n*n + 1;
    	
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	if (checkIndex(row,col) == true)
    	{
    		if (isOpen(row,col))
    			return ;
    		
    		o[(row-1)*len+col-1] = true;
    		num++;
    		
    		if (row == 1)
    		{
    			p.union((row-1)*len+col-1, top);
    			p2.union((row-1)*len+col-1, top);
    		}
                
            if (row == len) 
            	p.union((row-1)*len+col-1, bottom);
    		
    		if (checkIndex(row,col-1) && isOpen(row, col-1)) // sequence!
    		{
    			p.union((row-1) * len + col - 1, (row-1) * len + col - 2);
    			p2.union((row-1) * len + col - 1, (row-1) * len + col - 2);
    		}
    		
    		if (checkIndex(row-1,col) && isOpen(row-1, col))
    		{
    			p.union((row-1) * len + col - 1, (row-2) * len + col - 1);
    			p2.union((row-1) * len + col - 1, (row-2) * len + col - 1);
    		}
    			
    		if (checkIndex(row+1,col) && isOpen(row+1, col))
    		{
    			p.union((row-1) * len + col - 1, row * len + col - 1);
    			p2.union((row-1) * len + col - 1, row * len + col - 1);
    		}

    		if (checkIndex(row,col+1) && isOpen(row, col+1))
    		{
    			p.union((row-1) * len + col - 1, (row-1) * len + col);
    			p2.union((row-1) * len + col - 1, (row-1) * len + col);
    		}
    			
    	}
    	else
    	{
    		throw new IllegalArgumentException();
    	}
    	
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	if (checkIndex(row,col) == false)
    	{
    		throw new IllegalArgumentException();
    	}
    	return o[(row-1)*len+col-1];
		
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
    	if (checkIndex(row,col) == true)
    	{
    		return p2.connected((row-1) * len + col - 1, top);
    	}
    	throw new IllegalArgumentException();
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
    	return num;
    }

    // does the system percolate?
    public boolean percolates()
    {
    	return p.connected(top, bottom);
    }
    
    private boolean checkIndex(int i, int j)
    {
        if (i < 1 || i > len || j < 1 || j > len) 
        	return false;
        return true;
    }
    public static void main(String[] args) {
    	
	}
}
