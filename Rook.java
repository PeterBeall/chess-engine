public class Rook extends Piece {
	public Rook(Color color, Board board) {
		super(color, "R", board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This moves like so; one of the coordinates changes and the other stays the same:
		if ((fromRank == toRank && fromFile == toFile) || (fromRank != toRank && fromFile != toFile)) {
			return false;
		}
		
		//But, there can't be any pieces in the way:
		int greater;
		int lesser;
		if (fromRank == toRank) {
			greater = fromFile;
			lesser = toFile;
			if (fromFile < toFile) {
				greater = toFile;
				lesser = fromFile;
			}
			
			for (int i=greater-1; lesser<i; i--) {
				System.out.println(i + "," + fromRank + "," + (myBoard.pieces[i][fromRank].color != Color.NONE));
				if (myBoard.pieces[fromRank][i].color != Color.NONE) {
					return false;
				}
			}
		}else {
			greater = fromRank;
			lesser = toRank;
			if (fromRank < toRank) {
				greater = toRank;
				lesser = fromRank;
			}
			
			for (int i=greater-1; lesser<i; i--) {
				if (myBoard.pieces[i][fromFile].color != Color.NONE) {
					return false;
				}
			}
		}
		
		/*for (int i=fromRank-toRank+Integer.signum(fromRank-toRank); Math.abs(i)>0; i+=Integer.signum(toRank-fromRank)) {
			if (!ChessEngine.Board[i][fromFile].empty) {
				return false;
			}
		}
		for (int i=fromFile-toFile+Integer.signum(fromFile-toFile); Math.abs(i)>0; i+=Integer.signum(toFile-fromFile)) {
			if (!ChessEngine.Board[fromRank][i].empty) {
				return false;
			}
		}*/
		
		return true;
	}
}