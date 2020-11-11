import org.junit.Test;
import static org.junit.Assert.*;

public class TestKing {
	Board testBoard = new Board();
	King testKing = new King(Color.WHITE, testBoard);
	
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
}