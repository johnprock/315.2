import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Test {
  public static void main(String args[]){
    State s = new State();
    Engine e = new Engine(false);
    ArrayList<State> children = new ArrayList<State>();
    children = s.getChildren(true);

    System.out.println(children.size()); 

    for(State child : children) {
      System.out.println(e.DrawBoard(child));
    }
  }
}


