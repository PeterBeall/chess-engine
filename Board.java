import java.util.ArrayList;

public class Board {
	public Piece[][] pieces;
	public int move = 0;//this increases by 1 every move. So, it is white's turn if and only if this is even.
	
	//empty 8x8 board:
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
	
	//if the given move is legal, move the piece and return true, otherwise return false.
	public boolean movePiece(int fromRank, int fromFile, int toRank, int toFile) {
		/*To be legal, all these must be true:
			-moving a piece of the color whose turn it is
			-not moving an empty piece
			-not capturing a piece of the same color
			-moving as the piece should; determined by .isLegalMove()
			-moving to a coordinate that is off the board
		*/
		if ((((move % 2 == 0) == (Color.WHITE == pieces[fromRank][fromFile].color)) && pieces[fromRank][fromFile].color != Color.NONE && (pieces[fromRank][fromFile].color != pieces[toRank][toFile].color) && pieces[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile) && 0 <= fromRank && fromRank < pieces.length && 0 <= fromFile && fromFile < pieces[0].length)) {
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
}