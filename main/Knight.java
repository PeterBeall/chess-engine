import java.util.ArrayList;

public class Knight extends Piece {
	public Knight(Color color, Board board, IntPair pos) {
		super(color, "N", board, pos);
	}
	public Knight(Color color, Board board, int rank, int file) {
		super(color, "N", board, rank, file);
	}
	
	public Knight(Color color, Board board) {
		super(color, "N", board);
	}
	
	public boolean isLegalMove(int rank, int file) {
		//This simply moves 2 squares in one direction and 1 square in another.
		return (Math.abs(pos.rank-rank)==1 && Math.abs(pos.file-file)==2) || (Math.abs(pos.rank-rank)==2 && Math.abs(pos.file-file)==1);
	}
	public boolean isLegalMove(IntPair to) {
	return isLegalMove(to.rank, to.file);
	}
	
	public ArrayList<IntPair> getLegalMoves() {
		ArrayList<IntPair> legalMoves = new ArrayList<IntPair>();
		
		//just check all eight possible ones:
		for (IntPair i : new IntPair[]{new IntPair(pos.rank+2, pos.file+1), new IntPair(pos.rank+2, pos.file-1), new IntPair(pos.rank+1, pos.file+2), new IntPair(pos.rank-1, pos.file+2), new IntPair(pos.rank-2, pos.file-1), new IntPair(pos.rank-2, pos.file+1), new IntPair(pos.rank-1, pos.file-2), new IntPair(pos.rank+1, pos.file-2)}) {
			if (board.isPairOnBoard(i)) {
				legalMoves.add(i);
			}
		}
		
		return legalMoves;
	}
	public ArrayList<IntPair> getLegalMoves(boolean checkCheck) {
		return getLegalMoves();
	}
	
	public Knight copy(Board newBoard) {
		return new Knight(color, newBoard, pos);
	}
}