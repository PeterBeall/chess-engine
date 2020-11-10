import org.junit.Test;
import static org.junit.Assert.*;

public class TestQueen {
	Board testBoard = new Board();
	Queen testQueen = new Queen(true, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testQueen_d1g4_legal() {
		testBoard.pieces[0][3] = testQueen;
		
		assertTrue(testQueen.isLegalMove(0, 3, 3, 6));
	}
	
	@Test
	public void testQueen_d1g4_jump_illegal() {
		testBoard.pieces[0][3] = testQueen;
		testBoard.pieces[1][4] = testQueen;
		
		assertFalse(testQueen.isLegalMove(0, 3, 3, 6));
	}
	
	@Test
	public void testQueen_d1d2_legal() {
		testBoard.pieces[0][3] = testQueen;
		
		assertTrue(testQueen.isLegalMove(0, 3, 1, 3));
	}
}