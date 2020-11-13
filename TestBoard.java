import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard {
	Board testBoard;
	King testKing = new King(Color.WHITE, testBoard);
	Knight testKnight = new Knight(Color.WHITE, testBoard);
	Rook testEnemyRook = new Rook(Color.BLACK, testBoard);
	
	@Test
	public void testBoard_Ng1f3() {//create a board with a knight, move the knight, and verify the new position
		testBoard = new Board();
		
		
		testBoard.pieces[0][6] = testKnight;
		
		testBoard.movePiece(0, 6, 2, 5);
		
		assertFalse(testBoard.pieces[0][6].equals(testKnight));
		assertEquals(testBoard.pieces[2][5], testKnight);
	}
	
	@Test
	public void testBoard_moveIntoCheckIllegal() {
		testBoard = new Board();
		
		testBoard.pieces[0][6] = testKnight;
		testBoard.pieces[0][7] = testKing;
		testBoard.pieces[0][0] = testEnemyRook;
		
		assertFalse(testBoard.movePiece(0, 6, 2, 5));
	}
}