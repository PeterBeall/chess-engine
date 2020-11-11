import org.junit.Test;
import static org.junit.Assert.*;

public class TestPawn {
	Board testBoard = new Board();
	Pawn testWhitePawn = new Pawn(Color.WHITE, testBoard);
	Pawn testBlackPawn = new Pawn(Color.BLACK, testBoard);//pawns of opposite color are supposed to move in opposite directions,so I should test both
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testWhitePawn_e2e2_illegal() {
		testBoard.pieces[1][4] = testWhitePawn;
		
		assertFalse(testWhitePawn.isLegalMove(1, 4, 1, 4));//e2e2
	}
	
	@Test
	public void testWhitePawn_e2e3_legal() {
		testBoard.pieces[1][4] = testWhitePawn;
		
		assertTrue(testWhitePawn.isLegalMove(1, 4, 2, 4));//e2e3
	}
	
	@Test
	public void testBlackPawn_e7e6_legal() {
		testBoard.pieces[6][4] = testBlackPawn;
		
		assertTrue(testBlackPawn.isLegalMove(6, 4, 5, 4));//e7e6
	}
	
	@Test
	public void testWhitePawn_e7e6_illegal() {
		testBoard.pieces[6][4] = testWhitePawn;
		
		assertFalse(testWhitePawn.isLegalMove(6, 4, 5, 4));
	}
	
	@Test
	public void testBlackPawn_e2e3_illegal() {
		testBoard.pieces[1][4] = testBlackPawn;
		
		assertFalse(testBlackPawn.isLegalMove(1, 4, 2, 4));
	}
	
	@Test
	public void testWhitePawn_e2e4_legal() {
		testBoard.pieces[1][4] = testWhitePawn;
		
		assertTrue(testWhitePawn.isLegalMove(1, 4, 3, 4));
	}
	
	@Test
	public void testBlackPawn_e7e5_legal() {
		testBoard.pieces[6][4] = testBlackPawn;
		
		assertTrue(testBlackPawn.isLegalMove(6, 4, 4, 4));
	}
	
	@Test
	public void testWhitePawn_e2e4_jump_illegal() {
		testBoard.pieces[1][4] = testWhitePawn;
		testBoard.pieces[2][4] = testBlackPawn;
		
		assertFalse(testWhitePawn.isLegalMove(1, 4, 3, 4));
	}
	
	@Test
	public void testBlackPawn_e7e5_jump_illegal() {
		testBoard.pieces[6][4] = testBlackPawn;
		testBoard.pieces[5][4] = testWhitePawn;
		
		assertFalse(testBlackPawn.isLegalMove(6, 4, 4, 4));
	}
	
	@Test
	public void testWhitePawn_e3e5_illegal() {
		testBoard.pieces[2][4] = testWhitePawn;
		
		assertFalse(testWhitePawn.isLegalMove(2, 4, 4, 4));
	}
	
	@Test
	public void testBlackPawn_e6e4_illegal() {
		testBoard.pieces[5][4] = testBlackPawn;
		
		assertFalse(testBlackPawn.isLegalMove(5, 4, 3, 4));
	}
	
	@Test
	public void testWhitePawn_e4d5_illegal() {
		testBoard.pieces[3][4] = testWhitePawn;
		
		assertFalse(testWhitePawn.isLegalMove(3, 4, 4, 2));
	}
	
	@Test
	public void testWhitePawn_e4xd5_legal() {
		testBoard.pieces[3][4] = testWhitePawn;
		testBoard.pieces[4][5] = testBlackPawn;
		
		assertFalse(testWhitePawn.isLegalMove(3, 4, 4, 2));
	}
	
	@Test
	public void testWhitePawn_e5xd6_enPassant_legal() {
		testBoard.pieces[4][4] = testWhitePawn;
		testBoard.pieces[6][3] = testBlackPawn;
		
		testBoard.movePiece(6, 3, 4, 3);
		
		assertFalse(testWhitePawn.isLegalMove(4, 4, 3, 5));
	}
	
	@Test
	public void testWhitePawn_e5xd6_nonPassant_illegal() {
		testBoard.pieces[4][4] = testWhitePawn;
		testBoard.pieces[6][3] = testBlackPawn;
		
		assertFalse(testWhitePawn.isLegalMove(4, 4, 3, 5));
	}
}