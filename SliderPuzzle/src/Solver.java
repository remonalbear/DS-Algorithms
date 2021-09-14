import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	private boolean solvable=false;
	private Node root;
	private static final Comparator<Node> manhattenCM= new byManhatten();
	private static final Comparator<Node> hammingCM= new byHamming();
	//private class Node for each board
	private class Node{

		Board board;
		int moves;
		Node prevNode;
		int manhattenCost;
		int hammingCost;
		
		public Node(Board board, int moves, Node prevNode) {
			this.board=board;
			this.moves=moves;
			this.prevNode=prevNode;
			this.manhattenCost=this.board.manhattan() + this.moves;
			this.hammingCost=this.board.hamming() + this.moves;
		}
		
	}
	// compare by manhatten cost
	private static class byManhatten implements Comparator<Node> {

		@Override
		public int compare(Solver.Node o1, Solver.Node o2) {
			// TODO Auto-generated method stub
			if (o1.manhattenCost > o2.manhattenCost) 		return 1;
			else if (o1.manhattenCost < o2.manhattenCost) 	return -1;
			else 											return 0;		
		}	
	}
	
	// compare by hamming cost
	private static class byHamming implements Comparator<Node> {

		@Override
		public int compare(Solver.Node o1, Solver.Node o2) {
			// TODO Auto-generated method stub
			if (o1.hammingCost > o2.hammingCost) 		return 1;
			else if (o1.hammingCost < o2.hammingCost) 	return -1;
			else 										return 0;		
		}	
	}
	  // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	if(initial == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	MinPQ<Node> pqMain=new MinPQ<Node>(manhattenCM);
    	MinPQ<Node> pqTwin=new MinPQ<Node>(manhattenCM);
    	root = new Node(initial, 0, null);
    	pqMain.insert(root);
    	pqTwin.insert(new Node(initial.twin(),0,null));
    	while(!pqMain.isEmpty() || !pqTwin.isEmpty()) {
    		Node nodeMain=pqMain.delMin();
    		Node nodeTwin=pqTwin.delMin();
    		if (nodeMain.board.isGoal()) {
    			solvable =true;
    			root=nodeMain;
    			break;
    		}
    		else if (nodeTwin.board.isGoal()) {
    			break;
    		}
    		else {
    			for(Board neighbor : nodeMain.board.neighbors()) {
    				if (nodeMain.prevNode == null) {
    					pqMain.insert(new Node(neighbor,nodeMain.moves + 1,nodeMain));
    				}
    				else if (!neighbor.equals(nodeMain.prevNode.board)) {
    					pqMain.insert(new Node(neighbor,nodeMain.moves + 1,nodeMain));
    				}
    			}
    			for(Board neighbor : nodeTwin.board.neighbors()) {
    				if(nodeTwin.prevNode == null) {
    					pqTwin.insert(new Node(neighbor,nodeTwin.moves + 1,nodeTwin));
    				}
    				else if (!neighbor.equals(nodeTwin.prevNode.board)) {
    					pqTwin.insert(new Node(neighbor,nodeTwin.moves + 1,nodeTwin));
    				}
    			}
    		}
    	}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	if (solvable) {
    		return root.moves; 
    	}
    	return -1;
    }
    
//    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
    	Stack<Board> path=new Stack<Board>();
    	if(!solvable) {
    		return null;
    	}
    	else {
    		Node temp=root;
    		while(temp.prevNode != null) {
    			path.push(temp.board);
    			temp=temp.prevNode;
    		}
    		path.push(temp.board);
    	}
    	return path;
    }

    // test client (see below) 
    public static void main(String[] args) {
    	int [][] pSolvable= {{0,1,3},{4,2,5},{7,8,6}};
    	int [][] pUnSolvable= {{1,2,3},{4,5,6},{8,7,0}};
    	Board boardSolvable=new Board(pSolvable);
    	Board boardUnSolvable=new Board(pUnSolvable);
    	Solver solver = new Solver(boardUnSolvable);
    	System.out.println(solver.moves());
    	if(solver.isSolvable()) {
	    	for(Board b : solver.solution()) {
	    		System.out.println(b);
	    	}
    	}
    	else {
    		System.out.println("Not Solvable");
    	}
    }
}
