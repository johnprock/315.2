public class EmptyPiece extends Piece {
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
