import org.junit.Test;
import static org.junit.Assert.*;

public class TestKnight {
	Board testBoard = new Board();
	Knight testKnight = new Knight(true, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testKnight_g1f3_legal() {
		testBoard.pieces[0][6] = testKnight;
		
		assertTrue(testKnight.isLegalMove(0, 6, 2, 5));
	}
	
	@Test
	public void testKnight_g1g3_illegal() {
		testBoard.pieces[0][6] = testKnight;
		
		assertFalse(testKnight.isLegalMove(0, 6, 2, 6));
	}
}