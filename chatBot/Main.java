package chatBot;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends synonymAPI   {
		
	static PriorityQueue<patient> patientQ = new PriorityQueue<>();
	static patient p1 = new patient("Jon","Jones","2020",2);
	static patient p2 = new patient("Daniel","Cormier","2020",3);
	static patient p3 = new patient("Michael","Bisping","2020",4);

	
	//The review method allows the bot to ask the user many questions about their experience while differentiating between yes/no answers to specific questions
	//This allows our bot to respond slightly differently depending on the question asked prior.
		
	static String showListWith(patient p){
		String results = " ";
		int counter = 0;
		patientQ.add(p1);
		patientQ.add(p2);
		patientQ.add(p3);
		patientQ.add(p);
		Iterator<patient> itr = patientQ.iterator();

		while(itr.hasNext()) {
			counter++;
			 results=("Week " + counter + ", Patient Name: "+patientQ.peek().getFirst_name()+", Patient Last Name:  "+patientQ.poll().getLast_name());
			
		}
		return results;
	}
	
	static boolean validate(String first) {
	if(first.length()>1 && first.length()<17) {
				
			
			return true;
			
			}
			
			
			else {
				return false;
				}
		
	}
	/*
		 * The point of this chatbot is to verify an appointment or book an appointment if no appointment is
		 * present , levels of conversations are used to control the flow of conversation
		 * build is still in early production.
		 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		// define the strings. TODO store data in csv instead of multiple strings
		boolean conversation = true; 
		
		patient user = new patient();
		int pain =0;
		String dob ="";
		boolean isValid= false;
		String question = "Do you have an appointent?";
		JFrame f;
		f = new JFrame();
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
		
		int level=0;
		
		
		
		//System.out.println("Hello, thanks for contacting our clinic (Type OUT to exit)");
		JOptionPane.showMessageDialog(f, "Hello, thanks for contacting our clinic (Type OUT to exit)", "ChatBot", level);
		// main conversation loop
		while (conversation) {
			answer = JOptionPane.showInputDialog(""+question+"");
			
			if(answer.equalsIgnoreCase("OUT")) {
				level = -1;
				break;
			}
			
		switch(level) {// Switch statement that determines levels of dialogue during main conversation
		
		case 0:// this is the first level of conversation where we ask if the customer has an appointment 
			// we iterate through all the words in the array of positives and if we have a match we go to level 1 
			for(String positives:positive) {

				if (answer.matches("(.*)yes(.*)")||answer.matches("(.*)"+positives+"(.*)")){
					question=("Perfect what is your appointment date ? (DD/MM/YYYY)");
					level=30;
				break;
				}
			}	
			// if we have a negative we go to level 0 and book an appointment 
			for(String negatives:negative) {
						
				if (answer.matches("(.*)no(.*)")||answer.matches("(.*)"+negatives+"(.*)")){
					JOptionPane.showMessageDialog(f, "Sorry to hear that let's get you an appointment booked !", "Book An Appointment", level, null);
					
					level =1;
					question = "Please enter your first name below: ";
					}
					break;
				}
				break;
			
				


		case 1: //Gets First name
			fName = answer;
			fName.trim();
			fName = fName.substring(0, 1).toUpperCase() + fName.substring(1).toLowerCase();
			
			isValid= validate(fName);
			if (!isValid) {
				JOptionPane.showMessageDialog(f,"Sorry your input wasn't valid. Try that again");
			}else {
				question=("Thanks " + fName + ", what is your family name ?");
				level =2;
				user.setFirst_name(fName);
			}
			break;
			
		case 2: // Gets Last Name
			sName = answer;
			isValid = validate(sName);
			if (!isValid) {
				JOptionPane.showMessageDialog(f, "Sorry your input wasn't valid. Try that again", "Error", level, null);
			}
			else { question=("Thanks for that info, let's move on to your date of birth: ");
			user.setLast_name(sName);
				level=3;
				}
			break;
			
		case 3: // Gets Date of birth
			dob = answer;
			if(false) {
				// !dob.isValid TODO: verify date of birth 
				JOptionPane.showMessageDialog(f, "Please enter a valid birth date", "Wrong Date", level, null);
			}
			else{
			question=("Thanks, if you had to describe your level of pain from 1 to 10 what would it be?");	
			user.setBirthdate(dob);
			level = 4;
			}
			break;
			
		case 4: // Gets pain level
		try{
			pain = Integer.parseInt(answer); }
			catch(Exception e ){JOptionPane.showMessageDialog(f, "Sorry, something went wrong please try again.", "Error", level, null);
			break;}
			if(pain>10||pain<1) {
				JOptionPane.showMessageDialog(f, "Please enter an integer from 1-10", "Error", level, null);
			}else {	
				user.setPriority(pain);
				level=5;
				question=("Can you give a brief description of your symptoms?");
				}
			break;
			
		case 5: // Symptoms
			symptomsSentence = answer;
			level =6;
			question=("If you are having multiple symptoms, which is bothering you the most? (if not please type 'none')");
			break;
			
		case 6: //Which is the worse symptom
			worseSymptom = answer;
			JOptionPane.showMessageDialog(f, "If you are having chest pains, trouble breathing, severe bleeding, or extreme dizziness, please stop using this bot and Call 911.");
			question=("How many days have you experienced these symptoms?");
			level =7;
			break;
			
		case 7: // get duration of symptoms
		try{
			durationOfSymptoms = Integer.parseInt(answer);}
			catch (Exception e ){JOptionPane.showMessageDialog(f, "Sorry, something went wrong please enter the number of days again.");break;}
			question=("Okay. Would you prefer a male or female doctor?");
			level =8;
			break;
			
		case 8:// gets dr sex/gender preference
			drSexPreference = answer;
			if(!(drSexPreference.matches("male(.*)") || drSexPreference.matches("female(.*)"))) {
				JOptionPane.showMessageDialog(f, "Please answer either 'male' or 'female'");
			}else {	
				question=("Sounds good. Do you have a family doctor?" );
				level =9;	
			}
			break;
			
		case 9: // branch for get family doctor if no family doctor end chat
		
			if(answer.matches("(.*)no(.*)")) {
				String Plist =showListWith(user);
				System.out.println(Plist);
				JOptionPane.showMessageDialog(f, "Okay, thank you for specifying that. We have all of the information that we need.");
				
				JOptionPane.showMessageDialog(f, "Based on our conversation, I ranked you in the following order, please bring ID when you come in to the clinic.");
				JOptionPane.showMessageDialog(f, Plist);
				conversation= false;
				break;
				
			}
			else if(answer.matches("(.*)yes(.*)")) {
				level =10;
				question=("What is your family doctors name?");
			}
			
			break;
			
		case 10: // get family doctors name
			familyDoctor = answer;
			if(!familyDoctor.toLowerCase().matches("dr.(.*)")) {
				JOptionPane.showMessageDialog(f, "Please enter a valid family doctors name (Dr. ..)");
			}else {
				JOptionPane.showMessageDialog(f,"Based on our conversation, I ranked you in the following order, please bring ID when you come in to the clinic.");
				JOptionPane.showMessageDialog(f,showListWith(user));
				
				JOptionPane.showMessageDialog(f,"Thank you for using our service.");
					conversation= false;
					break;
			}
			
			break;
			
		case 11:// extra method in case we add more.
			break;
			
		case 30: // TODO: Add more verfication 
			if(answer.matches("(.*)2021")){ //if answer matches a date format go to level 3 
				JOptionPane.showMessageDialog(f,"The appointment date has been verified. Thank you for confirming using our service.");
					level++;
					conversation = false;
					break;
			}else {
				JOptionPane.showMessageDialog(f,"please enter a valid date");
				}
			break;
			
		//exit case
			case -1 : JOptionPane.showMessageDialog(f,"Thank you for using our service.");
					conversation= false;break;
		}

		//conversation while-loop end
		}	
		
		System.out.println("Would you like to participate in our quick service review and let us know how we did?");
		
		review = sc.nextLine();
		
		// for(String positives:positive) {
			
		// 	if (review.matches("(.*)yes(.*)")||review.matches("(.*)"+positives+"(.*)")){
		// 		review();
		// 		review = "no";
		// 		break;
		// 	}
		// }
		
		Scanner goodbye = new Scanner(System.in);
		
		System.out.println("Chat has now ended. Thanks for the chat! Feel free to say thank you to our bot.");
		end = goodbye.nextLine();
		sc.close();
		goodbye.close();
		System.out.println("Goodbye :)");

		
		
}
}