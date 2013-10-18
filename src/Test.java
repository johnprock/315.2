import java.io.*;
import java.util.Scanner;


public class Test {
  public static void main(String args[]){
	ServerParser p = new ServerParser();
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
