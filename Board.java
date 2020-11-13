import java.util.ArrayList;

public class Board {
	public Piece[][] pieces;
	public int move = 0;//this increases by 1 every move. So, it is white's turn if and only if this is even.
	
	//8x8 board with kings:
	public Board() {
		pieces = new Piece[8][8];
		
		NoPiece emptySquare = new NoPiece(this);
		
		//fill board with empty spots:
		for (int i=0; i<pieces.length; i++) {
			for (int j=0; j<pieces[i].length; j++) {
				pieces[i][j] = emptySquare;
			}
		}
	}
	
	//place given pieces on an empty 8x8 board:
	public Board(CoordinatePiece[] toAddPieces) {
		pieces = new Piece[8][8];
		//create empty board:
		for (int i=0; i<pieces.length; i++) {
			for (int j=0; j<pieces[i].length; j++) {
				pieces[i][j] = new NoPiece(this);
			}
		}
		
		//put given pieces onto the board:
		for (int i=0; i<toAddPieces.length; i++) {
			pieces[toAddPieces[i].pos.rank][toAddPieces[i].pos.file] = toAddPieces[i].piece;
		}
	}
	
	//use given piece arrangement:
	public Board(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	//set the piece arrangement:
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	//set a given coordinate to have the given pieece:
	public void setPiece(int r, int f, Piece piece) {
		pieces[r][f] = piece;
	}
	public void setPiece(CoordinatePiece coordinatePiece) {
		setPiece(coordinatePiece.pos.rank, coordinatePiece.pos.file, coordinatePiece.piece);
	}
	
	//if the given move is legal, move the piece and return true, otherwise return false. Or ignore legality of not checkIfLegal
	public boolean movePiece(int fromRank, int fromFile, int toRank, int toFile, boolean checkIfLegal) {
		/*To be legal, all these must be true:
			-moving a piece of the color whose turn it is
			-not moving an empty piece
			-not capturing a piece of the same color
			-moving as the piece should; determined by .isLegalMove()
			-moving to a coordinate that is on the board
			-not putting the friendly king in check
		*/
		
		if ((!checkIfLegal) || (((move % 2 == 0) == (Color.WHITE == pieces[fromRank][fromFile].color)) && pieces[fromRank][fromFile].color != Color.NONE && (pieces[fromRank][fromFile].color != pieces[toRank][toFile].color) && pieces[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile) && 0 <= fromRank && fromRank < pieces.length && 0 <= fromFile && fromFile < pieces[0].length)) {
			//check if the friendly king isn't being put in check:
			if (checkIfLegal) {
				Board hypotheticalBoard = this.getHypotheticalBoard(fromRank, fromFile, toRank, toFile);
				
				CoordinatePiece king = hypotheticalBoard.getKingOfMove(move);
				
				if (king != null && (((King)king.piece).isInCheck(king.pos.rank, king.pos.file))) {
					return false;
				}
			}
			
			//if the move involves, castling, en passant, etc, then coordinates and pieces to set those coordinates to will be in this list:
			CoordinatePiece[] special = pieces[fromRank][fromFile].getResultingSpecialSet(fromRank, fromFile, toRank, toFile);
			for (int i=0; i<special.length; i++) {
				setPiece(special[i]);
			}
			
			//move the main piece thats being moved:
			setPiece(toRank, toFile, pieces[fromRank][fromFile]);
			setPiece(fromRank, fromFile, new NoPiece(this));
			
			move += 1;
			
			return true;
		}
		
		//move illegal, do nothing:
		return false;
	}
	public boolean movePiece(int fromRank, int fromFile, int toRank, int toFile) {
		return movePiece(fromRank, fromFile, toRank, toFile, true);
	}
	
	//returns whether the game shouldn't continue:
	public boolean isGameOver() {
		CoordinatePiece king = getKingOfMove(move);
		
		//game is over in checkmate or stalemate:
		return ((King)king.piece).isInCheckmate(king.pos.rank, king.pos.file) || ((King)king.piece).isInStalemate(king.pos.rank, king.pos.file);
	}
	
	//returns the king and position of the color whose turn it would be on the given move
	public CoordinatePiece getKingOfMove(int kingsMove) {
		//we'll get the king whose turn it is:
		Color turnColor = Color.BLACK;
		if (kingsMove % 2 == 0) {
			turnColor = Color.WHITE;
		}
		
		for (int i=0; i<pieces.length; i++) {
			for (int j=0; j<pieces[i].length; j++) {
				if (pieces[i][j] instanceof King) {
					if (pieces[i][j].color == turnColor) {
						return new CoordinatePiece(pieces[i][j], i, j);
					}
				}
			}
		}
		
		return null;
	}
	
	//returns the board that this would be if the given move would be made
	public Board getHypotheticalBoard(int fromRank, int fromFile, int toRank, int toFile) {
		Board hypotheticalBoard = new Board();
		for (int i=0; i<hypotheticalBoard.pieces.length; i++) {
			for (int j=0; j<hypotheticalBoard.pieces[i].length; j++) {
				hypotheticalBoard.pieces[i][j] = pieces[i][j].copy(hypotheticalBoard);
			}
		}
		hypotheticalBoard.move = move;
		
		hypotheticalBoard.movePiece(fromRank, fromFile, toRank, toFile, false);
		
		return hypotheticalBoard;
	}
}