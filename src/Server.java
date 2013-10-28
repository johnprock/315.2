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
    final int portNumber = 8123;
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
      str = br.readLine();

    if(str.equals("EXIT")) {
      break;
    } else
      
    if(str.equals("DISPLAY")) {
      display = true;
    } else
		
	  if(str.equals("AI-AI")){
		  pw.println("OK");
	  }else
	  
	  
    if( p.parse(str) ) {        
		    //pw.println(*computer move*);
        // response handled by parser
    }
	  
	  
    else {
      pw.println("ILLEGAL");
    }
    
    if(display) {
      // draw board
      if(p.e == null) pw.println("Can't display yet, pick a color");
      else pw.println(p.e.draw());
    }
      
      
    }
    
    pw.close();
    socket.close();
    //scanIn.close();            

  }
}
