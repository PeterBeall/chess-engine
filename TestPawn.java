import org.junit.Test;
import static org.junit.Assert.*;

public class TestPawn {
	Board testBoard = new Board();
	Pawn testWhitePawn = new Pawn(Color.WHITE, testBoard, new IntPair(1, 4));
	Pawn testBlackPawn = new Pawn(Color.BLACK, testBoard, new IntPair(6, 4));//pawns of opposite color are supposed to move in opposite directions,so I should test both
	
	//I test this mostly by placing one on a board and verifying that the legality of going to a certain square is correct.
	
	@Test
	public void testWhitePawn_e2e2_illegal() {
		testBoard.pieces.add(testWhitePawn);
		
		assertFalse(testWhitePawn.isLegalMove(1, 4));//e2e2
	}
	
	@Test
	public void testWhitePawn_e2e3_legal() {
		testBoard.pieces.add(testWhitePawn);
		
		assertTrue(testWhitePawn.isLegalMove(2, 4));//e2e3
	}
	
	@Test
	public void testBlackPawn_e7e6_legal() {
		testBoard.pieces.add(testBlackPawn);
		
		assertTrue(testBlackPawn.isLegalMove(5, 4));//e7e6
	}
	
	@Test
	public void testWhitePawn_e7e6_illegal() {
		testWhitePawn.pos = new IntPair(6, 4);
		testBoard.pieces.add(testWhitePawn);
		
		assertFalse(testWhitePawn.isLegalMove(5, 4));
	}
	
	@Test
	public void testBlackPawn_e2e3_illegal() {
		testBlackPawn.pos = new IntPair(1, 4);
		testBoard.pieces.add(testBlackPawn);
		
		assertFalse(testBlackPawn.isLegalMove(2, 4));
	}
	
	@Test
	public void testWhitePawn_e2e4_legal() {
		testBoard.pieces.add(testWhitePawn);
		
		assertTrue(testWhitePawn.isLegalMove(3, 4));
	}
	
	@Test
	public void testBlackPawn_e7e5_legal() {
		testBoard.pieces.add(testBlackPawn);
		
		assertTrue(testBlackPawn.isLegalMove(4, 4));
	}
	
	@Test
	public void testWhitePawn_e2e4_jump_illegal() {
		testBlackPawn.pos = new IntPair(2, 4);
		
		testBoard.pieces.add(testWhitePawn);
		testBoard.pieces.add(testBlackPawn);
		
		assertFalse(testWhitePawn.isLegalMove(3, 4));
	}
	
	@Test
	public void testBlackPawn_e7e5_jump_illegal() {
		testWhitePawn.pos = new IntPair(5, 4);
		
		testBoard.pieces.add(testBlackPawn);
		testBoard.pieces.add(testWhitePawn);
		
		assertFalse(testBlackPawn.isLegalMove(4, 4));
	}
	
	@Test
	public void testWhitePawn_e3e5_illegal() {
		testWhitePawn.pos = new IntPair(2, 4);
		
		testBoard.pieces.add(testWhitePawn);
		
		assertFalse(testWhitePawn.isLegalMove(4, 4));
	}
	
	@Test
	public void testBlackPawn_e6e4_illegal() {
		testBlackPawn.pos = new IntPair(5, 4);
		
		assertFalse(testBlackPawn.isLegalMove(3, 4));
	}
	
	@Test
	public void testWhitePawn_e4d5_illegal() {
		testWhitePawn.pos = new IntPair(3, 4);
		
		testBoard.pieces.add(testWhitePawn);
		
		assertFalse(testWhitePawn.isLegalMove(4, 3));
	}
	
	@Test
	public void testWhitePawn_e4xd5_legal() {
		testWhitePawn.pos = new IntPair(3, 4);
		testBlackPawn.pos = new IntPair(4, 3);
		
		testBoard.pieces.add(testWhitePawn);
		testBoard.pieces.add(testBlackPawn);
		
		assertTrue(testWhitePawn.isLegalMove(4, 3));
	}
	
	@Test
	public void testWhitePawn_e5xd6_enPassant() {
		testWhitePawn.pos = new IntPair(4, 4);
		testBlackPawn.pos = new IntPair(6, 3);
		
		testBoard.pieces.add(testWhitePawn);
		testBoard.pieces.add(testBlackPawn);
		
		testBoard.move = 1;
		
		testBoard.movePiece(testBlackPawn, new IntPair(4, 3));
		testBoard.movePiece(testWhitePawn, new IntPair(5, 3));
		
		assertNull(testBoard.getPieceAt(4, 3));
		assertEquals(testBoard.getPieceAt(5, 3), testWhitePawn);
	}
	
	@Test
	public void testWhitePawn_e5xd6_nonPassant_illegal() {
		testWhitePawn.pos = new IntPair(4, 4);
		testBlackPawn.pos = new IntPair(4, 3);
		
		testBoard.pieces.add(testWhitePawn);
		testBoard.pieces.add(testBlackPawn);
		
		assertFalse(testWhitePawn.isLegalMove(5, 3));
	}
}