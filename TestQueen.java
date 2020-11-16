import org.junit.Test;
import static org.junit.Assert.*;

public class TestQueen {
	Board testBoard = new Board();
	Queen testQueen = new Queen(Color.WHITE, testBoard, new IntPair(0, 3));
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testQueen_d1g4_legal() {
		testBoard.pieces.add(testQueen);
		
		assertTrue(testQueen.isLegalMove(3, 6));
	}
	
	@Test
	public void testQueen_d1g4_jump_illegal() {
		testBoard.pieces.add(testQueen);
		testBoard.pieces.add(new Queen(Color.WHITE, testBoard, new IntPair(1, 4)));
		
		assertFalse(testQueen.isLegalMove(3, 6));
	}
	
	@Test
	public void testQueen_d1e3_illegal() {
		testBoard.pieces.add(testQueen);
		
		assertFalse(testQueen.isLegalMove(2, 4));
	}
}