import java.util.ArrayList;
import java.util.HashMap;

abstract class Piece implements PieceInterface {
	public String letter;
	public Color color;
	public Board board;
	public int lastMoveSpecialMove = -2;
	public IntPair pos;
	
	public Piece(Color color, String letter, Board board, IntPair pos) {
		this.letter = letter;
		this.color = color;
		this.board = board;
		this.pos = pos;
	}
	public Piece(Color color, String letter, Board board, int rank, int file) {
		this.letter = letter;
		this.color = color;
		this.board = board;
		this.pos = new IntPair(rank, file);
	}
	public Piece(Color color, String letter, Board board) {
		this.letter = letter;
		this.color = color;
		this.board = board;
	}
	public Piece(Color color, String letter) {
		this.letter = letter;
		this.color = color;
	}
	
	public HashMap<IntPair, Piece> getResultingSpecialSet(IntPair to) {return new HashMap<IntPair, Piece>();};
	
	//bishop, rook, and queen move similarly, so they'll all use this:
	public ArrayList<IntPair> getBrqLegalMoves(IntPair[] directions) {
		ArrayList<IntPair> legalMoves = new ArrayList<IntPair>();
		
		//look in each direction and add to the list of legal moves until an obstruction is reached:
		for (IntPair i : directions) {
			IntPair lookingAt = pos;
			
			do {
				lookingAt = new IntPair(lookingAt.rank + i.rank, lookingAt.file + i.file);
				
				if (board.getPieceAt(lookingAt) != null) {
					break;
				}
				
				legalMoves.add(legalMoves.size(), lookingAt);
			}while (board.isPairOnBoard(lookingAt));
			
			Piece capturing = board.getPieceAt(lookingAt);
			
			if (capturing == null || capturing.color != color) {
				legalMoves.add(legalMoves.size(), lookingAt);
			}
		}
		
		return legalMoves;
	}
}