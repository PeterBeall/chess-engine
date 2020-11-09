import java.util.Scanner;

class ChessEngine {
	public static Piece[][] Board;
	public static int lastMoveDoublePawnFile = -1;
	
	public static void main(String[] args) {
		//The current position is stored in this array. Board[i][j] is the piece in rank i+1 and file j+1.
		Board = new Piece[][] {
			{new Rook(true), new Knight(true), new Bishop(true), new Queen(true), new King(true), new Bishop(true), new Knight(true), new Rook(true)},
			{new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
			{new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece()},
			{new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece()},
			{new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece()},
			{new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece(), new NoPiece()},
			{new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false)},
			{new Rook(false), new Knight(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Knight(false), new Rook(false)}};
		
		PrintBoard();
		
		//i'll use the terminal to enter moves:
		Scanner scanner = new Scanner(System.in);
		
		boolean whitesTurn = true;
		
		//move pieces:
		while (true) {//later i'll replace 'true' with a condition of nobody being in checkmate or stalemate
			int fromFile;
			int fromRank;
			int toFile;
			int toRank;
			
			do {
				if (whitesTurn) {
					System.out.print("White's turn. ");
				}else {
					System.out.println("Black's turn.");
				}
				System.out.println("Enter the file (a-h) and rank (1-8) of the piece to move and the position to move to.");
				
				fromFile = scanner.next().charAt(0)-97;
				fromRank = scanner.nextInt()-1;
				toFile = scanner.next().charAt(0)-97;
				toRank = scanner.nextInt()-1;
				
				//retry if move is illegal
			} while (!((whitesTurn == Board[fromRank][fromFile].isWhite) && !(Board[fromRank][fromFile].empty) && ((Board[toRank][toFile].empty) || (Board[fromRank][fromFile].isWhite != Board[toRank][toFile].isWhite)) && Board[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile) && 0 <= fromRank && fromRank < 8 && 0 <= fromFile && fromFile < 8));
			
			if (Board[fromRank][fromFile].letter != "_" || lastMoveDoublePawnFile != fromFile) {
				lastMoveDoublePawnFile = -1;
			}
			
			Board[toRank][toFile] = Board[fromRank][fromFile];
			Board[fromRank][fromFile] = new NoPiece();
			
			PrintBoard();
			
			whitesTurn = !whitesTurn;
		}
	}
	
	public static void PrintBoard() {
		/*I want to focus on chess rather than graphics, so I'll just print text to display the game.
		I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.*/
		
		//Print rows of pieces with labels. Since the first things to be printed are at the top, I'll go backwards so White can be at the bottom:
		System.out.println("r\\f a  b  c  d  e  f  g  h ");
		for (int i=Board.length-1; i>=0; i--) {
			String rankText = (i+1) + "| ";
			
			for (int j=0; j<Board[i].length; j++) {
				String pieceText = Board[i][j].letter;
				
				if (Board[i][j].empty) {
					pieceText = "[" + pieceText + "]";
				}else if (Board[i][j].isWhite) {
					pieceText = "(" + pieceText + ")";
				}else {
					pieceText = "{" + pieceText + "}";
				}
				
				rankText += pieceText;
			}
			
			System.out.println(rankText);
		}
	}
}