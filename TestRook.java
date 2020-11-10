import org.junit.Test;
import static org.junit.Assert.*;

public class TestRook {
	Board testBoard = new Board();
	Rook testRook = new Rook(true, testBoard);
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testRook_a1a8_legal() {
		testBoard.pieces[0][0] = testRook;
		
		assertTrue(testRook.isLegalMove(0, 0, 7, 0));
	}
	
	@Test
	public void testRook_a1a8_jump_illegal() {
		testBoard.pieces[0][0] = testRook;
		testBoard.pieces[1][0] = testRook;
		
		assertFalse(testRook.isLegalMove(0, 0, 7, 2));
	}
	
	@Test
	public void testRook_a1b2_illegal() {
		testBoard.pieces[0][0] = testRook;
		
		assertFalse(testRook.isLegalMove(0, 0, 1, 1));
	}
}