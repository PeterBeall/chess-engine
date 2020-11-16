import org.junit.Test;
import static org.junit.Assert.*;

public class TestRook {
	Board testBoard = new Board();
	Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(0, 0));
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testRook_a1a8_legal() {
		testBoard.pieces.add(testRook);
		
		assertTrue(testRook.isLegalMove(7, 0));
	}
	
	@Test
	public void testRook_a1a8_jump_illegal() {
		testBoard.pieces.add(testRook);
		testBoard.pieces.add(new Rook(Color.WHITE, testBoard, new IntPair(1, 0)));
		
		assertFalse(testRook.isLegalMove(7, 2));
	}
	
	@Test
	public void testRook_a1b2_illegal() {
		testBoard.pieces.add(testRook);
		
		assertFalse(testRook.isLegalMove(1, 1));
	}
}