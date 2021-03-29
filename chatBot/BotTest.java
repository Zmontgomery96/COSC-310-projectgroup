package chatBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName; //Can be used to name the tests
import org.junit.jupiter.api.RepeatedTest; //Can be used to easily run a test multiple times
import org.junit.jupiter.api.Test;

//TODO:
//1. Test Main.review()        
//2. Test Main.showListWith()
//3. Test Main.validate()
//4. Test Main.validBirthDate()
//5. Test gui.send()
//6. Test gui.botSend()
//7. Test gui.retrieveUserInput()
//8. Test gui.botOutput()
//*** Some of these are quite simple and might not be necessary to test. I just included most of the methods we created in the Main and gui classes.


public class BotTest {
	
	private Main main;

	@BeforeEach
	public void setUp() throws Exception {
		main = new Main();
	}
	
	@Test
	public void test1() {
		
	}

}
