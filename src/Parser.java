import java.io.*;
import java.net.*;

public abstract class Parser {

  Tokenizer tokenizer;
  public Engine e;
  public PrintWriter pw;

  public abstract Boolean parse(String _str);

  protected Boolean parseComment(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    if(token.value.startsWith(";")) {
      pw.println("OK");
      return true;
    }
    else return false;
  }


  protected Boolean parseMove(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token row;
    Token col;   

    if(parseColumn(t)) {
      col = t.nextToken();
      if(parseRow(t)) {
        row = t.nextToken();
        int x = Integer.parseInt(row.value);
        x--;
        int y = convertCol(col.value);
        Location loc = new Location(x,y);
        if(e.move(loc)) {
          Location lastLoc = e.lastMove();
          String rows = Integer.toString(lastLoc.getX()+1);
          String cols = reverseConvertCol(lastLoc.getY());
           
          pw.println(cols + " " + rows);
          return true;
        }
        return false;

      }
    }
    return false;
  }

  protected Boolean parseColumn(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;

    if( val.equals("a") ||
        val.equals("b") ||
        val.equals("c") ||
        val.equals("d") ||
        val.equals("e") ||
        val.equals("f") ||
        val.equals("g") ||
        val.equals("h") ) return true; 
    return false;
  }

  protected Boolean parseRow(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;

    if( val.equals("1") ||
        val.equals("2") ||
        val.equals("3") ||
        val.equals("4") ||
        val.equals("5") ||
        val.equals("6") ||
        val.equals("7") ||
        val.equals("8") ) return true; 
    return false;
  }

  protected int convertCol(String _str) {
    if(_str.equals("a")) return 0;
    if(_str.equals("b")) return 1;
    if(_str.equals("c")) return 2;
    if(_str.equals("d")) return 3;
    if(_str.equals("e")) return 4;
    if(_str.equals("f")) return 5;
    if(_str.equals("g")) return 6;
    if(_str.equals("h")) return 7;
    return -1;
  }

  protected String reverseConvertCol(Integer i) {
    if(i == 0) return "a";
    if(i == 1) return "b";
    if(i == 2) return "c";
    if(i == 3) return "d";
    if(i == 4) return "e";
    if(i == 5) return "f";
    if(i == 6) return "g";
    if(i == 7) return "h";
    return "-1";
  }
}