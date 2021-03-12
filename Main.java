package chatBot;

import java.text.SimpleDateFormat;
import java.util.*;
public class Main extends synonymAPI   {
	
	
	
	static PriorityQueue<patient> patientQ = new PriorityQueue<>();
	static patient p1 = new patient("Jon","Jones","2020",2);
	static patient p2 = new patient("Daniel","Cormier","2020",3);
	static patient p3 = new patient("Michael","Bisping","2020",4);

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
		
		
		// define the strings
		int pain =0;
		String dob ="";
		boolean isValid= false;
		String fName = "";
		String sName= "";
		String answer ="";
		String[] positive = new String[50];
		positive = synonyms("yes");
		String[] negative = new String[50];
		negative = synonyms("no");
		// this is the first level of conversation where we ask if the customer has an appointment 
		double level=0;
		
		//start the conversation
		System.out.println("Hello, thanks for contacting our clinic, do you have an appointment booked already? (Type OUT to exit)");
		
		//if the answer is OUT we quit else we keep going
		while (!answer.equalsIgnoreCase("OUT") && level==0) {
			answer = sc.nextLine(); //get an input
			
			//if input is "out" break the loop
			if (answer.equalsIgnoreCase("OUT")) 
				break;
			
			// we iterate through all the words in the array of positives and if we have a match we go to level 2 
			for(String positives:positive) {
				
				if (answer.equalsIgnoreCase("yes")||answer.matches("(.*)"+positives+"(.*)")){
					System.out.println("Perfect what is your appointment date ? (DD/MM/YYYY)");
					level=10;
				break;
				}
			}
			
			// if we have a negation we go to level 0 and book an appointment 
				  for(String negatives:negative) {
						
						if (answer.equalsIgnoreCase("no")||answer.matches("(.*)"+negatives+"(.*)")){
							System.out.println("Sorry to hear that let's get you an appointment booked !");
						
						level++;
						}
						break;
					}
				
			}
	   
		// this is level 1 where the client still doesn't have an appointment and needs to get it booked
		while (!answer.equalsIgnoreCase("OUT") && level==1) {
			
			
			
			if (answer.equalsIgnoreCase("OUT")) { //if input is "out" break the loop
				
				break;
			}

			System.out.println("Please enter your first name below: ");
			
			fName = sc.nextLine(); 
			
			fName.trim();
			fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
			if(fName.equalsIgnoreCase("OUT")) {
				break;
			}
			
			isValid= validate(fName);
			if (!isValid) {
				System.out.println("Sorry your input wasn't valid. Try that again");
			}
			else {
			
				System.out.println("Thanks " + fName + ", what is your family name ?");
				sName = sc.nextLine();
				if(sName.equalsIgnoreCase("OUT")) {
					break;
				}
				isValid = validate(sName);
				if (!isValid) {
					System.out.println("Sorry your input wasn't valid. Try that again");
				}
				else { System.out.println("Thanks for that info, let's move on to your date of birth: ");
					level=2;}
				
			}
		
		
			
			}
			
			
		
		
		
			
			
			

			//go into level 2 of the conversation to get the age
			
			while (!answer.equalsIgnoreCase("OUT") && level==2) {
				
				answer = sc.nextLine(); //get an input
				
				
				if (answer.equalsIgnoreCase("OUT")) { //if input is "out" break the loop
					break;
				}
				
		// TODO : get the user date of birth 
				dob = answer;
			System.out.println("Thanks, what if you had to describe your level of pain from 1 to 10 what would it be ?");
			
			level++;
			
			
			
		}
			
			
			//go into level 3 of the conversation to get the level of pain(priority)
			
			while (!answer.equalsIgnoreCase("OUT") && level==3) {

				
				try {
				pain = sc.nextInt(); }
				catch( Exception e ){
					System.out.println("Please enter an integer from 1-10");
				}
				
				level++;
				
			
		}
			
			//go into level 10 of the conversation if the appoitment is already booked and give confirmatrion message
			
			while (!answer.equalsIgnoreCase("OUT") && level==10) {
				
				answer = sc.next(); //get an input
				
				
				if (answer.equalsIgnoreCase("OUT")) { //if input is "out" break the loop
					break;
				}
				
				if(answer.matches("(.*)2021")){ //if answer matches a date format go to level 3 
					System.out.println("The appointment date has been verified. Please verify your name:");
						level++;
						break;
					
				}
				
				else {
					System.out.println("please enter a valid date");
				}
				
				
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
		
		System.out.println("Chat has now ended.  Thanks for the chat!");
		
	

}
}