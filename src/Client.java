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
			return;

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
        out.println(message);

        System.out.println(message);
        s = br.readLine();

        } catch (IOException err) {
			err.printStackTrace();
		}
		System.out.println(s);
		return s;
	}
}
