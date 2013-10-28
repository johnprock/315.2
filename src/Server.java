import java.io.*;
//import java.util.Scanner;
import java.net.*;
public class Server {
  
  public static void main(String[] args)throws IOException {
   
    ServerParser p = new ServerParser();
    String str;
    Boolean display = false;

    //Scanner scanIn = new Scanner(System.in);
    InetAddress thisIp =InetAddress.getLocalHost();
    System.out.println("Server IP:"+thisIp.getHostAddress());
    // read command line args..
    final int portNumber = Integer.parseInt(args[0]);
  	System.out.println("Creating server socket on port " + portNumber);
  	ServerSocket serverSocket = new ServerSocket(portNumber);
    Socket socket = serverSocket.accept();
    OutputStream os = socket.getOutputStream();
    PrintWriter pw = new PrintWriter(os, true);
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    pw.println("WELCOME");
    p.pw = pw;

    // main loop
    while(true) {
      System.out.println("loop");
      str = br.readLine();

      if(p.e.isOver()) {
        pw.println("GAME OVER");
      }

      if(str.equals("EXIT")) {
        break;
      } 
      
      else if(str.equals("DISPLAY")) {
        display = true;
      } 
		
	    else if(str.equals("AI-AI")){
		    pw.println("OK");
        // implement ai-ai
        // act as a client
        String hostname;
        String port;
        hostname = br.readLine();
        port = br.readLine();
        aiVai(hostname, port, pw, br);
	    }

      else if( p.parse(str) ) {        
		      //pw.println(*computer move*);
          // response handled by parser
      }
	  
      else {
        pw.println("ILLEGAL");
        System.out.println("ILLEGAL");
      }
    
      if(display) {
        // draw board
        if(p.e == null) pw.println("Can't display yet, pick a color");
        else pw.println(p.e.draw());
      }  
      
    }
    
    // clean up
    pw.close();
    socket.close();
    //scanIn.close();            

  }

  // play an ai versus ai game
  private static void aiVai(String hostname, String port, PrintWriter pw, BufferedReader br) {
    Client c = new Client();
    c.connect(hostname, Integer.parseInt(port));

    c.write("BLACK");
    c.write("HUMAN-AI");
    c.write("EASY");

    pw = c.out;
    br = c.br;

    pw.println("c 4");

  }
}
