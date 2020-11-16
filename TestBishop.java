import org.junit.Test;
import static org.junit.Assert.*;

public class TestBishop {
	Board testBoard = new Board();
	Bishop testBishop = new Bishop(Color.WHITE, testBoard, new IntPair(0, 5));
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testBishop_f1c4_legal() {
		testBoard.pieces.add(testBishop);
		
		assertTrue(testBishop.isLegalMove(3, 2));
	}
	
	@Test
	public void testBishop_f1c4_jump_illegal() {
		Rook testRook = new Rook(Color.WHITE, testBoard, new IntPair(1, 4));
		
		testBoard.pieces.add(testBishop);
		testBoard.pieces.add(testRook);
		
		assertFalse(testBishop.isLegalMove(3, 2));
	}
	
	@Test
	public void testBishop_f1f4_illegal() {
		testBoard.pieces.add(testBishop);
		
		assertFalse(testBishop.isLegalMove(3, 5));
	}
}