import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(Color color, Board board, IntPair pos) {
		super(color, "R", board, pos);
	}
	public Rook(Color color, Board board, int rank, int file) {
		super(color, "R", board, rank, file);
	}
	
	public Rook(Color color, Board board) {
		super(color, "R", board);
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
		return getBrqLegalMoves(new IntPair[]{new IntPair(1, 0), new IntPair(0, 1), new IntPair(-1, 0), new IntPair(0, -1)});
	}
	public ArrayList<IntPair> getLegalMoves(boolean checkCheck) {
		return getLegalMoves();
	}
	
	public Rook copy(Board newBoard) {
		return new Rook(color, newBoard, pos);
	}
}