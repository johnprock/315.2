public abstract class Piece {
  Location loc;
 
  public abstract Piece copy();

  public abstract Boolean isBlack();
 
  public abstract Boolean isWhite();
 
  public abstract Boolean isEmpty();

  public abstract Piece flip(); 

  public Location getLoc() {
    return loc;
  }
  public void setLoc(Location _loc) {
    loc = _loc;
  }

  public Boolean sameColor(Piece _piece) {
    Boolean bothBlack = this.isBlack() && _piece.isBlack();
    Boolean bothWhite = this.isWhite() && _piece.isWhite();
    return bothBlack || bothWhite;
  }
}
