import java.util.Scanner;

class ChessEngine {
	public static void main(String[] args) {
		//Create objects for each color of each piece.
		//Create these boolean arrays such that if a piece is at (4,4), it can be legal to move to (i,j) if pieceMove[i][j].
		boolean[][] blackPawnMove = {
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		boolean[][] whitePawnMove = {//Pawn direction depends on color
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		System.out.println(whitePawnMove[5][4]);
		boolean[][] knightMove = {
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,true ,false,false,false,true ,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,true ,false,false,false,true ,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		boolean[][] bishopMove = {
			{true ,false,false,false,false,false,false,false,true },
			{false,true ,false,false,false,false,false,true ,false},
			{false,false,true ,false,false,false,true ,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,true ,false,false,false,true ,false,false},
			{false,true ,false,false,false,false,false,true ,false},
			{true ,false,false,false,false,false,false,false,true }
		};
		boolean[][] rookMove = {
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{true ,true ,true ,true ,false,true ,true ,true ,true },
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false},
			{false,false,false,false,true ,false,false,false,false}
		};
		boolean[][] queenMove = {
			{true ,false,false,false,true ,false,false,false,true },
			{false,true ,false,false,true ,false,false,true ,false},
			{false,false,true ,false,true ,false,true ,false,false},
			{false,false,false,true ,true ,true ,false,false,false},
			{true ,true ,true ,true ,false,true ,true ,true ,true },
			{false,false,false,true ,true ,true ,false,false,false},
			{false,false,true ,false,true ,false,true ,false,false},
			{false,true ,false,false,true ,false,false,true ,false},
			{true ,false,false,false,true ,false,false,false,true }
		};
		boolean[][] kingMove = {
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,true ,true ,true ,false,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,false,true ,true ,true ,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		boolean[][] blackPawnCapture = {//pawns take differently than they move
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		boolean[][] whitePawnCapture = {
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,true ,false,true ,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false}
		};
		
		//Now create all pieces:
		Piece whitePawn = new Piece(whitePawnMove, whitePawnCapture, "_", true);
		Piece blackPawn = new Piece(whitePawnMove, whitePawnCapture, "_", false);
		
		Piece whiteKnight = new Piece(knightMove, "N", true);
		Piece blackKnight = new Piece(knightMove, "N", false);
		
		Piece whiteBishop = new Piece(bishopMove, "B", true);
		Piece blackBishop = new Piece(bishopMove, "B", false);
		
		Piece whiteRook = new Piece(rookMove, "R", true);
		Piece blackRook = new Piece(rookMove, "R", false);
		
		Piece whiteQueen = new Piece(queenMove, "Q", true);
		Piece blackQueen = new Piece(queenMove, "Q", false);
		
		Piece whiteKing = new Piece(kingMove, "K", true);
		Piece blackKing = new Piece(kingMove, "K", false);
		
		Piece noPiece = new Piece(" ");
		
		/*I'll store the current position in an array. Board[i][j] is the piece in rank i+1 and file j+1.
		Information affecting the legality castling and en passant are not reflected here; I'll fix it later.*/
		Piece[][] Board = {
			{whiteRook, whiteKnight, whiteBishop, whiteQueen, whiteKing, whiteBishop, whiteKnight, whiteRook},
			{whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn},
			{noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece},
			{noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece},
			{noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece},
			{noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece, noPiece},
			{blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn},
			{blackRook, blackKnight, blackBishop, blackQueen, blackKing, blackBishop, blackKnight, blackRook}};
		
		PrintBoard(Board);
		
		//i should create a Piece class since movements are all different
		
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
				System.out.println("Enter the file (a-z -> 1-8) and rank (1-8) of the piece to move and the position to move to.");
				
				fromFile = scanner.nextInt()-1;
				fromRank = scanner.nextInt()-1;
				toFile = scanner.nextInt()-1;
				toRank = scanner.nextInt()-1;
				
				//retry if move is illegal
			} while (!((whitesTurn == Board[fromRank][fromFile].isWhite) && !(Board[fromRank][fromFile].equals(noPiece)) && Board[fromRank][fromFile].isLegalMove(fromRank, fromFile, toRank, toFile)));
			
			Board[toRank][toFile] = Board[fromRank][fromFile];
			Board[fromRank][fromFile] = noPiece;
			
			PrintBoard(Board);
			
			whitesTurn = !whitesTurn;
		}
	}
	
	public static void PrintBoard(Piece[][] board) {
		/*I want to focus on chess rather than graphics, so I'll just print text to display the game.
		I'll use parentheses for white pieces and curly brackets for black pieces, and square brackets for empty squares.*/
		
		//Print rows of pieces with labels. Since the first things to be printed are at the top, I'll go backwards so White can be at the bottom:
		System.out.println("r/f a  b  c  d  e  f  g  h ");
		for (int i=board.length-1; i>=0; i--) {
			String rankText = (i+1) + "| ";
			
			for (int j=0; j<board[i].length; j++) {
				String pieceText = board[i][j].Letter;
				
				if (board[i][j].noPiece) {
					pieceText = "[" + pieceText + "]";
				}else if (board[i][j].isWhite) {
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