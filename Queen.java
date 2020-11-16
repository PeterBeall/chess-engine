import java.util.ArrayList;

public class Queen extends Piece {
	public Queen(Color color, Board board, IntPair pos) {
		super(color, "Q", board, pos);
	}
	public Queen(Color color, Board board, int rank, int file) {
		super(color, "Q", board, rank, file);
	}
	
	public Queen(Color color, Board board) {
		super(color, "Q", board);
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
		return getBrqLegalMoves(new IntPair[]{new IntPair(1, 1), new IntPair(1, -1), new IntPair(-1, 1), new IntPair(-1, -1), new IntPair(1, 0), new IntPair(0, 1), new IntPair(-1, 0), new IntPair(0, -1)});
	}
	
	public Queen copy(Board newBoard) {
		return new Queen(color, newBoard, pos);
	}
}