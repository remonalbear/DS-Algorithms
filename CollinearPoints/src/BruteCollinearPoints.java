import java.util.Arrays;
import edu.princeton.cs.algs4.LinkedStack;


public class BruteCollinearPoints {	
	private LineSegment[] lines;
	private int num;
	   
	public BruteCollinearPoints(Point[] points) {  // finds all line segments containing 4 points
		if (points == null) {
			throw new IllegalArgumentException();
		}
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		
		for (int i = 0; i < pointsCopy.length; i++) { // check for null points
			if (pointsCopy[i] == null) {
				throw new IllegalArgumentException();
			}			
		}
		for (int i = 0; i < pointsCopy.length; i++) { // check for duplicate points  
			for (int j = i+1; j < pointsCopy.length; j++) { 
				if (pointsCopy[i].compareTo(pointsCopy[j]) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		Arrays.sort(pointsCopy); // sort the points
		
		num = 0;
		LinkedStack<LineSegment> segments = new LinkedStack<LineSegment>();
		
		for (int i = 0; i < pointsCopy.length; i++) {
			for (int j = i+1 ; j < pointsCopy.length; j++) {
				double slope1 = pointsCopy[i].slopeTo(pointsCopy[j]);
				
				for (int k = j+1; k < pointsCopy.length; k++) {
					double slope2 = pointsCopy[i].slopeTo(pointsCopy[k]);
					
					for (int m = k+1; m < pointsCopy.length; m++) { 
						double slope3 = pointsCopy[i].slopeTo(pointsCopy[m]);
						
						if (slope1 == slope2 && slope2 == slope3) {
							num++;
							LineSegment l = new LineSegment(pointsCopy[i], pointsCopy[m]); // why m not k ?!!
							segments.push(l);
						}
					}
				}
			}
		}
		
		lines = new LineSegment[num]; // copy the segments found
		for (int i = 0; i < num; i++) {
		   lines[i] = segments.pop();
		}
	   
   }
   public  int numberOfSegments() {        // the number of line segments
	   return num;
   }
   public LineSegment[] segments() {
	   return Arrays.copyOf(lines, num);
   }
   
   public static void main(String[] args) {
	   int num = Integer.parseInt(args[0]);
	   Point [] points = new Point[num];
	   for (int i = 1; i < num * 2; i += 2) {
		   int x = Integer.parseInt(args[i]);
		   int y = Integer.parseInt(args[i+1]);
		   points[i/2] = new Point(x, y);
	   }
	   BruteCollinearPoints b = new BruteCollinearPoints(points);
	   System.out.println(b.numberOfSegments());
	   LineSegment[] segs = b.segments();
	   for (int i=0 ;i < segs.length; i++) {
		   System.out.println(segs[i]);
	   }

	   
   }
}
