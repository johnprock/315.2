public class Location {
  int x;
  int y;
  
  public Location(int _x, int _y) {
    assert _x >= 0; // 8x8 board
    assert _x <= 7;
    assert _y >= 0;
    assert _y <= 7;
    x = _x;
    y = _y;
  }

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
}
