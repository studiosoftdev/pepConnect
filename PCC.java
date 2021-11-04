import java.io.*;
import java.net.*;

public class PCC{

	public static void main(String[] args){
		boolean quit = false;
		System.out.println("Welcome to pepConnect!\nEnter username: ");
		String uname = System.console().readLine();
		uname = "[" + uname + "]";
		try{      
			Socket s = new Socket("localhost", 8086);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //use this to send data to server
			DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //will use this to print out our received server data
			String msg = uname;
			dos.writeUTF(uname);  
			dos.flush();  //send the message
			while(!quit){
				if(msg.equals("!quit")){ //dont wanna quit the loop instnatly since we still wanna send the message to the server so it can tell everyone we left
					quit = true;
				}
				else{ //if the user hasnt quit, ask for them to write their message
					if (dis.available() != 0) {
        				String message = dis.readUTF();
        				System.out.println(message);
    				}
					System.out.print("Type: ");
					msg = System.console().readLine();
				}
				dos.writeUTF(msg);
				dos.flush();
			}
			dos.close();  
			s.close();  
		} catch(Exception e) {
			System.out.println(e);
		}  
	}
}