import org.junit.Test;
import static org.junit.Assert.*;

public class TestBishop {
	Board testBoard = new Board();
	Bishop testBishop = new Bishop(Color.WHITE, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testBishop_f1c4_legal() {
		testBoard.pieces[0][5] = testBishop;
		
		assertTrue(testBishop.isLegalMove(0, 5, 3, 2));
	}
	
	@Test
	public void testBishop_f1c4_jump_illegal() {
		testBoard.pieces[0][5] = testBishop;
		testBoard.pieces[1][4] = testBishop;
		
		assertFalse(testBishop.isLegalMove(0, 5, 3, 2));
	}
	
	@Test
	public void testBishop_f1f4_illegal() {
		testBoard.pieces[0][5] = testBishop;
		
		assertFalse(testBishop.isLegalMove(0, 5, 3, 5));
	}
}