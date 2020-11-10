import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard {
	Board testBoard;
		
	@Test
	public void testBoard_Ng1f3() {//create a board with a knight, move the knight, and verify the new position
		testBoard = new Board();
		Knight testKnight = new Knight(false, testBoard);
		
		testBoard.pieces[0][6] = testKnight;
		
		testBoard.movePiece(0, 6, 2, 5);
		
		assertTrue(!testBoard.pieces[0][6].equals(testKnight));
		assertEquals(testBoard.pieces[2][5], testKnight);
	}
}