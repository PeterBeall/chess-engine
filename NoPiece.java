public class NoPiece extends Piece {
	public NoPiece(Board board) {
		super(" ", board);
	}
	
	public NoPiece copy(Board newBoard) {
		return this;
	}
}