public class King extends Piece {
	public King(boolean white) {
		super(white, "K");
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This simply moves 1 square in one direction and 0 or 1 square in another.
		return (Math.abs(fromRank-toRank)==1 && Math.abs(fromFile-toFile)<2) || (Math.abs(fromRank-toRank)==1 && Math.abs(fromFile-toFile)<2);
		
		//i need to make check illegal
		//If there exists an enemy piece which is attacking the square the king is trying to move too, then it's illegal.
	}
}