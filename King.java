import java.util.Arrays;

public class King extends Piece {
	public King(Color color, Board board) {
		super(color, "K", board);
	}
	
	public boolean isLegalMove(int fromRank, int fromFile, int toRank, int toFile) {
		//This simply moves 1 square in one direction and 0 or 1 square in another.
		return (Math.abs(fromRank-toRank)==1 && Math.abs(fromFile-toFile)<2) || (Math.abs(fromFile-toFile)==1 && Math.abs(fromRank-toRank)<2);
	}
	
	//if this king is located at (rank, file), return whether it is in check (or possibly checkmate)
	public boolean isInCheck(int rank, int file) {
		//its in check if its on an unnsafe square:
		return !getSafeSquares(rank, file)[1][1];
	}
	
	//if this king is located at (rank, file), return whether it is in stalemate
	public boolean isInStalemate(int rank, int file) {
		//it could be in stalemate only if its safe but has no other safe squares:
		if (Arrays.deepEquals(getSafeSquares(rank, file), new boolean[][]{{false, false, false},
			{false, true, false},
			{false, false, false}})) {
			//stalemate if no friendly piece can move:
			for (int i=0; i<board.pieces.length; i++) {
				for (int j=0; j<board.pieces[i].length; j++) {
					if (board.pieces[i][j].color == color && board.pieces[i][j] != this) {
						for (int m=0; m<board.pieces.length; m++) {
							for (int n=0; n<board.pieces[m].length; n++) {
								if (board.pieces[i][j].isLegalMove(i, j, m, n)) {
									return false;
								}
							}
						}
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	//if this king is located at (rank, file), return whether it is in checkmate
	public boolean isInCheckmate(int rank, int file) {
		//it could be in checkmate only if its safe but has no other safe squares:
		if (Arrays.deepEquals(getSafeSquares(rank, file), new boolean[][]{{false, false, false},
			{false, false, false},
			{false, false, false}})) {
			//checkmate if no friendly piece can save it:
			for (int i=0; i<board.pieces.length; i++) {
				for (int j=0; j<board.pieces[i].length; j++) {
					if (board.pieces[i][j].color == color && board.pieces[i][j] != this) {
						for (int m=0; m<board.pieces.length; m++) {
							for (int n=0; n<board.pieces[m].length; n++) {
								if (board.pieces[i][j] != this && board.pieces[m][n] != this && board.pieces[i][j].isLegalMove(i, j, m, n)) {
									//System.out.println(i+","+j+","+m+","+n+","+(board.pieces[i][j] != this)+","+board.pieces[i][j].letter);
									
									CoordinatePiece hypotheticalKing = board.getHypotheticalBoard(i, j, m, n).getKingOfMove(board.move);
									if (!((King)hypotheticalKing.piece).isInCheck(hypotheticalKing.pos.rank, hypotheticalKing.pos.file)) {
										return false;
									}
								}
							}
						}
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	//returns a boolean array such that if getSafeSquares()[i][j] and the king is at (rank,file), then no enemy piece is attacking (rank+i-1,file+j-1) and it is possible to go there
	private boolean[][] getSafeSquares(int rank, int file) {
		boolean[][] safeSquares = new boolean[][]{{true, true, true},
			{true, true, true},
			{true, true, true}};
		
		//the king cannot move to a square that isn't on the board, or is occupied by a teammate;
		for (int i=0; i<safeSquares.length; i++) {
			for (int j=0; j< safeSquares[i].length; j++) {
				if ((rank+i-1 >= board.pieces.length || file+j-1 >= board.pieces[0].length || rank+i-1 < 0 || file+j-1 < 0) || ((board.pieces[rank+i-1][file+j-1].color == color) && (board.pieces[rank+i-1][file+j-1] != this))) {
					safeSquares[i][j] = false;
				}
			}
		}
		
		//the king cannot move to a square that is being attacked by at least one enemy piece:
		for (int i=0; i<board.pieces.length; i++) {
			for (int j=0; j<board.pieces[i].length; j++) {
				//only consider enemy pieces:
				if (board.pieces[i][j].color != Color.NONE && board.pieces[i][j].color != color) {
					for (int m=0; m<safeSquares.length; m++) {
						for (int n=0; n<safeSquares[m].length; n++) {
							//set safe square false if its being attacked:
							if (safeSquares[m][n] && board.pieces[i][j].isLegalMove(i, j, rank+m-1, file+n-1)) {
								safeSquares[m][n] = false;
							}
						}
					}
				}
			}
		}
		
		return safeSquares;
	}
	
	public King copy(Board newBoard) {
		return new King(color, newBoard);
	}
}