import java.util.ArrayList;

public class Piece {
	public String letter;
	public boolean isWhite;
	public boolean empty = false;
	public Board myBoard;
	public int lastMoveSpecialMove = -1;
	
	public Piece(boolean white, String letter, Board board) {
		this.letter = letter;
		this.isWhite = white;
		this.myBoard = board;
	}
	public Piece(boolean white, String letter) {
		this.letter = letter;
		this.isWhite = white;
	}
	public Piece(String letter, Board board) {
		this.letter = letter;
		this.empty = true;
		this.myBoard = board;
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {return false;};
	
	//given coordinates of a possible move, returns a list containing a coordinates and a pieces to set the coordinates to.
	public ArrayList<Object> getResultingSpecialSet(int fromRank, int fromFile, int toRank, int toFile) {return new ArrayList<Object>();}
}