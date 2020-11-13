public class Queen extends Piece {
	//I'll just build on these to determine the Queen's legal moves:
	Bishop bishop;
	Rook rook;
	
	public Queen(Color color, Board board) {
		super(color, "Q", board);
		
		bishop = new Bishop(color, board);
		rook = new Rook(color, board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//the Queen can move like a bishop, or like a rook:
		return bishop.isLegalMove(fromRank, fromFile, toRank, toFile) || rook.isLegalMove(fromRank, fromFile, toRank, toFile);
	}
	
	public Queen copy(Board newBoard) {
		return new Queen(color, newBoard);
	}
}