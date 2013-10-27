import java.io.*;
import java.net.Socket;
import java.util.*;
import java.net.UnknownHostException;

public class Client{

	Display d = new Display("test");
    
	Socket socket;
    PrintWriter out;
    BufferedReader br;
	
	public void connect(String host, int portNumber){
		String userInput;
		try{
            System.out.println("Creating socket to '" + host + "' on port " + portNumber);
			socket = new Socket(host, portNumber);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(br.readLine());
			while(true){
			
				Scanner in = new Scanner(System.in);
				userInput = in.nextLine();
				 if ("exit".equalsIgnoreCase(userInput)) {
					 socket.close();
					 break;
				 }
				//br.readLine();
				out.println(userInput);
				System.out.println(br.readLine());
				//String sCurrentLine= null;
				//while((sCurrentLine =br.readLine()) != null){
						//System.out.println(sCurrentLine );
				//	}
			}
			
			
		} catch (UnknownHostException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
	
	}
	public static void main(String args[]){
		
		System.out.println("Server IP: ");
		Scanner in = new Scanner(System.in);
		final String host = in.nextLine();
		//final String host = "localhost";
		final int portNumber = 8123;
	    Client client=new Client();
		client.connect(host,portNumber);

			// BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			// String userInput = userInputBR.readLine();

			// out.println(userInput);

			// System.out.println("server says:" + br.readLine());

			// if ("exit".equalsIgnoreCase(userInput)) {
				// socket.close();
				// break;
		    	// }
	    	// }
       
	}
}
