import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;

public class King extends Piece {
	public King(Color color, Board board, IntPair pos) {
		super(color, "K", board, pos);
	}
	public King(Color color, Board board, int rank, int file) {
		super(color, "K", board, rank, file);
	}
	
	public King(Color color, Board board) {
		super(color, "K", board);
	}
	
	public boolean isLegalMove(int rank, int file) {
		//This simply moves 1 square in one direction and 0 or 1 square in another.
		return (Math.abs(pos.rank-rank)==1 && Math.abs(pos.file-file)<2) || (Math.abs(pos.file-file)==1 && Math.abs(pos.rank-rank)<2);
	}
	public boolean isLegalMove(IntPair to) {
		return isLegalMove(to.rank, to.file);
	}
	
	//return whether it is in check (or possibly checkmate)
	public boolean isInCheck() {
		//check if the king is being attacked by at least one enemy piece:
		for (Piece piece : board.pieces) {
			//only consider enemy pieces:
			if (piece.color != color) {
				for (IntPair move : piece.getLegalMoves()) {
					if (move.rank == pos.rank && move.file == pos.file) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	//return whether it is in stalemate
	public boolean isInStalemate() {
		//if there does not exist a friendly piece with a move such that the king is not in check, and currently is not in check, then stalemate:
		if (!isInCheck()) {
			for (Piece piece : board.pieces) {
				if (piece.color == color) {
					for (IntPair move : piece.getLegalMoves()) {
						Board hypotheticalBoard = board.getHypotheticalBoard(piece, move);
						
						if (!hypotheticalBoard.getKingOfMove(color==Color.WHITE ? 0 : 1).isInCheck()) {
							return false;
						} 
					}
				}
			}
		}else {
			return false;
		}
		
		return true;
	}
	
	//return whether it is in checkmate
	public boolean isInCheckmate() {		
		//if there does not exist a friendly piece with a move such that the king is not in check, then checkmate:
		if (isInCheck()) {
			for (Piece piece : board.pieces) {
				if (piece.color == color) {
					for (IntPair move : piece.getLegalMoves()) {
						Board hypotheticalBoard = board.getHypotheticalBoard(piece, move);
						
						if (!hypotheticalBoard.getKingOfMove(color==Color.WHITE ? 0 : 1).isInCheck()) {
							return false;
						} 
					}
				}
			}
		}else {
			return false;
		}
		
		return true;
	}
	
	/*
	//returns a boolean array such that if getSafeSquares()[i][j] and the king is at (rank,file), then no enemy piece is attacking (rank+i-1,file+j-1) and it is possible to go there
	private boolean[][] getSafeSquares() {
		boolean[][] safeSquares = new boolean[][]{{true, true, true},
			{true, true, true},
			{true, true, true}};
		
		//the king cannot move to a square that isn't on the board, or is occupied by a teammate;
		for (int i=0; i<safeSquares.length; i++) {
			for (int j=0; j< safeSquares[i].length; j++) {
				Piece pieceHere = board.getPieceAt(pos.rank+i-1, pos.file+j-1);
				if ((!board.isPairOnBoard(pos.rank+i-1, pos.file+j-1)) || ((pieceHere != null && pieceHere.color == color) && (pieceHere != this))) {
					safeSquares[i][j] = false;
				}
			}
		}
		
		//the king cannot move to a square that is being attacked by at least one enemy piece:
		for (Piece piece : board.pieces) {
			//only consider enemy pieces:
			if (piece.color != color) {
				for (IntPair move : piece.getLegalMoves()) {
					for (int m=0; m<safeSquares.length; m++) {
						for (int n=0; n<safeSquares[m].length; n++) {
							if (move.rank == pos.rank+m-1 && move.file == pos.file+n-1) {
								safeSquares[m][n] = false;
							}
						}
					}
				}
			}
		}
		
		return safeSquares;
	}*/
	
	public ArrayList<IntPair> getLegalMoves() {
		ArrayList<IntPair> legalMoves = new ArrayList<IntPair>();
		
		for(int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (!(i == 1 && j == 1) && board.isPairOnBoard(pos.rank+i-1, pos.file+j-1)) {
					legalMoves.add(new IntPair(pos.rank+i-1, pos.file+j-1));
				}
			}
		}
		
		return legalMoves;
	}
	
	public King copy(Board newBoard) {
		return new King(color, newBoard, pos);
	}
}