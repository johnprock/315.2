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

}
