public class CoordinatePiece {
	//a piece and a coordinate pair
	public Piece piece;
	public CoordinatePair pos;
	
	public CoordinatePiece(Piece piece, CoordinatePair pos) {
		this.piece = piece;
		this.pos = pos;
	}
	
	public CoordinatePiece(Piece piece, int rank, int file) {
		this.piece = piece;
		this.pos = new CoordinatePair(rank, file);
	}
}