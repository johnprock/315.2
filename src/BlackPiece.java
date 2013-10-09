public class BlackPiece extends Piece {
  BlackPiece(Location _loc) {
    loc = _loc;
  }
  
  // copy constructor copies location
  BlackPiece(Piece _piece) { 
    this.loc = _piece.loc;
  }  

  public Piece flip() {
    return WhitePiece(this);
  } 

  public Boolean isBlack() {
    return true;
  }

  public Boolean isWhite() {
    return false;
  }

  public Boolean isEmpty() {
    return false;
  }
}
