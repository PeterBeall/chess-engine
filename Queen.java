public class Queen extends Piece {
	public Queen(boolean white) {
		super(white, "Q");
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This can move either like a bishop or like a rook.
		boolean likeBishop = true;
		//Bishops move diagonally (i.e. don't not move diagonally):
		if ((fromRank == toRank && fromFile == toFile) || !(Math.abs(fromRank-toRank) == Math.abs(fromFile-toFile))) {
			likeBishop = false;
		}else {
			//But, there can't be any pieces in the way:
			int greaterRank = fromRank;
			int lesserRank = toRank;
			if (fromRank < toRank) {
				greaterRank = toRank;
				lesserRank = fromRank;
			}
			
			int greaterFile = fromFile;
			int lesserFile = toFile;
			if (fromFile < toFile) {
				greaterFile = toFile;
				lesserFile = fromFile;
			}
			
			for (int i=greaterFile-1; lesserFile<i; i--) {
				if ((fromFile==greaterFile && fromRank==greaterRank) || (fromFile==lesserFile && fromRank==lesserRank)) {
					for (int j=greaterRank-1; lesserRank<j; j--) {
						if (!ChessEngine.Board[i][j].empty) {
							likeBishop = false;
						}
					}
				}else {
					for (int j=lesserRank+1; greaterRank>j; j++) {
						if (!ChessEngine.Board[i][j].empty) {
							likeBishop = false;
						}
					}
				}
			}
		}
		if (likeBishop) {
			return true;
		}
		
		//It didn't move like a bishop, so we consider the rook-like moves.
		
		//Rooks move like so; one of the coordinates changes and the other stays the same:
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
				if (!ChessEngine.Board[fromRank][i].empty) {
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
				if (!ChessEngine.Board[i][fromFile].empty) {
					return false;
				}
			}
		}
		
		return true;
	}
}