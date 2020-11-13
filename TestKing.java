import org.junit.Test;
import static org.junit.Assert.*;

public class TestKing {
	Board testBoard = new Board();
	King testKing = new King(Color.WHITE, testBoard);
	King testEnemyKing = new King(Color.BLACK, testBoard);
	Bishop testFirendlyBishop = new Bishop(Color.WHITE, testBoard);
	Bishop testEnemyBishop = new Bishop(Color.BLACK, testBoard);
	Knight testEnemyKnight = new Knight(Color.BLACK, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testKing_e1e2_legal() {
		testBoard.pieces[0][4] = testKing;
		
		assertTrue(testKing.isLegalMove(0, 4, 1, 4));
	}
	
	@Test
	public void testKing_e1e3_illegal() {
		testBoard.pieces[0][4] = testKing;
		
		assertFalse(testKing.isLegalMove(0, 4, 2, 4));
	}
	
	@Test
	public void testKing_e1f2_legal() {
		testBoard.pieces[0][4] = testKing;
		
		assertTrue(testKing.isLegalMove(0, 4, 1, 5));
	}
	
	//test check related things:
	@Test
	public void testKing_inCheck() {
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[2][6] = testEnemyKing;
		testBoard.pieces[4][3] = testEnemyBishop;
		
		assertTrue(testKing.isInCheck(0, 7));
	}
	
	@Test
	public void testKing_notInCheck() {
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[2][6] = testEnemyKing;
		
		assertFalse(testKing.isInCheck(0, 7));
	}
	
	@Test
	public void testKing_inStalemate() {
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[2][6] = testEnemyKing;
		testBoard.pieces[2][7] = testEnemyKnight;
		
		assertTrue(testKing.isPossiblyInStalemate(0, 7));
	}
	
	@Test
	public void testKing_notInStalemate() {
		testBoard.pieces[0][7] = testKing;
		
		assertFalse(testKing.isPossiblyInStalemate(0, 7));
	}
	
	@Test
	public void testKing_inCheckmate() {
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[2][6] = testEnemyKing;
		testBoard.pieces[2][7] = testEnemyKnight;
		testBoard.pieces[4][3] = testEnemyBishop;
		
		assertTrue(testKing.isInCheckmate(0, 7));
	}
	
	@Test
	public void testKing_notInCheckmate() {
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[2][6] = testEnemyKing;
		testBoard.pieces[4][3] = testEnemyBishop;
		
		assertFalse(testKing.isInCheckmate(0, 7));
	}
}