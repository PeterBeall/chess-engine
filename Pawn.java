import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece {
	public int direction = 1;
	
	public Pawn(Color color, Board board) {
		super(color, "_", board);
		
		//White pawns have to move towards the 8th rank. Black pawns have to move towards the 1st rank.
		if (color == Color.BLACK) {
			direction = -1;
		}
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//If a pawn is moving from a file to the same file, then it should be moving and not capturing anything. It can move forward by 1 square, and also by 2 squares if it is on it's side's second row.
		if (fromFile == toFile && myBoard.pieces[toRank][toFile].color == Color.NONE && (((toRank-fromRank) == direction*1) || ((toRank-fromRank) == direction*2 && myBoard.pieces[toRank-direction*1][toFile].color == Color.NONE && ((color == Color.WHITE && fromRank == 1) || (color == Color.BLACK && fromRank == 6))))) {
			//If its moving forward by 2, we need to note that it did at this move in case en passant applies next move:
			if (toRank-fromRank == direction*2) {
				lastMoveSpecialMove = myBoard.move;
			}
			
			return true;
		}
		
		//If the pawn is capturing, it should be either taking a piece directly or taking it en passant:
		return ((tryingToCapture(fromFile, fromRank, toFile, toRank) && (myBoard.pieces[toRank][toFile].color != Color.NONE)) || getResultingSpecialSet(fromRank, fromFile, toRank, toFile).length > 0);
		
		//i need to add promotion
	}
	
	public CoordinatePiece[] getResultingSpecialSet(int fromRank, int fromFile, int toRank, int toFile) {
		//en passant is possible if there is a pawn in from of the (toRank, toFile) which is an enemy pawn that moved forward by 2 squares last move
		if (tryingToCapture(fromRank, fromFile, toRank, toFile) && (myBoard.pieces[toRank-direction][toFile].lastMoveSpecialMove == myBoard.move-1 && myBoard.pieces[toRank-direction][toFile].letter == letter && (myBoard.pieces[toRank-direction][toFile].color != color))) {
			//the board needs to remove the captured pawn:
			return new CoordinatePiece[]{new CoordinatePiece(new NoPiece(myBoard), toRank-direction, toFile)};
		}
		
		//if this isn't en passant then the board doesn't need to set any additional pieces:
		return new CoordinatePiece[0];
		
		/*
		if ((fromFile == toFile + 1 || fromFile == toFile - 1) && (toRank == fromRank+direction)) {
			if (myBoard.pieces[toRank][toFile].color == Color.NONE && (myBoard.pieces[toRank][toFile].color != color)) {
				//not the en passant case
				return new ArrayList<Object>(Arrays.asList(true));
			}else if (myBoard.pieces[toRank-direction][toFile].lastMoveSpecialMove == myBoard.move-1 && myBoard.pieces[toRank-direction][toFile].letter == letter && (myBoard.pieces[toRank-direction][toFile].color != color)) {
				//this is the en passant case
				ArrayList<Object> special = new ArrayList<Object>(Arrays.asList(
					toRank-direction, toFile, new NoPiece(myBoard), true
				));
				
				return special;
			}
		}
		//no special moves for non capturing pawns:
		return new ArrayList<Object>(Arrays.asList(false));
		*/
	}
	
	//return true if (toRank, toFile) is one forward and one to either side of (fromRank, fromFile)
	private boolean tryingToCapture(int fromRank, int fromFile, int toRank, int toFile) {
		return (fromFile == toFile + 1 || fromFile == toFile - 1) && (toRank == fromRank+direction);
	}
}