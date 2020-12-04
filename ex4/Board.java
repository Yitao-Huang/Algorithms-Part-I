package ex4;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
    	if (tiles == null)
    		throw new IllegalArgumentException();
    	this.tiles = tiles;
    }
    
    private int[][] copyBlocks() {
        int[][] copy = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }
                                           
    // string representation of this board
    public String toString()
    {
    	 StringBuilder s = new StringBuilder();
         s.append(dimension() + "\n");
         for (int i = 0; i < dimension(); i++) {
             for (int j = 0; j < dimension(); j++) {
                 s.append(String.format("%2d ", tiles[i][j]));
             }
             s.append("\n");
         }
         return s.toString();		
    }

    // board dimension n
    public int dimension()
    {
    	return tiles.length;
    }

    // number of tiles out of place
    public int hamming()
    {
    	int sum = 0;
    	for (int i=0;i<dimension();i++)
    	{
    		for (int j=0;j<dimension();j++)
    		{
    			if (tiles[i][j] != 0 && tiles[i][j] != (i*dimension()+j+1))
    				sum++;
    		}
    	}
    	return sum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
    	int sum = 0;
    	for (int i=0;i<dimension();i++)
    	{
    		for (int j=0;j<dimension();j++)
    		{
    			if (tiles[i][j] != 0 && tiles[i][j] != (i*dimension()+j+1))
    			{
    				int row = (tiles[i][j]-1) / dimension();
    				int col = (tiles[i][j]-1) - row*dimension();
    				sum += Math.abs(row-i) + Math.abs(col-j);
    			}
    				
    		}
    	}
    	return sum;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
    	return manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
    	if (y == this)
    		return true;
    	if (y.getClass() != this.getClass())
    		return false;
    	if (y == null)
    		return false;
    	
    	Board board = (Board) y;
    	if (board.dimension() != dimension())
    		return false;
    	for (int i=0;i<dimension();i++)
    	{
    		for (int j=0;j<dimension();j++)
    		{
    			if (tiles[i][j] != board.tiles[i][j])
    				return false;
    		}
    	}
    	return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
    	 Stack<Board> stack = new Stack<>();
         int[] dx = {-1, 1, 0, 0};
         int[] dy = {0, 0, -1, 1};
         
         for (int i = 0; i < dimension(); i++) {
             for (int j = 0; j < dimension(); j++) {
                 if (tiles[i][j] == 0) {
                     for (int t = 0; t < dx.length; t++) {
                         int nx = i + dx[t];
                         int ny = j + dy[t];
                         if (nx >= 0 && nx < dimension() && ny >= 0 && ny < dimension()) {
                             int[][] tmpblock = copyBlocks();
                             int toMove = tiles[nx][ny];
                             tmpblock[nx][ny] = 0;
                             tmpblock[i][j] = toMove;
                             Board nboard = new Board(tmpblock);
                             stack.push(nboard);
                         }
                     }

                 }
             }
         }
         return stack;
    } 

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
    	int[][] twin = copyBlocks();
    	
    	if (twin[0][0] != 0 && twin[0][1] != 0) {
            int tmp = twin[0][0];
            twin[0][0] = twin[0][1];
            twin[0][1] = tmp;
        } else {
            int tmp = twin[1][0];
            twin[1][0] = twin[1][1];
            twin[1][1] = tmp;
        }

        Board twinboard = new Board(twin);
        return twinboard;
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {
    	
    }

}
