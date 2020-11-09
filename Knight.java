public class Knight extends Piece {
	public Knight(boolean white, Board board) {
		super(white, "N", board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This simply moves 2 squares in one direction and 1 square in another.
		return (Math.abs(fromRank-toRank)==1 && Math.abs(fromFile-toFile)==2) || (Math.abs(fromRank-toRank)==2 && Math.abs(fromFile-toFile)==1);
	}
}