import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
	
	//return whether it is in check (or possibly checkmate)
	public boolean isInCheck() {
		//check if the king is being attacked by at least one enemy piece:
		for (Piece piece : board.pieces) {
			//only consider enemy pieces:
			if (piece.color != color) {
				for (IntPair move : piece.getLegalMoves(false)) {
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
					for (IntPair move : piece.getLegalMoves(false)) {
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
					for (IntPair move : piece.getLegalMoves(false)) {
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
	
	public ArrayList<IntPair> getLegalMoves(boolean checkCheck) {
		ArrayList<IntPair> legalMoves = new ArrayList<IntPair>();
		
		for(int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (!(i == 1 && j == 1) && board.isPairOnBoard(pos.rank+i-1, pos.file+j-1)) {
					legalMoves.add(new IntPair(pos.rank+i-1, pos.file+j-1));
				}
			}
		}
		
		//castling:
		if (lastMove < 0 && (!checkCheck || !isInCheck())) {
			int homeRank = (color == Color.WHITE) ? 0 : 7;
			
			//king side:
			Piece kingRook = board.getPieceAt(homeRank, 7);
			if (kingRook != null && kingRook.lastMove < 0) {
				boolean movingThroughCheck = false;
				//can't move through check:
				if (board.getPieceAt(homeRank, 5) != null) {
					movingThroughCheck = true;
				}else if (checkCheck) {
					Board hypotheticalBoard = board.getHypotheticalBoard(this, new IntPair(homeRank, 5));
					
					King king = hypotheticalBoard.getKingOfMove((color == Color.WHITE) ? 0 : 1);
					
					if (king.isInCheck()) {
						movingThroughCheck = true;
					}
				}
				
				if (!movingThroughCheck) {
					legalMoves.add(new IntPair(homeRank, 6));
				}
			}
			
			//queen side:
			Piece queenRook = board.getPieceAt(homeRank, 0);
			if (queenRook != null && queenRook.lastMove < 0) {
				boolean movingThroughCheck = false;
				//can't move through check:
				for (int i=4; i>2; i--) {
					if (board.getPieceAt(homeRank, i) != null) {
						movingThroughCheck = true;
					}else if (checkCheck) {
						Board hypotheticalBoard = board.getHypotheticalBoard(this, new IntPair(homeRank, i));
						
						King king = hypotheticalBoard.getKingOfMove((color == Color.WHITE) ? 0 : 1);
						
						if (king.isInCheck()) {
							movingThroughCheck = true;
						}
					}
				}
				
				if (!movingThroughCheck) {
					legalMoves.add(new IntPair(homeRank, 2));
				}
			}
		}
		
		return legalMoves;
	}
	public ArrayList<IntPair> getLegalMoves() {
		return getLegalMoves(true);
	}
	
	//return the rook that is being castled with:
	public HashMap<IntPair, Piece> getResultingSpecialSet(IntPair to) {
		HashMap<IntPair, Piece> pieceToMove = new HashMap<IntPair, Piece>();
		
		int homeRank = (color == Color.WHITE) ? 0 : 7;
		
		if (to.rank == homeRank && lastMove < 0 && !isInCheck()) {
			//king side:
			if (to.file == 6) {
				Piece kingRook = board.getPieceAt(homeRank, 7);
				if (kingRook != null && kingRook.lastMove < 0) {
					pieceToMove.put(new IntPair(homeRank, 5), kingRook);
				}
			}
			
			//queen side:
			if (to.file == 2) {
				Piece queenRook = board.getPieceAt(homeRank, 0);
				if (queenRook != null && queenRook.lastMove < 0) {
					pieceToMove.put(new IntPair(homeRank, 3), queenRook);
				}
			}
		}
		
		//if not castling do nothing:
		return pieceToMove;
	}
	
	public King copy(Board newBoard) {
		return new King(color, newBoard, pos);
	}
}