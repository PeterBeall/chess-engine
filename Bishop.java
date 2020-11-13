public class Bishop extends Piece {
	public Bishop(Color color, Board board) {
		super(color, "B", board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This moves diagonally (i.e. doesn't not move diagonally):
		if ((fromRank == toRank && fromFile == toFile) || !(Math.abs(fromRank-toRank) == Math.abs(fromFile-toFile))) {
			return false;
		}
		
		//But, there can't be any pieces in the way, so we check all the squares in between:
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
		
		boolean fromGreaterOrLesser = (fromFile==greaterFile && fromRank==greaterRank) || (fromFile==lesserFile && fromRank==lesserRank);
		
		int rankInc = -1;
		if (!fromGreaterOrLesser) {
			rankInc = 1;
		}
		int j = greaterRank+rankInc;
		if (!fromGreaterOrLesser) {
			j = lesserRank+rankInc;
		}
		
		for (int i=greaterFile-1; lesserFile<i; i--) {
			if (board.pieces[j][i].color != Color.NONE) {
				return false;
			}
			
			j += rankInc;
		}
		
		return true;
	}
	
	public Bishop copy(Board newBoard) {
		return new Bishop(color, newBoard);
	}
}