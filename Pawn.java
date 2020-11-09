public class Pawn extends Piece {
	public Pawn(boolean white) {
		super(white, "_");
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//White pawns have to move towards the 8th rank. Black pawns have to move towards the 1st rank.
		int direction = 1;
		if (!isWhite) {
			direction = -1;
		}
		
		//Since pawns move differently than they capture, we consider two cases.
		
		/*Case 1. We aren't capturing anything, just moving.
		A pawn can move forward 1 square.
		It's also an option to move forward 2 squares.*/
		if (fromFile == toFile && ChessEngine.Board[toRank][toFile].empty && (((toRank-fromRank) == direction*1) || ((toRank-fromRank) == direction*2 && ChessEngine.Board[toRank-direction*1][toFile].empty))) {
			//If its moving forward by 2, we need to note its file in case en passant applies next move.
			if (toRank-fromRank == direction*2) {
				ChessEngine.lastMoveDoublePawnFile = fromFile;
			}
			
			return true;
		}
		
		/*Case 2. We are capturing by moving the pawn forward by 1 and to the side by 1.
		If the square we are moving too is empty, then we need to check if en passant applies.
		*/
		if ((fromFile == toFile + 1 || fromFile == toFile - 1) && (toRank == fromRank+direction)) {
			if (!ChessEngine.Board[toRank][toFile].empty && (ChessEngine.Board[toRank][toFile].isWhite == !isWhite)) {
				//not the en passant case
				return true;
			}else if (ChessEngine.lastMoveDoublePawnFile == toFile && ChessEngine.Board[toRank-direction][toFile].letter == letter && (ChessEngine.Board[toRank-direction][toFile].isWhite == !isWhite)) {
				//this is the en passant case
				
				//in the main method, isLegalMode() is only used once, so I think this is okay:
				ChessEngine.Board[toRank-direction][toFile] = new NoPiece();
				
				return true;
			}
		}
		
		//i need to add promotion
		
		return false;
	}
}