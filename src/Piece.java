public class Piece {

  Boolean color; // true for white
  Location loc;

  public Boolean isBlack() {
    return !color;
  }
  public Boolean isWhite() {
    return color;
  }
  public Location getLoc() {
    return loc;
  }
  public void setLoc(Location _loc) {
    loc = _loc;
  }
}
