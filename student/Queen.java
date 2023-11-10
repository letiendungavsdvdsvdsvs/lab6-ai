package student;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		this.setRow(this.getRow()+1);
		if(getRow()>Node.N-1) this.setRow(0);
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		if(row==q.row || column==q.getColumn()) return true;
		if(Math.abs(row-q.getRow())==Math.abs(column-q.getColumn())) return true;
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}