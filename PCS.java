import java.io.*;
import java.net.*;
import java.time.LocalDateTime; //add time to msg
import java.time.format.DateTimeFormatter; //so it doesnt look terrible

public class PCS{
	ServerSocket ss;
	boolean serverOn = true;

	PCS () throws InterruptedException{
		try{
			ss = new ServerSocket(8086); //setup our server socket over port 8086
		} catch (Exception e){
			System.out.println(e);
		}
		while(serverOn){
			try{  
				Socket s = ss.accept(); //establishes connection   
				ClientSock t = new ClientSock(s); //start new thread to handle each connection
				t.start();
			} catch (Exception e) {
				System.out.println(e);
			}
			Thread.sleep(500);
		}
		try{
			ss.close();
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(-1);
		}
	}


	class ClientSock extends Thread{
		Socket cs; //client socket

		ClientSock(Socket s){
			cs = s; //takes socket from connection
		}

		public void run(){
			try{
				//do stuff
				DataInputStream dis = new DataInputStream(cs.getInputStream()); //input data with DIS using the input stream (console)
				DataOutputStream dos = new DataOutputStream(cs.getOutputStream()); //use this to send data to client
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
							System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + uname + " has left.");
							dos.writeUTF("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + uname + " has left.");
							dos.flush();
							break;
						}
					}
				} 
				dis.close();
				cs.close();
			} catch (Exception e){
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{
		PCS pcs = new PCS();
	}
}