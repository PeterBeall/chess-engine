import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Board {
	public HashSet<Piece> pieces;
	public int move = 0;//this increases by 1 every move. So, it is white's turn if and only if this is even.
	
	//empty board:
	public Board() {
		pieces = new HashSet<Piece>();
	}
	
	//use given pieces:
	public Board(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
	
	//set the pieces:
	public void setPieces(HashSet<Piece> pieces) {
		this.pieces = pieces;
	}
	public void setPieces(Piece[][] pieces) {
		HashSet<Piece> setOfPieces = new HashSet<Piece>();
		
		for (int i=0; i<pieces.length; i++) {
			for (int j=0; j<pieces[i].length; j++) {
				pieces[i][j].pos = new IntPair(i, j);
				setOfPieces.add(pieces[i][j]);
			}
		}
		
		setPieces(setOfPieces);
	}
	
	//if the given move is legal, move the piece and return true, otherwise return false. Or ignore legality if not checkIfLegal
	public boolean movePiece(Piece piece, IntPair to, boolean checkCheck) {
		/*To be legal, all these must be true:
			-moving a piece of the color whose turn it is
			-not moving an empty piece
			-not capturing a piece of the same color
			-moving as the piece should; determined by .isLegalMove()
			-moving to a coordinate that is on the board
			-not putting the friendly king in check
		*/
		
		Piece capturing = getPieceAt(to);
		
		if (((move % 2 == 0) == (Color.WHITE == piece.color)) && (capturing == null || (piece.color != capturing.color)) && piece.isLegalMove(to) && isPairOnBoard(to)) {
			//check if the friendly king isn't being put in check:
			if (checkCheck) {
				Board hypotheticalBoard = this.getHypotheticalBoard(piece, to);
				
				King king = hypotheticalBoard.getKingOfMove(move);
				
				if (king != null && king.isInCheck()) {
					return false;
				}
			}
			
			//if the move involves castling, en passant, etc, then coordinates and pieces to set to have those coordinates to will be in this list:
			HashMap<IntPair, Piece> special = piece.getResultingSpecialSet(to);
			
			for (Map.Entry i : special.entrySet()) {
				Piece specialPiece = (Piece)i.getValue();
				IntPair position = (IntPair)i.getKey();
				
				if (specialPiece.pos == position) {//i'll indicate removal by having the key equal to the position
					pieces.remove(specialPiece);
				}else {
					specialPiece.pos = position;
				}
			}
			
			//remove captured piece:
			if (capturing != null) {
				pieces.remove(capturing);
			}
			
			//move the piece that's being moved:
			piece.pos = to;
			
			move += 1;
			
			return true;
		}
		
		//move illegal, do nothing:
		return false;
	}
	public boolean movePiece(Piece piece, IntPair to) {
		return movePiece(piece, to, true);
	}
	public boolean movePiece(IntPair from, IntPair to, boolean checkCheck) {
		return movePiece(getPieceAt(from), to, checkCheck);
	}
	public boolean movePiece(IntPair from, IntPair to) {
		return movePiece(getPieceAt(from), to, true);
	}
	
	//returns the piece at the given coordinates:
	public Piece getPieceAt(int rank, int file) {
		for (Piece i : pieces) {
			if (i.pos.rank == rank && i.pos.file == file) {
				return i;
			}
		}
		
		return null;
	}
	public Piece getPieceAt(IntPair pos) {
		return getPieceAt(pos.rank, pos.file);
	}
	
	//returns whether the game shouldn't continue:
	public boolean isGameOver() {
		King king = getKingOfMove(move);
		
		//game is over in checkmate or stalemate:
		return king.isInCheckmate() || king.isInStalemate();
	}
	
	//returns the king and position of the color whose turn it would be on the given move
	public King getKingOfMove(int kingsMove) {
		//we'll get the king whose turn it is:
		Color lookingFor = (kingsMove % 2 == 0) ? Color.WHITE : Color.BLACK;
		
		for (Piece piece : pieces) {
			if (piece.color == lookingFor && piece.letter == "K") {
				return (King)piece;
			}
		}
		
		return null;
	}
	
	//returns the board that this would be if the given move would be made
	public Board getHypotheticalBoard(Piece piece, IntPair to) {
		Board hypotheticalBoard = new Board();
		Piece hypotheticalPiece = piece;
		for (Piece origialPiece : pieces) {
			Piece copyPiece = origialPiece.copy(hypotheticalBoard);
			
			hypotheticalBoard.pieces.add(copyPiece);
			
			if (piece.pos == copyPiece.pos) {//will need to use the piece thats going to be moved:
				hypotheticalPiece = copyPiece;
			}
		}
		hypotheticalBoard.move = move;
		
		hypotheticalBoard.movePiece(hypotheticalPiece, to, false);
		
		return hypotheticalBoard;
	}
	
	public boolean isPairOnBoard(int rank, int file) {
		return (0 <= rank && rank < 8 && 0 <= file && file < 8);
	}
	public boolean isPairOnBoard(IntPair pair) {
		return isPairOnBoard(pair.rank, pair.file);
	}
}