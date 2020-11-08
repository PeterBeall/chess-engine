public class Piece {
	private boolean[][] Moving;//a 9x9 array. if the piece is at (4,4), then it's legal to move to (i,j) if Moving[i][j].
	private boolean[][] Capturing;//for pawns, capturing would be different from moving
	public String Letter;
	public boolean isWhite;
	public boolean noPiece = false;
	
	public Piece(String letter) {
		this.Letter = letter;
		this.noPiece = true;
	}
	public Piece(boolean[][] moving, boolean[][] capturing, String letter, boolean isWhite) {
		this.Moving = moving;
		this.Capturing = capturing;
		this.Letter = letter;
		this.isWhite = isWhite;
	}
	public Piece(boolean[][] moving, String letter, boolean isWhite) {
		this.Moving = moving;
		this.Capturing = moving;
		this.Letter = letter;
		this.isWhite = isWhite;
	}
	
	//if this piece is at (toRank, toFile), can it move to (fromRank, fromFile)?
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//bad coordinates are illegal:
		if (!(isOnBoard(toRank) && isOnBoard(toFile) && isOnBoard(fromRank) && isOnBoard(fromFile))) {
			return false;
		}
		
		return Moving[Math.abs(toRank-fromRank)+4][Math.abs(toFile-fromFile)+4];
	}
	
	private boolean isOnBoard(int coordinate) {
		return 0 <= coordinate && coordinate < 8;
	}
}