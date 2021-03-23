package chatBot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gui extends JFrame{
	
	int i = 0;
	//String userText = "";
	JTextField textField = new JTextField(50);
	JFrame chat = new JFrame();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<String> bot = new ArrayList<String>();
	
	public gui() {
		//Creating initial defaults required for an operational JFrame
		chat.setSize(1200,900);
		chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Centering the window on the screen
		chat.setLocationRelativeTo(null);
		
		JLabel sendLabel = new JLabel("Enter message here:");
		JButton send = new JButton("SEND");
		JButton clear = new JButton("CLEAR");
		
		send.addActionListener( (e) -> {
			send();
		});
		
		clear.addActionListener( (e) -> {
			clear();
		});
		
		panel1.add(sendLabel);
		panel1.add(textField);
		panel1.add(send);
		panel1.add(clear);
		
		JLabel label = new JLabel("BOT SAYS HI");
		
		panel2.add(label);
		
		chat.getContentPane().add(BorderLayout.SOUTH, panel1);
		chat.getContentPane().add(BorderLayout.CENTER, panel2);
		
		
		chat.setVisible(true);
	}
	
	private void send() {
		user.add(textField.getText());
		System.out.println(user.get(i));
		
		JLabel temp = new JLabel(user.get(i) + "\n");
		panel2.add(temp);
		
		chat.getContentPane().add(BorderLayout.CENTER, panel2);
		chat.setVisible(true);
		
		i++;
		clear();
	}
	
	private void clear() {
		textField.setText("");
	}
	
	public static void main(String[] args) {
		
		new gui();
		
	}

}
