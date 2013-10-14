import java.util.Random;

public  class Engine {
  State state;
  Random rand;

  Boolean humanColor;
  Boolean aiColor;
  Boolean turn;
  // false black
  // true white


  Engine(Boolean _color) {
    state = new State();
    humanColor = _color;
    aiColor = !humanColor;
    turn = false;
    rand = new Random();
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
    if(!humanColor) { // move black player
      Piece p = new BlackPiece(_loc);
      if(state.isValidMove(p)) {
        state.addBlack(_loc.getX(), _loc.getY());
        return true;
      }
    } else { // move white player
      Piece p = new WhitePiece(_loc);
      if(state.isValidMove(p)) {
        state.addWhite(_loc.getX(), _loc.getY());
        return true;
      }
    }
    turn = !turn;
    return false;
  }


  private Boolean aiMove() {
    Piece p;
    Location loc;

    if(!aiColor) { // black

      while(true) {
        p = randomBlack();
        loc = p.getLoc();

        if( state.isValidMove(p) ) {
          state.addBlack(loc.getX(), loc.getY());
          return true;
        }
      }
    }
    else { // white

      while(true) {
        p = randomWhite();
        loc = p.getLoc();

        if( state.isValidMove(p) ) {
          state.addWhite(loc.getX(), loc.getY());
          turn = !turn;
          return true;
        }
      }
    }
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

}
