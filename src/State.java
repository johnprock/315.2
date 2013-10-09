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

  Piece getPiece(int x, int y) {
    return board[x][y];
  }

  void flipPiece(int x, int y) {
    board[x][y] = board[x][y].flip();
  }

  void addBlack(int x, int y) {
  }

  void addWhite(int x, int y) {
  }
 
}
