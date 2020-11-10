public class Queen extends Piece {
	//I'll build on these to determine the Queen's legal moves:
	Bishop bishop;
	Rook rook;
	
	public Queen(boolean white, Board board) {
		super(white, "Q", board);
		
		bishop = new Bishop(white, board);
		rook = new Rook(white, board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//the Queen can move like a bishop, or like a rook:
		return bishop.isLegalMove(fromRank, fromFile, toRank, toFile) || rook.isLegalMove(fromRank, fromFile, toRank, toFile);
	}
}