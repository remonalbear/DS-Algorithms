import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class FastCollinearPoints {
	private LineSegment[] lines;
	private int num;
	
	public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
		
		if (points == null) {
			throw new IllegalArgumentException();
		}
		
		Point[] pointsData = Arrays.copyOf(points, points.length);
		
		for (int i = 0; i < pointsData.length; i++) { // check for null points
			if (pointsData[i] == null) {
				throw new IllegalArgumentException();
			}
		}
		
		for (int i = 0; i < pointsData.length; i++) { // check for duplicate points  
			for (int j = i+1; j < pointsData.length; j++) { 
				if(pointsData[i].compareTo(pointsData[j]) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		
		Arrays.sort(pointsData); // sort the points
		
		
		num = 0;
		LinkedStack<LineSegment> segments = new LinkedStack<LineSegment>();
		Point[] pointsCopy = Arrays.copyOf(pointsData, pointsData.length);
		for (int i = 0; i < pointsData.length; i++) {
			Point p = pointsData[i];
			Arrays.sort(pointsCopy);
			Arrays.sort(pointsCopy, p.slopeOrder());
			
			int count = 0;
			int maxCount = 0;
			int index = 0;
			for (int j = 2; j < pointsData.length; j++) {
				//count three or more consecutive points
				if (p.slopeTo(pointsCopy[j]) == p.slopeTo(pointsCopy[j-1])) {
					
					count++;
					index = j;

				}
				else { // we have to re check here with max count
					if (count > maxCount) {
						maxCount = count;
					}
					count = 0;			
					if (maxCount >= 2) {
						if (p.compareTo(pointsCopy[index-maxCount]) <= 0) {
							segments.push(new LineSegment(p,pointsCopy[index]));
							num++;
						}
						maxCount=0;
					}
				}
				// if p is the largest or the lowest -> done
			}
			if (count > maxCount) {
				maxCount = count;
			}
			if (maxCount >= 2) {
				if (p.compareTo(pointsCopy[index-maxCount]) <= 0) {
					segments.push(new LineSegment(p,pointsCopy[index]));
					num++;
				}
			}
			
			

			

		}
		
		   lines =new LineSegment[num];
		   for(int i = 0; i < num; i++ ) { // copy the segments
			   lines[i] = segments.pop();
		   }
	}
		
	public int numberOfSegments() {       // the number of line segments
		return num;
	}
	public LineSegment[] segments()  {
	   return Arrays.copyOf(lines, num);
	}
	
	public static void main(String [] args) {

		 // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
		
		
		

	}
}
