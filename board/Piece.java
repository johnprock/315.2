public abstract class Piece {
  Location loc;

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
}