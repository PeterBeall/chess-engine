import java.util.ArrayList;

public class Bishop extends Piece {
	public Bishop(Color color, Board board, IntPair pos) {
		super(color, "B", board, pos);
	}
	public Bishop(Color color, Board board, int rank, int file) {
		super(color, "B", board, rank, file);
	}
	
	public Bishop(Color color, Board board) {
		super(color, "B", board);
	}
	
	public boolean isLegalMove(int rank, int file) {
		for (IntPair move : getLegalMoves()) {
			if (move.rank == rank && move.file == file) {
				return true;
			}
		}
		
		return false;
	}
	public boolean isLegalMove(IntPair to) {
		return isLegalMove(to.rank, to.file);
	}
	
	public ArrayList<IntPair> getLegalMoves() {
		return getBrqLegalMoves(new IntPair[]{new IntPair(1, 1), new IntPair(1, -1), new IntPair(-1, 1), new IntPair(-1, -1)});
	}
	
	public Bishop copy(Board newBoard) {
		return new Bishop(color, newBoard, pos);
	}
}