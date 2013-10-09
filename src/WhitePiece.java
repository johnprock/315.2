public class WhitePiece extends Piece {
  WhitePiece(Location _loc) {
    loc = _loc;
  }
  
  // copy constructor copies location
  WhitePiece(Piece _piece) { 
    this.loc = _piece.loc;
  } 

  public Piece flip() {
    return BlackPiece(this);
  }
  
  public Boolean isBlack() {
    return false;
  }

  public Boolean isWhite() {
    return true;
  }

  public Boolean isEmpty() {
    return false;
  }
}
