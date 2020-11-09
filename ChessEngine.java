import java.util.Scanner;
import java.util.ArrayList;

class ChessEngine {
	public static void main(String[] args) {
		//A Board object will store the pieces in their positions. Board.pieces[i][j] is the piece in rank i+1 and file j+1.
		Board TheBoard = new Board();
		TheBoard.setPieces(new Piece[][] {
			{new Rook(true, TheBoard), new Knight(true, TheBoard), new Bishop(true, TheBoard), new Queen(true, TheBoard), new King(true, TheBoard), new Bishop(true, TheBoard), new Knight(true, TheBoard), new Rook(true, TheBoard)},
			{new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard), new Pawn(true, TheBoard)},
			{new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard)},
			{new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard)},
			{new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard)},
			{new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard), new NoPiece(TheBoard)},
			{new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard), new Pawn(false, TheBoard)},
			{new Rook(false, TheBoard), new Knight(false, TheBoard), new Bishop(false, TheBoard), new Queen(false, TheBoard), new King(false, TheBoard), new Bishop(false, TheBoard), new Knight(false, TheBoard), new Rook(false, TheBoard)}});
		
		PrintBoard(TheBoard);
		
		//i'll use the terminal to enter moves:
		Scanner scanner = new Scanner(System.in);
		
		//boolean whitesTurn = true;
		
		//move pieces:
		while (true) {//later i'll replace 'true' with a condition of nobody being in checkmate or stalemate
			int fromFile;
			int fromRank;
			int toFile;
			int toRank;
			
			do {
				//TheBoad.move is an integer that should increase by 1 every move.
				if (TheBoard.move % 2 == 0) {
					System.out.print("White's turn. ");
				}else {
					System.out.println("Black's turn.");
				}
				System.out.println("Enter the file (a-h) and rank (1-8) of the piece to move and the position to move to.");
				
				fromFile = scanner.next().charAt(0)-97;//'a'->0, 'b'->1, etc, since everywhere else files are integers.
				fromRank = scanner.nextInt()-1;
				toFile = scanner.next().charAt(0)-97;
				toRank = scanner.nextInt()-1;
				
				//retry if move is illegal
			} while (!(((TheBoard.move % 2 == 0) == TheBoard.pieces[fromRank][fromFile].isWhite) && !(TheBoard.pieces[fromRank][fromFile].empty) && ((TheBoard.pieces[toRank][toFile].empty) || (TheBoard.pieces[fromRank][fromFile].isWhite != TheBoard.pieces[toRank][toFile].isWhite)) && TheBoard.pieces[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile) && 0 <= fromRank && fromRank < 8 && 0 <= fromFile && fromFile < 8));
			
			TheBoard.movePiece(fromRank, fromFile, toRank, toFile);
			
			PrintBoard(TheBoard);
			
			//whitesTurn = !whitesTurn;
		}
	}
	
	public static void PrintBoard(Board board) {
		/*I want to focus on chess rather than graphics, so I'll just print text to display the game.
		I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.*/
		
		//Print rows of pieces with labels. Since the first things to be printed are at the top, I'll go backwards so White can be at the bottom:
		System.out.println("r\\f a  b  c  d  e  f  g  h ");
		for (int i=board.pieces.length-1; i>=0; i--) {
			String rankText = (i+1) + "| ";
			
			for (int j=0; j<board.pieces[i].length; j++) {
				String pieceText = board.pieces[i][j].letter;
				
				if (board.pieces[i][j].empty) {
					pieceText = "[" + pieceText + "]";
				}else if (board.pieces[i][j].isWhite) {
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