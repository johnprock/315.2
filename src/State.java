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

  Piece getPiece(int _x, int _y) {
    return board[_x][_y];
  }

  void flipPiece(int _x, int _y) {
    board[_x][_y] = board[_x][_y].flip();
  }

  void addBlack(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new BlackPiece(loc);
  }

  void addWhite(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new WhitePiece(loc);
  } 
}
