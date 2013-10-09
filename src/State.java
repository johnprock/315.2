public class State {
  Piece[][] board; 

  // regular constructor
  State() {
    board = new Piece[8][8];
  }

  // copy constructor
  State(State state) {
    this.board = state.board;
  }

  public Piece getPiece(int _x, int _y) {
    return board[_x][_y];
  }

  public void flipPiece(int _x, int _y) {
    board[_x][_y] = board[_x][_y].flip();
  }

  public void addBlack(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new BlackPiece(loc);
  }

  public void addWhite(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new WhitePiece(loc);
  }

  public Boolean isValidMove(Piece _piece) {
    return false;
  }

  // returns true if the game is in an end state
  public Boolean isOver() {
    return false;
  } 
}
