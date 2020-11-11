import java.util.ArrayList;

public class Board {
	public Piece[][] pieces;
	public int move = 0;
	
	//contructor with no paramaters makes empty 8x8 board:
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
	
	//contructor with pieces and coordinates puts them on an empty 8x8 board:
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
	
	//constructor using given piece arrangement:
	public Board(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	//set the piece arrangement to a given one:
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	//set a given coordinate to have the given pieece:
	public boolean setPiece(int r, int f, Piece piece) {
		//we should only do this on coordinates that are on the board:
		if (0 <= r && r < pieces.length && 0 <= f && f < pieces[0].length) {
			pieces[r][f] = piece;
			return true;
		}
		return false;
	}
	
	public boolean movePiece(int fromRank, int fromFile, int toRank, int toFile) {
		if (pieces[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile)) {
			//if the move involves, castling, en passant, etc, then some coordinates to be set to pieces will be in this list:
			ArrayList<Object> special = pieces[fromRank][fromFile].getResultingSpecialSet(fromRank, fromFile, toRank, toFile);
			
			for (int i=0; i<special.size()-2; i+=3) {
				setPiece((int)special.get(i), (int)special.get(i+1), (Piece)special.get(i+2));
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