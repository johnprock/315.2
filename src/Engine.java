public class Engine {
  State state;
  Boolean turn;
  
  Engine() {
    state = new State();
    turn = true; // dark player moves first
    // false for black
    // true for white
  }

  public Boolean move(Location _loc) {
    if(!turn) { // move black player
      Piece p = new BlackPiece(_loc);
      if(state.isValidMove(p)) {
        state.addBlack(_loc.getX(), _loc.getY());
        return true;
      }
    }
    else { // move white player
      Piece p = new WhitePiece(_loc);
      if(state.isValidMove(p)) {
        state.addWhite(_loc.getX(), _loc.getY());
        return true;
      }
    }
    return false;
  }

  public Boolean isOver() {
    return state.isOver(turn);
  }

  public void aiMove() {
    if(turn) { // black

    }
    else { // white

    }

  }

  public String draw() {
    return DrawBoard(state);
  }

  private String DrawBoard(State currentState) {
    String newLine = "\n";
    String str = "";
    str += "  _ _ _ _ _ _ _ _"; //top of the board

    for (int row = 0; row<8; row++){
      str += (row+"|");
      for (int column = 0; column<8; column++)
      {
        if (currentState.board[row][column]!= null){
          if (currentState.getPiece(row,column).isBlack()){
            str += "@|";
          }
          if (currentState.getPiece(row,column).isWhite()){
            str += "O|";
          }
        }
        else
          str += "_|"; 
      }
    }
    str += "  a b c d e f g h";//bottom of the board
    return str;
  }

}
