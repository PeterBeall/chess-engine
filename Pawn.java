import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece {
	public int direction = 1;
	
	public Pawn(boolean white, Board board) {
		super(white, "_", board);
		
		//White pawns have to move towards the 8th rank. Black pawns have to move towards the 1st rank.
		if (!isWhite) {
			direction = -1;
		}
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//Since pawns move differently than they capture, we consider two cases.
		
		/*Case 1. We aren't capturing anything, just moving.
		A pawn can move forward 1 square.
		It's also an option to move forward 2 squares.*/
		if (fromFile == toFile && myBoard.pieces[toRank][toFile].empty && (((toRank-fromRank) == direction*1) || ((toRank-fromRank) == direction*2 && myBoard.pieces[toRank-direction*1][toFile].empty))) {
			//If its moving forward by 2, we need to note its file in case en passant applies next move.
			if (toRank-fromRank == direction*2) {
				lastMoveSpecialMove = myBoard.move;
				//ChessEngine.lastMoveDoublePawnFile = fromFile;
			}
			
			return true;
		}
		
		/*Case 2. We are capturing by moving the pawn forward by 1 and to the side by 1.
		If the square we are moving to is empty, then we need to check if en passant applies.
		*/
		ArrayList<Object> special = getResultingSpecialSet(fromRank, fromFile, toRank, toFile);
		
		return (special.size() > 2) || (special.get(0).equals(true));
		
		//i need to add promotion
		
		//return false;
	}
	
	public ArrayList<Object> getResultingSpecialSet(int fromRank, int fromFile, int toRank, int toFile) {
		if ((fromFile == toFile + 1 || fromFile == toFile - 1) && (toRank == fromRank+direction)) {
			if (!myBoard.pieces[toRank][toFile].empty && (myBoard.pieces[toRank][toFile].isWhite == !isWhite)) {
				//not the en passant case
				return new ArrayList<Object>(Arrays.asList(true));
			}else if (myBoard.pieces[toRank-direction][toFile].lastMoveSpecialMove == myBoard.move-1 && myBoard.pieces[toRank-direction][toFile].letter == letter && (myBoard.pieces[toRank-direction][toFile].isWhite == !isWhite)) {
				//this is the en passant case
				ArrayList<Object> special = new ArrayList<Object>(Arrays.asList(
					toRank-direction, toFile, new NoPiece(myBoard), true
				));
				
				return special;
			}
		}
		//no special moves for non capturing pawns:
		return new ArrayList<Object>(Arrays.asList(false));
	}
}