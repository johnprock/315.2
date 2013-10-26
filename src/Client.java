import java.io.*;
import java.net.Socket;
import java.util.*;
import java.net.UnknownHostException;

public class Client{

	Display d = new Display("test");
    

	public static void main(String args[]){
		ServerParser parser;
        parser = new ServerParser();

		System.out.println("Server IP: ");
		Scanner in = new Scanner(System.in);
		final String host = in.nextLine();
		//final String host = "localhost";
		final int portNumber = 8123;
	    try{
            System.out.println("Creating socket to '" + host + "' on port " + portNumber);

        // main loop
		while (true) {
			Socket socket = new Socket(host, portNumber);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("server says:" + br.readLine());

			BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			String userInput = userInputBR.readLine();

			out.println(userInput);

			System.out.println("server says:" + br.readLine());

			if ("exit".equalsIgnoreCase(userInput)) {
				socket.close();
				break;
		    	}
	    	}
        }catch (UnknownHostException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
}
