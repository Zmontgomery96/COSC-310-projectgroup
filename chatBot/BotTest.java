package chatBot;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName; //Can be used to name the tests
// import org.junit.jupiter.api.RepeatedTest; //Can be used to easily run a test multiple times
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;


public class BotTest {
	File myfile = new File("Test1.txt");
	
	private Main main;

	@BeforeEach
	public void setUp() throws Exception {
		main = new Main();
	}
	// Date validation method
	 @Test
	 public void testValidBirthDate() {
		assertEquals(true, Main.validBirthDate("02/02/2021"));
		assertEquals(false, Main.validBirthDate("02/0/2021"));

	 }
	 //Tests that user input is correctly read and stored
	 @Test
	public void testRetrieveUserInput() {
		assertEquals("Hello",gui.retrieveUserInput(0));
	}
	// Tests that an empty message would result in a false result
	@Test
	public void testSend() {
		gui.textField.setText("This is a test, and since it's not empty it will return true");
		assertEquals(true, gui.send());
		//This will not send and return false since it is empty
		gui.textField.setText("");
		assertEquals(false, gui.send());
	}
	// Tests that name is of a valid format
	@Test
	public void testValidate() {
		assertEquals(true, Main.validate("Anthony"));
		assertEquals(false, Main.validate("A"));
	}
	@Test
	public void testShowListWith() {
		patient p = new patient();
		assertEquals(true, main.showListWith(p));
	}

	//botsend
	@Test
	public void testBotSend() {
		gui.botOutput("test");

		assertEquals(gui.ta.getText(), " USER (Add name here later): This is a test, and since it's not empty it will return true\n"
				+ " BOT: test\n");
	}
	//botoutput
	@Test
	public void testBotOutput() {
		assertTrue(gui.botOutput("Test"));
		assertTrue(gui.bot.contains("Test"));
		
	}

}
