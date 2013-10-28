import java.io.*;
import java.net.Socket;
import java.util.*;
import java.net.UnknownHostException;

public class Client{

    
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

    // used to talk to the server
	public String write(String message) {
		String s = "no response";
        try {

		OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
        pw.println(message);
        s = br.readLine();

        } catch (IOException err) {
			err.printStackTrace();
		}
        return s;
	}
}
