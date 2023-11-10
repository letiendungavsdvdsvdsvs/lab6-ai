package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				if (state[i].isConflict(state[j]))
					heuristic++;
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < N; i++) {
			Node temp = new Node(this.state);
			temp.state[i].move();
			result.add(temp);
		}
		return result;
	}

	public Node selectNextRandomCandidate() {
		Node re = new Node(state);
		Random r = new Random();
		int i = r.nextInt(N);
		int row = r.nextInt(N);
		re.state[i].setRow(row);
		return re;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public Node execute(Node initialState) {
		if(initialState.getH()==0) return initialState;
		Node current = initialState;
		Node neighbor = null;
		while(true) {
			neighbor = current.getBestCandidate();
			if(current.getH()>neighbor.getH()) {
				current = neighbor;
			}else {
				return current;
			}
		}
	}

	private Node getBestCandidate() {
		Node bestNode = null;
		List<Node> list = generateAllCandidates();
		Node n = list.get(0);
		for (Node node : list) {
			if(n.getH()>node.getH()) bestNode=node;
		}
		return bestNode;
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		// Enter your code here.
		return null;
	}

}