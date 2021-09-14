import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private final int [][] tiles;
	private final int n;
	private int index1,index2;
	private int hammingVal,manhattenVal=0;
	private boolean goal=true;
	// create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    
	public Board(int[][] tiles) {
    	this.tiles = arrayCopy(tiles);
    	n = tiles.length;
    	index1 = StdRandom.uniform(0,(n*n) - 1);
    	index2 = StdRandom.uniform(0,(n*n) - 1);
    	while(tiles[index1 / n][index1 % n] == 0) {
    		index1 = StdRandom.uniform(0,(n*n) - 1);
    	}
    	while((tiles[index2 / n][index2 % n] == 0) || (index1 == index2)) {
    		index2 = StdRandom.uniform(0,(n*n) - 1);
    	}
    	// calculate hamming
    	for (int i = 0; i < n*n - 1; i++) {
    		if(tiles[i/n][i%n] != (i+1)) {
    			hammingVal++;
    		}
    	}
    	// calculate manhatten
    	for (int i = 0; i < n*n ; i++) {
			int row = i/n;
			int col = i%n;
			int val = tiles[row][col];
    		if (((i == (n*n - 1)) && (val == 0)) || (val == 0) ) { // corner cases
    			continue;
    		}
    		if (val != (i+1)) {

    			int trueRow = (val - 1)/n;
    			int trueCol = (val - 1)%n;
    			manhattenVal += Math.abs(row-trueRow)+Math.abs(col-trueCol);
    		}
    	}
    	
    	// is goal?
    	for (int i = 0; i < n*n - 1; i++) {
    		if (tiles[i/n][i%n] != (i+1)) {
    			goal=false;;
    		}
    	}

    }
                                           


    // board dimension n
    public int dimension() {
    	return n;
    }

    // number of tiles out of place
    public int hamming() {
    	return hammingVal;
    }
//
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {    	
    	return manhattenVal;
    }
//
    // is this board the goal board?
    public boolean isGoal() {
    	return goal;
    }
//
    // does this board equal y?
    public boolean equals(Object y) {
    	if(y == this) {
    		return true;
    	}
    	if(y == null) {
    		return false;
    	}
    	if(y.getClass() != this.getClass()) {
    		return false;
    	}
    	Board that= (Board) y;
    	if(this.n != that.n) {
    		return false;
    	}
    	return Arrays.deepEquals(this.tiles, that.tiles);
    
    }
//
//    // all neighboring boards
    public Iterable<Board> neighbors() {
    	Stack<Board> s=new Stack<Board>();
    	int index=n*n - 1; // Initialize with the last element 
    	// get the blank square
    	for (int i = 0; i < n*n ; i++) {
    		if (tiles[i / n][i % n] == 0) {
    			index = i;
    		}
    	}
    	
    	// get its row and col
    	int row = index/n;
    	int col = index%n;
    	// copy of the tiles array
    	int [] [] copy;
    	// check for the upper square
    	if (row > 0) {
    		copy=arrayCopy(tiles);
    		swap(copy,row,col,row-1,col);
    		s.push(new Board(copy));
    	}
    	// check for the lower square
    	if (row < n - 1) {
    		copy=arrayCopy(tiles);
    		swap(copy,row,col,row+1,col);
    		s.push(new Board(copy));
    	}
    	// check for the left square
    	if (col > 0) {
    		copy=arrayCopy(tiles);
    		swap(copy,row,col,row,col-1);
    		s.push(new Board(copy));
    	}
    	// check for the right square
    	if(col < n - 1) {
    		copy=arrayCopy(tiles);
    		swap(copy,row,col,row,col+1);
    		s.push(new Board(copy));
    	}
    	
    	return s; 
    }
    // helper method swap two entries in board 
    private void swap(int [] [] arr, int row1, int col1, int row2, int col2) {
    	int temp = arr[row1][col1];
    	arr[row1][col1] = arr[row2][col2];
    	arr[row2][col2] = temp;
    }
    // helper method to copy 2d array
    private int [][] arrayCopy(int [][] arr) {
    int [][] arrCopy=new int [arr.length][arr.length];	
    	for (int i = 0; i < arr.length; i++) {
        	for (int j = 0; j < arr.length; j++) {
        		arrCopy[i][j] = arr[i][j];
        	}
    	}
    	return arrCopy;
    }
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	int [][] arrCopy=arrayCopy(tiles);
//    	System.out.println("index : "+index1+" index2 : "+index2);
    	swap(arrCopy, index1 / n, index1 % n, index2 / n, index2 % n);
    	return new Board(arrCopy);
    }
    // string representation of this board
    public String toString() {
    	String output = "";
    	output = output+n;
    	for (int i = 0; i < n*n; i++) {
    		if (i%3 == 0) {
    			output = output+'\n';
    		}
    		output = output+tiles[i/n][i%n]+" ";
    	}
    	return output;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    	int [][] p1 = {{8,1,3}, {4,0,2}, {7,6,5}};
    	int [][] p2 = {{8,1,3}, {4,0,2}, {7,6,5}};
    	int [][] p3 = {{1,2,3}, {4,5,6}, {7,8,0}};
    	Board b1 = new Board(p1);
    	Board b2 = new Board(p2);
    	Board b3 = new Board(p3);
    	System.out.println(b1.isGoal());
    	System.out.println(b2.isGoal());
    	System.out.println(b3.isGoal());


    }
}
