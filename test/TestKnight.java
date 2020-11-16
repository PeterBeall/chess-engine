import org.junit.Test;
import static org.junit.Assert.*;

public class TestKnight {
	Board testBoard = new Board();
	Knight testKnight = new Knight(Color.WHITE, testBoard, new IntPair(0, 6));
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testKnight_g1f3_legal() {
		testBoard.pieces.add(testKnight);
		
		assertTrue(testKnight.isLegalMove(2, 5));
	}
	
	@Test
	public void testKnight_g1g3_illegal() {
		testBoard.pieces.add(testKnight);
		
		assertFalse(testKnight.isLegalMove(2, 6));
	}
}