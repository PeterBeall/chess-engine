import java.util.ArrayList;
import java.util.HashMap;

public interface PieceInterface {
	//returns whether, as its kind of piece, it might be able to move there. ignores check
	public boolean isLegalMove(IntPair to);
	
	//returns list of possibly legal moves
	public ArrayList<IntPair> getLegalMoves();
	
	//given coordinates of a possible move, returns a list containing pieces to remove or to move to
	public HashMap<IntPair, Piece> getResultingSpecialSet(IntPair to);
	
	//return a piece with the same properties:
	public Piece copy(Board newBoard);
}