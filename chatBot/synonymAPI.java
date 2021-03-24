package chatBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class synonymAPI {
	
	public static String[] synonyms(String word) {
		String hold = "";
		int i = 0;
		int beg = 0;
		int end = 0;
		String[] strArr = new String[100];
		
		//We use an HTTP GET request here in order to retrieve the synonyms we require from the API
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.dictionaryapi.com/api/v3/references/thesaurus/json/" + word + "?key=f2be3352-0863-45df-a88b-085a31690fea"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			hold = response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			strArr[0] = "Error in code. Caught exception.";
			return strArr;
		}
		
		//This is to only get a substring of the result, as we're only needing the synonyms
		beg = hold.indexOf("syns");
		end = hold.indexOf("ants");
		
		hold = hold.substring(beg + 8, end - 4);
		
		//To check the synonyms that the API is putting out, include the code below:
			//System.out.println(hold);
		
		
		
		//This loop is to separate our synonyms into different array indexes
		while(hold.contains("\"")) {
			end = hold.indexOf("\"", 1);
			
			strArr[i] = hold.substring(1, end);
			
			try {
				hold = hold.substring(end + 2);
			} catch(StringIndexOutOfBoundsException e) {
				hold = hold.substring(end);
			}
			i++;
			
			
		//These exceptions/if statements are specific to the format of the string that we receive from this API, it will not function properly with other APIs attempting the same thing
			if(hold.contains("[") && hold.indexOf("[", 1) < 2) {
				hold = hold.substring(2);
			}
			if(hold.length() < 2)
				hold = "";
		}
		
		return strArr;
		
	}

	public static void main(String[] args){

		//This area of the code is just being used to test the API and ensuring the method works properly.
		
		String[] test = new String[100];
		int i = 0;
		
		test = synonyms("yes");
		
		
		while(test[i] != null) {
			System.out.println(test[i]);
			i++;
		}
	}

    //The review method is not used until the end of the program, given the user the option to give a review if they wish.
    static void review() {
    	//These variables are used to verify various things throughout the method to ensure the user is being taken down the correct path.
    	int n = 0;
    	boolean correct = true;
    	boolean match[] = new boolean [30];
    	String yn = "";
    	String answer = "";
    	
    	//These arrays utilize the synonym API we implemented, giving us many different version of "yes" and/or "no" answers.
    	String[] positive = new String[50];
    	positive = synonyms("yes");
    	String[] negative = new String[50];
    	negative = synonyms("no");
    	
    	Scanner sc = new Scanner(System.in);
    	
    	for(int i = 0; i < match.length; i++) {
    		match[i] = false;
    	}
    	
    	//The while statement gives a way to escape our review method, however this has not been implemented yet
    	//This is considered preparation for a future build where we will optimize different aspects of this program, where this will be one of them.
    	while (!answer.equalsIgnoreCase("OUT")||!yn.equalsIgnoreCase("OUT")){
    		
    	System.out.println("Quick notice before we begin: Do you mind if we attach your personal information along with your review? (Yes to stay anonymous; No to allow us to associate this with your personal information");
    	yn = sc.nextLine();
    	
    	
    	System.out.println("Thanks for participating in our review! Have you been here with us before today?");
    	
    	yn = sc.nextLine();
    	
    	
    	//This while statement only allows the user to proceed to the next set of questions if they give a variation of yes or no that is accepted by our program.
    	//Otherwise it will loop, telling them it is an invalid input and to try again.
    	while(!match[n]) {
    		
    		if(!correct) {
    			System.out.println("Invalid input, try again.");
    			yn = sc.nextLine();
    		}
    			
    	for(String positives:positive) {
    			
    		if (yn.matches("(.*)yes(.*)")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("Great to hear! Do you think you'll come back?");
    			match[n] = true;
    			break;
    		}
    	}
    	for(String negatives:negative) {
    		
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			System.out.println("There's a first for everything! Do you think you'll come back?");
    			match[n] = true;
    			break;
    		}
    	}
    	correct = false;
    	}
    	n++;
    	correct = true;
    	
    	yn = sc.nextLine();
    		
    	//We repeat this while statement multiple times to ensure the review method is given the answers that are required in specific places.
    	while(!match[n]) {
    		
    		if(!correct) {
    			System.out.println("Invalid input, try again.");
    			yn = sc.nextLine();
    		}
    			
    	for(String positives:positive) {
    			
    		if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("Awesome! Would you recommend our service to friends and family?");
    			match[n] = true;
    			break;
    		}
    	}
    	for(String negatives:negative) {
    			
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			System.out.println("Oh no! ): Would you recommend our service to friends and family?");
    			match[n] = true;
    			break;
    		}
    	}
    	correct = false;
    	}
    	n++;
    	correct = true;
    		
    	yn = sc.nextLine();
    		
    	System.out.println("Are there any recommendations you'd like to make in order for us to make your experience better next time?");
    		
    	answer = sc.nextLine();
    		
    		
    	System.out.println("Were you able to accomplish what you came here to do today?");
    		
    	yn = sc.nextLine();
    		
    		
    	while(!match[n]) {
    			
    		if(!correct) {
    			System.out.println("Invalid input, try again.");
    			yn = sc.nextLine();
    		}
    			
    	for(String positives:positive) {
    			
    		if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("Great! Is there any way we could make this easier the next time?");
    			match[n] = true;
    			break;
    		}
    	}
    	for(String negatives:negative) {
    			
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			System.out.println("Is there any way we could fix this for you?");
    			match[n] = true;
    			break;
    		}
    	}
    	correct = false;
    	}
    	n++;
    	correct = true;
    		
    	answer = sc.nextLine();
    		
    	System.out.println("We'll be sure to take any suggestions into consideration!");
    	System.out.println("Did you feel you were safe while you were in our care?");
    		
    	yn = sc.nextLine();
    		
    	System.out.println("Do you mind explaining how or why you felt this way?");
    		
    	answer = sc.nextLine();
    		
    	System.out.println("Thank you for providing us with this information.");
    	System.out.println("Did it feel like you were talking with a real person today?");
    		
    	yn = sc.nextLine();
    		
    		
    	while(!match[n]) {
    			
    		if(!correct) {
    			System.out.println("Invalid input, try again.");
    			yn = sc.nextLine();
    		}
    			
    	for(String positives:positive) {
    			
    		if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("What made you feel this way?");
    			match[n] = true;
    			break;
    		}
    	}
    	for(String negatives:negative) {
    			
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			System.out.println("How can we improve this?");
    			match[n] = true;
    			break;
    		}
    	}
    	correct = false;
    	}
    	n++;
    	correct = true;
    		
    	answer = sc.nextLine();
    		
    	System.out.println("This \'bot\' thanks you for your cooperation! :)");
    	System.out.println("Do you have any general complaints at all from you experience here?");
    		
    	answer = sc.nextLine();
    		
    	System.out.println("Thank you for letting us know.");
    	System.out.println("We've reached near the halfway mark in this review. Would you like to continue?");
    		
    	yn = sc.nextLine();
    		
    		
    	//This response determines if the user would like to continue the survey. If they respond "no" or some variation this will allow the user to escape the survey and end the chat.
    	for(String negatives:negative) {
    			
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			correct = false;
    			break;
    		}
    	}
    		
    	if(!correct) {
    		return;
    	}
    		
    	System.out.println("Great! We'll continue on with the service review. You're almost done!");
    	System.out.println("Did you feel our information gathering process was efficient?");
    		
    	yn = sc.nextLine();
    		
    		
    	while(!match[n]) {
    			
    		if(!correct) {
    			System.out.println("Invalid input, try again.");
    			yn = sc.nextLine();
    		}
    			
    	for(String positives:positive) {
    			
    		if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("Great! Do you have any suggestions for a more efficient method?");
    			match[n] = true;
    			break;
    		}
    	}
    	for(String negatives:negative) {
    			
    		if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
    			System.out.println("Do you have any suggestions to create a speedier experience for you next time?");
    			match[n] = true;
    			break;
    		}
    	}
    	correct = false;
    	}
    	n++;
    	correct = true;
    		
    	answer = sc.nextLine();
    		
    	System.out.println("Would you be willing to rate us today? (Type OUT to exit)");
    		
    	yn = sc.nextLine();
    		
    	for(String positives:positive) {
    			
    		if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
    			System.out.println("Rate our service today on a scale of 1-10 (1 being poor and 10 being amazing)");
    				
    			answer = sc.nextLine();
    				
    			System.out.println("Are there any specific reasons you gave us the score you did today?");
    				
    			answer = sc.nextLine();
    			break;
    		}
    	}
    		
    	break;
    		
    	}
    		
    		
    	//This signifies the end of the review() method, where it will be exited accordingly
    	System.out.println("Thank you for participating in our service review! Do you have any last comments/suggestions you'd like to make?");
    		
    	answer = sc.nextLine();
    	sc.close();
    	return;
    }
}