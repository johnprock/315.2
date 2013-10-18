public class EmptyPiece extends Piece {
  EmptyPiece(Location _loc) {
    loc = _loc;
  }  
  
  EmptyPiece(Piece _piece) {
    this.loc = _piece.loc;
  }

  public Piece copy() {
    return new EmptyPiece(this);
  }

  public Piece flip() {
    return this;
  } 
 
  public Boolean isBlack() {
    return false;
  }
  
  public Boolean isWhite() {
    return false;
  }
 
  public Boolean isEmpty() {
    return true;
  }
}
