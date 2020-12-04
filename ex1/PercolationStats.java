package ex1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private int t;
	private double[] threshold;
	
	// perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
    	if (trials < 1 || n < 1)
    		throw new IllegalArgumentException();
    	
    	t = trials;
    	threshold = new double[t];
    	int count = 0;
    	
    	for (int i=0;i<t;i++)
    	{
    		Percolation p = new Percolation(n);
    		count = 0;
    		while (!p.percolates())
    		{
    			int rand1 = StdRandom.uniform(n);
    			int rand2 = StdRandom.uniform(n);
    			if (!p.isOpen(rand1+1, rand2+1))
    			{
    				p.open(rand1+1, rand2+1);
    			count++;
    			} // Very important! otherwise the threshold 
    			  //will be significantly lower 
    		}
    		threshold[i] = (double) count / (n*n);
    	}
    }

    // sample mean of percolation threshold
    public double mean()
    {
    	return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
    	return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
    	double t1 = t;
    	return (mean() - 1.96 * stddev() / Math.sqrt(t1));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
    	double t1 = t;
    	return (mean() + 1.96 * stddev() / Math.sqrt(t1));
    }
    
    public static void main(String[] args) {
		
	}
}
