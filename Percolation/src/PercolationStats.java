
import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] means;  
    private int trials;
    private static final double CONFIDENCE_196=1.96;
    // perform independent trials on an n-by-n grid
      public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0 ){
            throw new IllegalArgumentException();
        }
        this.trials=trials;//intialize number of trials 
        means=new double [trials]; //intialize empty array of means
        for (int i = 0;i < trials; i++)
        { // iterate by trials number
            Percolation p=new Percolation(n); // new percolation object
            while(!p.percolates()){ // loop until percolates
                int randIndex = StdRandom.uniform(0,(n*n) - 1); //pick random number
                int row = (randIndex / n) + 1;
                int col = (randIndex % n) + 1;
                p.open(row,col); // open the site
            }
            means[i] = p.numberOfOpenSites()/(double)(n*n) ; // add the mean to the array
        }
      }

      // sample mean of percolation threshold
      public double mean()
      {
        return StdStats.mean(means);
      }
  
      // sample standard deviation of percolation threshold
      public double stddev()
      {
        return StdStats.stddev(means);
      }
  
      // low endpoint of 95% confidence interval
      public double confidenceLo()
      {
        double mean = mean();
        double std = stddev();
        double conf = mean - (CONFIDENCE_196 * Math.sqrt(std) / Math.sqrt(trials));
        return conf;
      }
  
      // high endpoint of 95% confidence interval
      public double confidenceHi()
      {
          double mean = mean();
          double std = stddev();
          double conf = mean + (CONFIDENCE_196 * Math.sqrt(std) / Math.sqrt(trials));
          return conf;
      }
  
     // test client (see below)
     public static void main(String[] args)
     {
       PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
       System.out.println("mean                    = "+ps.mean());
       System.out.println("stddev                  = "+ps.stddev());
       System.out.println("95% confidence interval = ["+ps.confidenceLo()+", "+ps.confidenceHi()+"]");
    }
}
