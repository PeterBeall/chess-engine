import java.util.Scanner;
import java.util.ArrayList;

class ChessEngine {
	public static void main(String[] args) {
		//create pieces for the board:
		Board TheBoard = new Board();
		Pawn BlackPawn = new Pawn(Color.BLACK, TheBoard);
		Pawn WhitePawn = new Pawn(Color.WHITE, TheBoard);
		Knight BlackKnight = new Knight(Color.BLACK, TheBoard);
		Knight WhiteKnight = new Knight(Color.WHITE, TheBoard);
		Bishop BlackBishop = new Bishop(Color.BLACK, TheBoard);
		Bishop WhiteBishop = new Bishop(Color.WHITE, TheBoard);
		Rook BlackRook = new Rook(Color.BLACK, TheBoard);
		Rook WhiteRook = new Rook(Color.WHITE, TheBoard);
		Queen BlackQueen = new Queen(Color.BLACK, TheBoard);
		Queen WhiteQueen = new Queen(Color.WHITE, TheBoard);
		King BlackKing = new King(Color.BLACK, TheBoard);
		King WhiteKing = new King(Color.WHITE, TheBoard);
		NoPiece EmptyPiece = new NoPiece(TheBoard);
		
		//A Board object will store the pieces in their positions. Board.pieces[i][j] is the piece in rank i+1 and file j+1.
		TheBoard.setPieces(new Piece[][] {
			{WhiteRook, WhiteKnight, WhiteBishop, WhiteQueen, WhiteKing, WhiteBishop, WhiteKnight, WhiteRook},
			{WhitePawn, WhitePawn, WhitePawn, WhitePawn, WhitePawn, WhitePawn, WhitePawn, WhitePawn},
			{EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece},
			{EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece},
			{EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece},
			{EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece, EmptyPiece},
			{BlackPawn, BlackPawn, BlackPawn, BlackPawn, BlackPawn, BlackPawn, BlackPawn, BlackPawn},
			{BlackRook, BlackKnight, BlackBishop, BlackQueen, BlackKing, BlackBishop, BlackKnight, BlackRook}});
		
		PrintBoard(TheBoard);
		
		//i'll use the terminal to enter moves:
		Scanner scanner = new Scanner(System.in);
		
		//move pieces:
		while (true) {//later i'll replace 'true' with a condition of nobody being in checkmate or stalemate
			//fromRank,fromFile will be the coordinates of the piece to be moved. toRank,toFile is where to move it to.
			int fromFile;
			int fromRank;
			int toFile;
			int toRank;
			
			do {
				//TheBoad.move is an integer that should increase by 1 every move.
				if (TheBoard.move % 2 == 0) {
					System.out.print("White's turn. ");
				}else {
					System.out.print("Black's turn.");
				}
				System.out.println("Enter the file (a-h) and rank (1-8) of the piece to move and the position to move to.");
				
				fromFile = scanner.next().charAt(0)-97;//'a'->0, 'b'->1, etc, since everywhere else files are integers.
				fromRank = scanner.nextInt()-1;
				toFile = scanner.next().charAt(0)-97;
				toRank = scanner.nextInt()-1;
				
				//retry until the move given is legal
			} while (!TheBoard.movePiece(fromRank, fromFile, toRank, toFile));
			
			PrintBoard(TheBoard);
		}
	}
	
	//print board to the terminal:
	public static void PrintBoard(Board board) {
		//I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.
		
		//Print rows of pieces with labels. I'll orient the board based on whoevers turn it is.
		if (board.move % 2 == 0) {
			System.out.println("r\\f a  b  c  d  e  f  g  h ");
		}else {
			System.out.println("r\\f h  g  f  e  d  c  b  a ");
		}
		for (int iPrime=board.pieces.length-1; iPrime>=0; iPrime--) {
			int i = iPrime;
			//flip the board if its black's turn:
			if (board.move % 2 == 1) {
				i = board.pieces.length-1 - iPrime;
			}
			
			String rankText = (i+1) + "| ";
			
			for (int jPrime=0; jPrime<board.pieces[i].length; jPrime++) {
				int j = jPrime;
				//flip the board if its black's turn:
				if (board.move % 2 == 1) {
					j = board.pieces[i].length-1 - jPrime;
				}
				
				String pieceText = board.pieces[i][j].letter;
				
				if (board.pieces[i][j].color == Color.NONE) {
					pieceText = "[" + pieceText + "]";
				}else if (board.pieces[i][j].color == Color.WHITE) {
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