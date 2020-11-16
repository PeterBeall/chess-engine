import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestBoard.class,
	TestPawn.class,
	TestKnight.class,
	TestBishop.class,
	TestRook.class,
	TestQueen.class,
	TestKing.class
})

public class TestSuite {}