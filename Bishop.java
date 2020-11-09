public class Bishop extends Piece {
	public Bishop(boolean white) {
		super(white, "B");
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This moves diagonally (i.e. doesn't not move diagonally):
		if ((fromRank == toRank && fromFile == toFile) || !(Math.abs(fromRank-toRank) == Math.abs(fromFile-toFile))) {
			return false;
		}
		
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
						return false;
					}
				}
			}else {
				for (int j=lesserRank+1; greaterRank>j; j++) {
					if (!ChessEngine.Board[i][j].empty) {
						return false;
					}
				}
			}
		}
			
		return true;
	}
}