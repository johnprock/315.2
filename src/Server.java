public class Server {
  public static void main(String[] args) {
    State s = new State();
    if(s.isOver(true)) {
      System.out.println("game over");
    }
  }
}
