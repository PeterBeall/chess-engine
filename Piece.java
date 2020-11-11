import java.util.ArrayList;

public class Piece {
	public String letter;
	public Color color;
	public Board myBoard;
	public int lastMoveSpecialMove = -1;
	
	public Piece(Color color, String letter, Board board) {
		this.letter = letter;
		this.color = color;
		this.myBoard = board;
	}
	public Piece(Color color, String letter) {
		this.letter = letter;
		this.color = color;
	}
	public Piece(String letter, Board board) {
		this.letter = letter;
		this.color = Color.NONE;
		this.myBoard = board;
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {return false;};
	
	//given coordinates of a possible move, returns a list containing a coordinates and a pieces to set the coordinates to.
	public CoordinatePiece[] getResultingSpecialSet(int fromRank, int fromFile, int toRank, int toFile) {return new CoordinatePiece[0];}
}