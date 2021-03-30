package chatBot;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName; //Can be used to name the tests
// import org.junit.jupiter.api.RepeatedTest; //Can be used to easily run a test multiple times
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class BotTest {
	
	private Main main;

	@BeforeEach
	public void setUp() throws Exception {
		main = new Main();
	}
	
	 @Test
	 public void testValidBirthDate() {
		assertEquals(true, Main.validBirthDate("02/02/2021"));
		assertEquals(false, Main.validBirthDate("02/0/2021"));

	 }

	@Test
	public void testValidate() {
		assertEquals(true, Main.validate("Anthony"));
		assertEquals(false, Main.validate("A"));
	}
	@Test
	public void testShowListWith() {
		//assertEquals(true, Main.(""));
		//assertEquals(false, Main.(""));
	}

}
