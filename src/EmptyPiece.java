public class EmptyPiece extends Piece {
  EmptyPiece(Location _loc) {
    loc = _loc;
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
