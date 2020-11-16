import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pawn extends Piece {
	public int direction = 1;
	
	public Pawn(Color color, Board board, IntPair pos) {
		super(color, "_", board, pos);
		
		//White pawns have to move towards the 8th rank. Black pawns have to move towards the 1st rank.
		if (color == Color.BLACK) {
			direction = -1;
		}
	}
	public Pawn(Color color, Board board, int rank, int file) {
		super(color, "_", board, rank, file);
		
		if (color == Color.BLACK) {
			direction = -1;
		}
	}
	
	public Pawn (Color color, Board board) {
		super(color, "_", board);
		
		//White pawns have to move towards the 8th rank. Black pawns have to move towards the 1st rank.
		if (color == Color.BLACK) {
			direction = -1;
		}
	}
	
	public boolean isLegalMove(IntPair to) {
		//If a pawn is moving from a file to the same file, then it should be moving and not capturing anything. It can move forward by 1 square, and also by 2 squares if it is on it's side's second row.
		if (pos.file == to.file && board.getPieceAt(to) == null && ((to.rank-pos.rank == direction*1) || ((to.rank-pos.rank) == direction*2 && board.getPieceAt(to.rank-direction*1, to.file) == null && ((color == Color.WHITE && pos.rank == 1) || (color == Color.BLACK && pos.rank == 6))))) {
			//If its moving forward by 2, we need to note that it did at this move in case en passant applies next move:
			if (to.rank-pos.rank == direction*2) {
				lastMoveSpecialMove = board.move;
			}
			
			return true;
		}
		
		//If the pawn is capturing, it should be either taking a piece directly or taking it en passant:
		return ((tryingToCapture(to) && (board.getPieceAt(to) != null)) || getResultingSpecialSet(to).size() > 0);
	}
	public boolean isLegalMove(int rank, int file) {
		return isLegalMove(new IntPair(rank, file));
	}
	
	//need to return the piece that is being taken en passant:
	public HashMap<IntPair, Piece> getResultingSpecialSet(IntPair to) {
		HashMap<IntPair, Piece> pieceToCapture = new HashMap<IntPair, Piece>();
		
		Piece capturing = board.getPieceAt(to.rank-direction, to.file);
		//en passant is possible if there is a pawn in front of the to square which is an enemy pawn that moved forward by 2 squares last move
		if (tryingToCapture(to) && capturing != null && (capturing.lastMoveSpecialMove == board.move-1 && board.getPieceAt(to.rank-direction, to.file).letter == letter && (board.getPieceAt(to.rank-direction, to.file).color != color))) {
			//the board needs to remove the captured pawn:
			pieceToCapture.put(capturing.pos, capturing);
		}
		
		//if this isn't en passant then the board doesn't need to set any additional pieces:
		return pieceToCapture;
	}
	
	//return true if to is one forward and one to either side of pos
	private boolean tryingToCapture(IntPair to) {
		return (pos.file == to.file + 1 || pos.file == to.file - 1) && (to.rank == pos.rank+direction);
	}
	
	public ArrayList<IntPair> getLegalMoves() {
		ArrayList<IntPair> legalMoves = new ArrayList<IntPair>();
		
		//just check all four possible ones:
		for (IntPair i : new IntPair[]{new IntPair(pos.rank+direction, pos.file), new IntPair(pos.rank+2*direction, pos.file), new IntPair(pos.rank+direction, pos.file+1), new IntPair(pos.rank+direction, pos.file-1)}) {
			if (board.isPairOnBoard(i) && isLegalMove(i)) {
				legalMoves.add(i);
			}
		}
		
		return legalMoves;
	}
	
	public Pawn copy(Board newBoard) {
		Pawn newPawn = new Pawn(color, newBoard, pos);
		newPawn.direction = direction;
		
		return newPawn;
	}
}