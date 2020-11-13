public class Rook extends Piece {
	public Rook(Color color, Board board) {
		super(color, "R", board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This moves like so; one of the coordinates changes and the other stays the same:
		if ((fromRank == toRank && fromFile == toFile) || (fromRank != toRank && fromFile != toFile)) {
			return false;
		}
		
		//There can't be any pieces in the way, so we check all the squares in between:
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
				if (board.pieces[fromRank][i].color != Color.NONE) {
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
				if (board.pieces[i][fromFile].color != Color.NONE) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public Rook copy(Board newBoard) {
		return new Rook(color, newBoard);
	}
}