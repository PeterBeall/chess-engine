public class Piece {
	public String letter;
	public boolean isWhite;
	public boolean empty = false;
	
	public Piece(boolean white, String letter) {
		this.letter = letter;
		this.isWhite = white;
	}
	public Piece(String letter) {
		this.letter = letter;
		this.empty = true;
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {return false;};
}