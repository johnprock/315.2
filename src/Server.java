import java.io.*;
import java.util.Scanner;

public class Server {
  public static void main(String[] args) {
   
    State s = new State();
    Engine e = new Engine();
    ServerParser p = new ServerParser();
    ClientParser c = new ClientParser();
    String str;

    Scanner scanIn = new Scanner(System.in);
    
    while(true) {
      System.out.print("Parser> ");
      str = scanIn.nextLine();
    
      if(str.equals("quit")) break;  
    
      if(p.parse(str)) {
        System.out.println("Parse succeded.");
      } 
      else {
        System.out.println("Parse failed.");
      }
    }
    
    scanIn.close();            

  }
}
