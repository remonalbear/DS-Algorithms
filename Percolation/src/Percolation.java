import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation
{
    private int [][] grid;
    final private int n;
    final private WeightedQuickUnionUF qf;
    private int openSitesCount=0;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        grid = new int[n][n]; // define the grid
        // initialize the grid with zeros
        for(int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                grid[i][j] = 0;
            }
        }
        qf = new WeightedQuickUnionUF((n*n)+2); // create the grid with upper snd lower virtual sites
       this.n = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if ((row < 1 || row > n) || (col < 1 || col > n))
        {
            throw new IllegalArgumentException();
        }
        if ( grid[row-1][col-1] == 0) {
        	 grid[row-1][col-1] = 1;
        	 openSitesCount++;
        }
        
        // union
        int index = ((row-1) * n) + col; // the index of the element
        // connect with left if possible
        if (col > 1 && isOpen(row, col - 1))
        {
            qf.union(index,index - 1 );
        }
        // connect with right if possible
        if (col < n && isOpen(row, col + 1))
        {
            qf.union(index,index + 1 );   
        }
        // connect with upper if possible
        if (row > 1 && isOpen(row - 1, col))
        {
            qf.union(index,index - n ); 
        }
        else if (row == 1) // connect with the virtual upper site 
        { 
            qf.union(0, index);
        }
        // connect with lower if possible
        if (row < n && isOpen(row + 1, col))
        {
            qf.union(index,index + n ); 
        }
        else if (row == n) // connect with the virtual lower site
        {
            qf.union((n*n) + 1, index);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if ((row <1 || row > n) || (col <1 || col > n))
        {
            throw new IllegalArgumentException();
        }
        if (grid[row-1][col-1] == 1){
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (( row <1 || row > n) || (col <1 || col > n)){
            throw new IllegalArgumentException();
        }
        if (!isOpen(row,col))
        {
            return false;
        }
        else
        {
            int index = ((row-1) * n) + col; // the index of the element
            if (qf.find(0) == qf.find(index)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {

        return this.openSitesCount;

    }

    // does the system percolate?
    public boolean percolates()
    {
        if (qf.find(0) == qf.find((n*n) + 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // test client (optional)
    public static void main(String[] args)
    {
        Percolation p = new Percolation(8);
        p.open(1,1);
        p.open(2,1);
        p.open(2,3);
        System.out.println("is full: "+p.isFull(2,3));
        p.open(2,2);
        System.out.println("is full: "+p.isFull(2,2));
        p.open(2,3);
        p.open(3,3);
        System.out.println("is full: "+p.isFull(3,3));
        p.open(3,4);
        p.open(4,4);
        p.open(4,5);
        p.open(5,5);
        System.out.println("is full: "+p.isFull(5,5));
        System.out.println("is full: "+p.isFull(5,7));
        p.open(5,6);
        p.open(6,6);
        p.open(6,7);
        p.open(7,7);
        p.open(7,8);
        p.open(8,8);
        System.out.println("percolates: "+p.percolates());
        System.out.println("count : "+p.numberOfOpenSites());



    }
}