public  class Engine {
  State state;
  Boolean turn;
  // black is false
  // white is true

  Engine() {
    state = new State();
    turn = false;
  }

  public Boolean isOver() {
    return state.isOver(turn);
  }

  public String draw() {
    return DrawBoard(state);
  }

  public Boolean move(Location _loc) {
    if( humanMove(_loc) ) {
      aiMove();
      return true;
    }

    return false;
  }

  private Boolean humanMove(Location _loc) {
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


  private void aiMove() {
    if(!turn) { // black

    }
    else { // white

    }

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
