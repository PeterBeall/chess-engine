import java.util.ArrayList;

public class Piece {
	public String letter;
	public Color color;
	public Board board;
	public int lastMoveSpecialMove = -1;
	
	public Piece(Color color, String letter, Board board) {
		this.letter = letter;
		this.color = color;
		this.board = board;
	}
	public Piece(Color color, String letter) {
		this.letter = letter;
		this.color = color;
	}
	public Piece(String letter, Board board) {
		this.letter = letter;
		this.color = Color.NONE;
		this.board = board;
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {return false;};
	
	//given coordinates of a possible move, returns a list containing a coordinates and a pieces to set the coordinates to.
	public CoordinatePiece[] getResultingSpecialSet(int fromRank, int fromFile, int toRank, int toFile) {return new CoordinatePiece[0];}
	
	//return a piece with the same properties:
	public Piece copy(Board newBoard) {return new Piece(color, letter, newBoard);};
}