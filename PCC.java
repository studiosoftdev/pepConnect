import java.io.*;
import java.net.*;

public class PCC{

	public static void main(String[] args){
		System.out.println("Welcome to pepConnect!\nEnter username: ");
		String uname = System.console().readLine();
		uname = "[" + uname + "]";
		try{      
			Socket s = new Socket("localhost", 8086);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //use this to send data to server
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //will use this to print out our received server data
			String msg = System.console().readLine();
			dos.writeUTF(uname);  
			dos.flush();  //send the message
			while(!msg.equals("!quit")){
				System.out.print("Type: ");
				msg = System.console().readLine();
				dos.writeUTF(msg);
				dos.flush();
			}
			dos.close();  
			s.close();  
		} catch(Exception e) {System.out.println(e);}  
	}
}