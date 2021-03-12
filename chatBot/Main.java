package chatBot;

import java.text.SimpleDateFormat;
import java.util.*;
public class Main extends synonymAPI   {
	
	
	
	static PriorityQueue<patient> patientQ = new PriorityQueue<>();
	static patient p1 = new patient("Jon","Jones","2020",2);
	static patient p2 = new patient("Daniel","Cormier","2020",3);
	static patient p3 = new patient("Michael","Bisping","2020",4);
//test
	static void review() {
		String yn = "";
		String answer = "";
		String[] positive = new String[50];
		positive = synonyms("yes");
		String[] negative = new String[50];
		negative = synonyms("no");
		
		Scanner sc = new Scanner(System.in);
		
		while (!answer.equalsIgnoreCase("OUT")||!yn.equalsIgnoreCase("OUT")){
			
		System.out.println("Quick notice before we begin: Do you mind if we attach your personal information along with your review? (Yes to stay anonymous; No to allow us to associate this with your personal information");
		yn = sc.nextLine();
		
		
		System.out.println("Thanks for participating in our review! Have you been here with us before today? (Type OUT to exit)");
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("Great to hear! Do you think you'll come back?");
			}
			else {
				System.out.println("There's a first for everything! Do you think you'll come back?");
			}
		}
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("Awesome! Would you recommend our service to friends and family?");
			}
			else {
				System.out.println("Oh no! ): Would you recommend our service to friends and family?");
			}
		}
		
		yn = sc.nextLine();
		
		System.out.println("Are there any recommendations you'd like to make in order for us to make your experience better next time? (Type OUT to exit)");
		
		answer = sc.nextLine();
		
		
		System.out.println("Were you able to accomplish what you came here to do today?");
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("Great! Is there any way we could make this easier the next time?");
			}
			else {
				System.out.println("Is there any way we could fix this for you?");
			}
		}
		
		answer = sc.nextLine();
		
		System.out.println("We'll be sure to take any suggestions into consideration!");
		System.out.println("Did you feel you were safe while you were in our care? (Type OUT to exit)");
		
		yn = sc.nextLine();
		
		System.out.println("Do you mind explaining how or why you felt this way?");
		
		answer = sc.nextLine();
		
		System.out.println("Thank you for providing us with this information.");
		System.out.println("Did it feel like you were talking with a real person today?");
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("What made you feel this way?");
			}
			else {
				System.out.println("How can we improve this?");
			}
		}
		
		answer = sc.nextLine();
		
		System.out.println("This \'bot\' thanks you for your cooperation! :)");
		System.out.println("Do you have any general complaints at all from you experience here? (Type OUT to exit)");
		
		answer = sc.nextLine();
		
		System.out.println("Thank you for letting us know.");
		System.out.println("We've reached near the halfway mark in this review. Would you like to continue?");
		
		yn = sc.nextLine();
		
		for(String negatives:negative) {
			
			if (yn.equalsIgnoreCase("no")||yn.matches("(.*)"+negatives+"(.*)")){
				answer = "OUT";
			}
		}
		
		System.out.println("Great! We'll continue on with the service review. You're almost done!");
		System.out.println("Did you feel our information gathering process was efficient?");
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("Great! Do you have any suggestions for a more efficient method?");
			}
			else {
				System.out.println("Do you have any suggestions to create a speedier experience for you next time?");
			}
		}
		
		answer = sc.nextLine();
		
		System.out.println("Would you be willing to rate us today? (Type OUT to exit)");
		
		yn = sc.nextLine();
		
		for(String positives:positive) {
			
			if (yn.equalsIgnoreCase("yes")||yn.matches("(.*)"+positives+"(.*)")){
				System.out.println("Rate our service today on a scale of 1-10 (1 being poor and 10 being amazing)");
				
				answer = sc.nextLine();
				
				System.out.println("Are there any specific reasons you gave us the score you did today?");
				
				answer = sc.nextLine();
			}
		}
		
		}
		
		System.out.println("Thank you for participating in our service review! Do you have any last comments/suggestions you'd like to make?");
		
		answer = sc.nextLine();
	}
	
	static boolean validate(String first) {
	if(first.length()>1 && first.length()<17) {
				
			
			return true;
			
			}
			
			
			else {
				return false;
				}
		
	}
	public static void main(String[] args) {
		/*
		 * The point of this chatbot is to verify an appointment or book an appointment if no appointment is
		 * present , levels of conversations are used to control the flow of conversation
		 * build still early
		 * 
		 * 
		 * 
		 */
		Scanner sc = new Scanner(System.in);
		
		
		// define the strings. TODO store data in csv instead of multiple strings
		boolean conversation = true; 
		boolean proceed =false;
		int pain =0;
		String dob ="";
		boolean isValid= false;
		String fName = "";
		String sName= "";
		String answer ="";
		String end = "";
		String review = "";
		String symptomsSentence ="";
		String worseSymptom = "";
		int durationOfSymptoms = 0;
		String drSexPreference ="";
		String familyDoctor ="";
		String[] positive = new String[50];
		positive = synonyms("yes");
		String[] negative = new String[50];
		negative = synonyms("no");
		// this is the first level of conversation where we ask if the customer has an appointment 
		int level=0;
		
		
		System.out.println("Hello, thanks for contacting our clinic, do you have an appointment booked already? (Type OUT to exit)");
		
		while (conversation) {
			answer = sc.nextLine();
			
			if(answer.equalsIgnoreCase("OUT")) {
				break;
			}
		switch(level) {
		case 0:
			// we iterate through all the words in the array of positives and if we have a match we go to level 2 
			for(String positives:positive) {
				
				if (answer.equalsIgnoreCase("yes")||answer.matches("(.*)"+positives+"(.*)")){
					System.out.println("Perfect what is your appointment date ? (DD/MM/YYYY)");
					level=30;
				break;
				}
			}
			
			// if we have a negation we go to level 0 and book an appointment 
			for(String negatives:negative) {
						
				if (answer.equalsIgnoreCase("no")||answer.matches("(.*)"+negatives+"(.*)")){
					System.out.println("Sorry to hear that let's get you an appointment booked !");
					
					level =1;
					System.out.println("Please enter your first name below: ");
					}
					break;
				}
				break;
		case 1: // First name
			fName = answer;
			fName.trim();
			fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
			
			isValid= validate(fName);
			if (!isValid) {
				System.out.println("Sorry your input wasn't valid. Try that again");
			}else {
				System.out.println("Thanks " + fName + ", what is your family name ?");
				level =2;
			}
			break;
		case 2: // Last Name
			sName = answer;
			isValid = validate(sName);
			if (!isValid) {
				System.out.println("Sorry your input wasn't valid. Try that again");
			}
			else { System.out.println("Thanks for that info, let's move on to your date of birth: ");
				level=3;
				}
			break;
		case 3: // Date of birth
			dob = answer;
			if(false) {
				// !dob.isValid TODO
				System.out.println("Please enter a valid birth date");
			}
			else{
			System.out.println("Thanks, if you had to describe your level of pain from 1 to 10 what would it be?");	
			level =4;
			}
			break;
			
		case 4: // Amount of pain
			pain = Integer.parseInt(answer); 
			if(pain>10||pain<1) {
				System.out.println("Please enter an integer from 1-10");
			}else {	
				level=5;
				System.out.println("Can you give a brief description of your symptoms?");
				}
			break;
		case 5: // Symptoms
			symptomsSentence = answer;
			level =6;
			System.out.println("If you are having multiple symptoms, which is bothering you the most?");
			break;
		case 6: //Which is the worse symptom
			worseSymptom = answer;
			System.out.println("If you are having chest pains, trouble breathing, severe bleeding, or extreme dizziness, please stop using this bot and Call 911.");
			System.out.println("How many days have you experienced these symptoms?");
			level =7;
			break;
		case 7: // get duration of symptoms
			durationOfSymptoms = Integer.parseInt(answer);
			System.out.println("Okay. Would you prefer a male or female doctor?");
			level =8;
		case 8:// gets dr sex preference, please dont cancel me TODO remove <-
			drSexPreference = answer;
			if(!(drSexPreference.equalsIgnoreCase("male") || drSexPreference.equalsIgnoreCase("female"))) {
				System.out.println("Please answer either male or female");
			}else {
				System.out.println("Sounds good. Do you have a family doctor?" );
				level =9;		
			}
			break;
		case 9: // branch for get family doctor
			if(answer.equalsIgnoreCase("no")) {
				level=10;
				System.out.println("Okay. How many days have you experienced these symptoms?");
			}else if(answer.equalsIgnoreCase("yes")) {
				level =10;
				System.out.println("What is your family doctors name?");
			}
			System.out.println();
			break;
		case 10: // get family doctors name
			familyDoctor = answer;
			if(!familyDoctor.toLowerCase().matches("dr.(.*)")) {
				System.out.println("Please enter a valid family doctors name (Dr. ..)");
			}else {
				System.out.println("Thank you for using our service.");
				conversation = false;
				break;
			}
			System.out.println();
			break;
		case 11:// extra method in case we add more.
			break;
		case 30: // TODO Add more verfication 
			if(answer.matches("(.*)2021")){ //if answer matches a date format go to level 3 
				System.out.println("The appointment date has been verified. Thank you for confirming using our service.");
					level++;
					conversation = false;
					break;
			}
			
			else {
				System.out.println("please enter a valid date");
			}
			break;
			
		}
		
	
		//convo loop end
		}
		
		
		//after we get all the info we create a new patient and place them in priority que
		
		patient user = new patient(fName,sName,dob,pain);
		
		patientQ.add(p1);
		patientQ.add(p2);
		patientQ.add(p3);
		patientQ.add(user);
		Iterator<patient> itr = patientQ.iterator();
		while(itr.hasNext()) {
			System.out.println("Patient: "+patientQ.poll().getFirst_name());
		}
		
		
		// let the user now where they stand in the queue
		
		System.out.println("Would you like to participate in our quick service review and let us know how we did?");
		
		review = sc.nextLine();
		
		for(String positives:positive) {
			
			if (review.equalsIgnoreCase("yes")||review.matches("(.*)"+positives+"(.*)")){
				review();
			}
		}
		
		System.out.println("Chat has now ended. Thanks for the chat! Feel free to say thank you to our bot.");
		
		end = sc.nextLine();
		
		System.out.println("Goodbye :)");
		
		
	

}
}