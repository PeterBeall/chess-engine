import org.junit.Test;
import static org.junit.Assert.*;

public class TestKing {
	Board testBoard = new Board();
	King testKing = new King(Color.WHITE, testBoard, new IntPair(0, 4));
	Bishop testFirendlyBishop = new Bishop(Color.WHITE, testBoard);
	Knight testEnemyKnight = new Knight(Color.BLACK, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testKing_e1e2_legal() {
		testBoard.pieces.add(testKing);
		
		assertTrue(testKing.isLegalMove(1, 4));
	}
	
	@Test
	public void testKing_e1e3_illegal() {
		testBoard.pieces.add(testKing);
		
		assertFalse(testKing.isLegalMove(2, 4));
	}
	
	@Test
	public void testKing_e1f2_legal() {
		testBoard.pieces.add(testKing);
		
		assertTrue(testKing.isLegalMove(1, 5));
	}
	
	//test check related things:
	@Test
	public void testKing_inCheck() {
		testKing.pos = new IntPair(0, 7);
		King testEnemyKing = new King(Color.BLACK, testBoard, new IntPair(2, 6));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(4, 3));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyKing);
		testBoard.pieces.add(testEnemyBishop);
		
		assertTrue(testKing.isInCheck());
	}
	
	@Test
	public void testKing_notInCheck() {
		testKing.pos = new IntPair(0, 7);
		King testEnemyKing = new King(Color.BLACK, testBoard, new IntPair(2, 6));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyKing);
		
		assertFalse(testKing.isInCheck());
	}
	
	@Test
	public void testKing_inStalemate() {
		testKing.pos = new IntPair(0, 7);
		King testEnemyKing = new King(Color.BLACK, testBoard, new IntPair(2, 6));
		Knight testEnemyKnight = new Knight(Color.BLACK, testBoard, new IntPair(2, 7));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyKing);
		testBoard.pieces.add(testEnemyKnight);
		
		assertTrue(testKing.isInStalemate());
	}
	
	@Test
	public void testKing_notInStalemate() {
		testBoard.pieces.add(testKing);
		
		assertFalse(testKing.isInStalemate());
	}
	
	@Test
	public void testKing_inCheckmate() {
		testKing.pos = new IntPair(0, 7);
		King testEnemyKing = new King(Color.BLACK, testBoard, new IntPair(2, 6));
		Knight testEnemyKnight = new Knight(Color.BLACK, testBoard, new IntPair(2, 7));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(4, 3));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyKing);
		testBoard.pieces.add(testEnemyKnight);
		testBoard.pieces.add(testEnemyBishop);
		
		assertTrue(testKing.isInCheckmate());
	}
	
	@Test
	public void testKing_notInCheckmate() {
		testKing.pos = new IntPair(0, 7);
		King testEnemyKing = new King(Color.BLACK, testBoard, new IntPair(2, 6));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(4, 3));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyKing);
		testBoard.pieces.add(testEnemyBishop);
		
		assertFalse(testKing.isInCheckmate());
	}
	
	//castling:
	@Test
	public void testKing_castle_kingside_legal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 7));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		
		testBoard.movePiece(testKing, new IntPair(0, 6));
		
		assertEquals(testKing, testBoard.getPieceAt(0, 6));
		assertEquals(testRook, testBoard.getPieceAt(0, 5));
	}
	
	@Test
	public void testKing_castle_kingside_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 7));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(7, 7));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(testEnemyBishop);
		
		testBoard.movePiece(testRook, new IntPair(1, 7));
		testBoard.movePiece(testEnemyBishop, new IntPair(6, 6));
		testBoard.movePiece(testRook, new IntPair(0, 7));
		
		assertFalse(testBoard.movePiece(testKing, new IntPair(0, 6)));
	}
	
	@Test
	public void testKing_castle_queenside_legal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 0));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		
		testBoard.movePiece(testKing, new IntPair(0, 2));
		
		assertEquals(testKing, testBoard.getPieceAt(0, 2));
		assertEquals(testRook, testBoard.getPieceAt(0, 3));
	}
	
	@Test
	public void testKing_castle_queenside_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 0));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(7, 7));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(testEnemyBishop);
		
		testBoard.movePiece(testRook, new IntPair(1, 7));
		testBoard.movePiece(testEnemyBishop, new IntPair(6, 6));
		testBoard.movePiece(testRook, new IntPair(0, 7));
		
		assertFalse(testBoard.movePiece(testKing, new IntPair(0, 6)));
	}
	
	@Test
	public void testKing_castle_outofCheck_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 7));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(1, 5));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(testEnemyBishop);
		
		assertFalse(testBoard.movePiece(testKing, new IntPair(0, 6)));
	}
	
	@Test
	public void testKing_castle_intoCheck_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 7));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(1, 7));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(testEnemyBishop);
		
		assertFalse(testBoard.movePiece(testKing, new IntPair(0, 6)));
	}
	
	@Test
	public void testKing_castle_throughCheck_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 7));
		Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard, new IntPair(1, 6));
		
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(testEnemyBishop);
		
		assertFalse(testBoard.movePiece(testKing, new IntPair(0, 6)));
	}
}