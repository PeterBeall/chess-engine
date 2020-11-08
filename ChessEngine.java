class ChessEngine {
	public static void main(String[] args) {
		/*I'll store the current position in an array. Board[i][j] is the piece in rank i+1 and file j+1.
		
		Pieces will correspond to numbers as follows:
		0 - no piece
		1 - pawn
		2 - knight
		3 - bishop
		4 - rook
		5 - queen
		6 - king
		
		White's pieces will be these numbers, and black's pieces will be the negative versions of these numbers.
		
		Information affecting the legality castling and en passant are not reflected here; I'll fix it later.*/
		int[][] Board = {
			{4, 2, 3, 5, 6, 3, 2, 4},
			{1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0},
			{-1,-1,-1,-1,-1,-1,-1, -1},
			{-4,-2,-3,-5,-6,-3,-2,-4}};
		
		PrintBoard(Board);
	}
	
	public static void PrintBoard(int[][] board) {
		/*I want to focus on chess rather than graphics, so I'll just print text to display the game.
		I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.*/
		
		//This table will translate numbers representing pieces into letters:
		
		String[] pieceLetters = {" ", "_", "N", "B", "R", "Q", "K"};
		
		//Print rows of pieces. Since the first things to be printed are at the top, I'll go backwards so White can be at the bottom:
		for (int i=board.length-1; i>=0; i--) {
			String rankText = "";
			
			for (int j=0; j<board[i].length; j++) {
				String pieceText = pieceLetters[Math.abs(board[i][j])];
				
				if (board[i][j] < 0) {
					pieceText = "{" + pieceText + "}";
				}else if (board[i][j] > 0) {
					pieceText = "(" + pieceText + ")";
				}else {
					pieceText = "[" + pieceText + "]";
				}
				
				rankText += pieceText;
			}
			
			System.out.println(rankText);
		}
	}
}