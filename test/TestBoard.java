import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestBoard {
	Board testBoard = new Board();
	
	@Test
	public void testBoard_Ng1f3() {//create a board with a knight, move the knight, and verify the new position
		Knight testKnight = new Knight(Color.WHITE, testBoard, new IntPair(0, 6));
		
		testBoard.pieces.add(testKnight);
		
		testBoard.movePiece(testKnight, new IntPair(2, 5));
		
		assertNull(testBoard.getPieceAt(0, 6));
		assertEquals(testBoard.getPieceAt(2, 5), testKnight);
	}
	
	@Test
	public void testBoard_moveIntoCheckIllegal() {
		Knight testKnight = new Knight(Color.WHITE, testBoard, new IntPair(0, 6));
		King testKing = new King(Color.WHITE, testBoard, new IntPair(0, 7));
		Rook testEnemyRook = new Rook(Color.BLACK, testBoard, new IntPair(0, 0));
		
		testBoard.pieces.add(testKnight);
		testBoard.pieces.add(testKing);
		testBoard.pieces.add(testEnemyRook);
		
		assertFalse(testBoard.movePiece(testKnight, new IntPair(2, 5)));
	}
}