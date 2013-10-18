import java.util.Random;
import java.util.ArrayList;

public  class Engine {
  State state;
  Random rand;

  static final Boolean black = false;
  static final Boolean white = true;

  Boolean humanColor;
  Boolean aiColor;
  Boolean turn;
  // false black
  // true white

  int depth;
  Heuristic heuristic;

  Engine(Boolean _color) {
    state = new State();
    humanColor = _color;
    aiColor = !humanColor;
    turn = false;
    rand = new Random();
    
    heuristic = new TestHeuristic();
    depth = 1;
  }

  public Boolean isOver() {
    return state.isOver(turn);
  }

  public String draw() {
    return DrawBoard(state);
  }

  public Boolean move(Location _loc) {
    if(isOver()) return false;

    if( humanMove(_loc) ) {
      if( aiMove() ) {
        return true;
      }
    }

    return false;
  }

  private Boolean humanMove(Location _loc) {
    if(humanColor == black) { // move black player
      Piece p = new BlackPiece(_loc);
      if(state.isValidMove(p)) {
        state.addBlack(_loc.getX(), _loc.getY());
        turn = !turn;
        return true;
      }
    } else { // move white player
      Piece p = new WhitePiece(_loc);
      if(state.isValidMove(p)) {
        state.addWhite(_loc.getX(), _loc.getY());
        turn = !turn;
        return true;
      }
    }
    return false;
  }


  public Boolean aiMove() {
    Piece p;
    Location loc;
    ArrayList<State> children;
    double bestValue = 0;
    double val = 0;
    State bestState = state; 

    children = state.getChildren(aiColor);
    for(State child : children) {
      val = minimax(child, depth, true, aiColor); 
      bestValue = Math.max(bestValue, val);
      if(bestValue == val) {
        bestState = child;
      }
    }
     
    state = state;     
    return true;
  }

  private Piece randomBlack() {
    int x = rand.nextInt(8);
    int y = rand.nextInt(8);
    Location loc = new Location(x,y);
    return new BlackPiece(loc);
  }

  private Piece randomWhite() {
    int x = rand.nextInt(8);
    int y = rand.nextInt(8);
    Location loc = new Location(x,y);
    return new WhitePiece(loc);
  }

  private String DrawBoard(State currentState) {
    String newLine = "\n";
    String str = "";
    str += "  _ _ _ _ _ _ _ _\n"; //top of the board

    for (int row = 0; row<8; row++){
      str += ( (row+1) + "|");
      for (int column = 0; column<8; column++)
      {
        if(currentState.getPiece(row,column) != null) {
          if (currentState.getPiece(row,column).isBlack()){
            str += "@|";
          } 
          if (currentState.getPiece(row,column).isWhite()){
            str += "O|";
          }
          if (currentState.getPiece(row,column).isEmpty()) {
            str += "_|";
          } 
        }
      }
      str += "\n";
    }
    str += "  a b c d e f g h\n";//bottom of the board
    return str;
  }

  // algorithm taken largely from wikipedia
  private double minimax(State _state, int _depth, Boolean _player, Boolean _color) {
    double bestValue;
    double val;
    ArrayList<State> children;

    if(depth == 0 || _state.isOver(_color)) {
      return heuristic.evaluate(_state);
    }
    
    if(_player) {
      bestValue = 0;
      children = _state.getChildren(_color);
      for(State child : children) {
        val = minimax(child, depth-1, false, !_color);
        bestValue = Math.max(bestValue, val);
      } 
      return bestValue;
    }
        
    else {
      bestValue = 1;
      children = _state.getChildren(_color);
      for(State child : children) {
        val = minimax(child, depth-1, true, !_color);
        bestValue = Math.min(bestValue, val);
      }
      return bestValue;
    }
  }

  private interface Heuristic {
    public double evaluate(State _state); 
  }

  private class TestHeuristic implements Heuristic {
    public double evaluate(State _state) {
      return .5;
    }
  }
}
