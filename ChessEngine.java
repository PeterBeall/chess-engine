import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;

class ChessEngine {
	public static void main(String[] args) {
		Board TheBoard = new Board();
		
		//create set with pieces in the starting position:
		HashSet<Piece> startingPieces = new HashSet<Piece>(Arrays.asList(
			new Rook(Color.WHITE, TheBoard, 0, 0),
			new Knight(Color.WHITE, TheBoard, 0, 1),
			new Bishop(Color.WHITE, TheBoard, 0, 2),
			new Queen(Color.WHITE, TheBoard, 0, 3),
			new King(Color.WHITE, TheBoard, 0, 4),
			new Bishop(Color.WHITE, TheBoard, 0, 5),
			new Knight(Color.WHITE, TheBoard, 0, 6),
			new Rook(Color.WHITE, TheBoard, 0, 7),
			new Rook(Color.BLACK, TheBoard, 7, 0),
			new Knight(Color.BLACK, TheBoard, 7, 1),
			new Bishop(Color.BLACK, TheBoard, 7, 2),
			new Queen(Color.BLACK, TheBoard, 7, 3),
			new King(Color.BLACK, TheBoard, 7, 4),
			new Bishop(Color.BLACK, TheBoard, 7, 5),
			new Knight(Color.BLACK, TheBoard, 7, 6),
			new Rook(Color.BLACK, TheBoard, 7, 7)));
		for (int i=0; i<8; i++) {
			startingPieces.add(new Pawn(Color.WHITE, TheBoard, 1, i));
			startingPieces.add(new Pawn(Color.BLACK, TheBoard, 6, i));
		}
		
		//A Board object will store the pieces in their positions. Board.pieces[i][j] is the piece in rank i+1 and file j+1.
		TheBoard.setPieces(startingPieces);
		
		PrintBoard(TheBoard);
		
		//i'll use the terminal to enter moves:
		Scanner scanner = new Scanner(System.in);
		
		//move pieces:
		while (!TheBoard.isGameOver()) {
			//from will be the coordinates of the piece to be moved. to will be where to move it to.
			int fromFile;
			int fromRank;
			int toFile;
			int toRank;
			IntPair from;
			IntPair to;			
			
			do {
				//TheBoad.move is an integer that should increase by 1 every move.
				if (TheBoard.move % 2 == 0) {
					System.out.print("White's turn. ");
				}else {
					System.out.print("Black's turn. ");
				}
				System.out.println("Enter the file (a-h) and rank (1-8) of the piece to move and the position to move to, e.g. \"e2e4\"");
				
				String enteredMove = scanner.next();
				
				fromFile = enteredMove.charAt(0)-97;//'a'->0, 'b'->1, etc, since everywhere else files are integers.
				fromRank = enteredMove.charAt(1)-49;
				toFile = enteredMove.charAt(2)-97;
				toRank = enteredMove.charAt(3)-49;
				
				from = new IntPair(fromRank, fromFile);
				to = new IntPair(toRank, toFile);
								
				//retry until the move given is legal
			} while (!TheBoard.movePiece(from, to));
			
			PrintBoard(TheBoard);
		}
		
		System.out.println("Game over");
	}
	
	//print board to the terminal:
	public static void PrintBoard(Board board) {
		Piece[][] piecesArray = new Piece[8][8];
		
		//arrange pieces into an array:
		for (Piece piece : board.pieces) {
			piecesArray[piece.pos.rank][piece.pos.file] = piece;
		}
		
		//I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.
		
		//Print rows of pieces with labels. I'll orient the board based on whoevers turn it is.
		if (board.move % 2 == 0) {
			System.out.println("r\\f a  b  c  d  e  f  g  h ");
		}else {
			System.out.println("r\\f h  g  f  e  d  c  b  a ");
		}
		for (int iPrime=7; iPrime>=0; iPrime--) {
			int i = iPrime;
			//flip the board if its black's turn:
			if (board.move % 2 == 1) {
				i = 7 - iPrime;
			}
			
			String rankText = (i+1) + "| ";
			
			for (int jPrime=0; jPrime<8; jPrime++) {
				int j = jPrime;
				//flip the board if its black's turn:
				if (board.move % 2 == 1) {
					j = 7 - jPrime;
				}
				
				String pieceText = "";
				
				if (piecesArray[i][j] == null) {
					pieceText = "[ ]";
				}else if (piecesArray[i][j].color == Color.WHITE) {
					pieceText = "(" + piecesArray[i][j].letter + ")";
				}else {
					pieceText = "{" + piecesArray[i][j].letter + "}";
				}
				
				rankText += pieceText;
			}
			
			System.out.println(rankText);
		}
	}
}