import java.io.*;
//import java.util.Scanner;
import java.net.*;
public class Server {

  public static void main(String[] args)throws IOException {
   
    Engine e = new Engine();
    ServerParser p = new ServerParser();
    String str;

    //Scanner scanIn = new Scanner(System.in);
    InetAddress thisIp =InetAddress.getLocalHost();
    System.out.println("Server IP:"+thisIp.getHostAddress());
    final int portNumber = 8123;
	System.out.println("Creating server socket on port " + portNumber);
	ServerSocket serverSocket = new ServerSocket(portNumber);
    Socket socket = serverSocket.accept();
    OutputStream os = socket.getOutputStream();
    PrintWriter pw = new PrintWriter(os, true);
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    pw.println("WELCOME");


    // main loop
    while(true) {

        pw.println("whats ur move? ");
		str = br.readLine();
		pw.println("test move, " + str);
		//System.out.print("Parser> ");
        //str = scanIn.nextLine();
 
        if(str.equals("quit")) break;  
    
        if(p.parse(str)) {
			System.out.println("Parse succeded.");
			pw.println("move, " + str);
			System.out.println("move:" + str);
		} 
      else {
			System.out.println("Parse failed.");
		}

    }
    
    pw.close();
    socket.close();
    //scanIn.close();            

  }
}
