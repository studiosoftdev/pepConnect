import java.io.*;
import java.net.*;
import java.time.LocalDateTime; //add time to msg
import java.time.format.DateTimeFormatter; //so it doesnt look terrible

public class PCS {
	public static void main(String[] args){
		try{  
			ServerSocket ss = new ServerSocket(8086); //setup our server socket over port 8086
			Socket s = ss.accept(); //establishes connection   
			DataInputStream dis = new DataInputStream(s.getInputStream()); //input data with DIS using the input stream (console)
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //use this to send data to client
			String uname = (String) dis.readUTF(); //user should input uname as first prompt
			String msg = "";
			System.out.println(uname + " has joined.");
			while(true){
				msg = (String) dis.readUTF();
				if(msg.length() != 0){
					if(!(msg.equals("!quit"))){
						System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + uname + ": " + msg);
						dos.writeUTF("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + uname + ": " + msg);
						dos.flush();
					}
					else{
						break;
					}
				}
			} 
			dis.close();
			s.close();
			ss.close();  
		} catch(Exception e){
			System.out.println(e);
		}
	}
}