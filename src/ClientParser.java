public class ClientParser extends Parser {

  ClientParser() {
    
  }

  public Boolean parse(String _str) {
    tokenizer = new Tokenizer(_str);
    return parseAck();
  }

  private Boolean parseAck() {
    return parseWelcome(tokenizer) ||
           parseOk(tokenizer)      ||
           parseMove(tokenizer)    ||
           parseIllegal(tokenizer) ||
           parseComment(tokenizer) ;
  }

  private Boolean parseWelcome(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;  

    if(val.equals("WELCOME")) {
      ret = true;
    }
    return ret;
  }

  private Boolean parseOk(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;  

    if(val.equals("OK")) {
      ret = true;
    }
    return ret;
  }

  private Boolean parseIllegal(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;  

    if(val.equals("ILLEGAL")) {
      ret = true;
    }
    return ret;
  }

  // updates State s with move
  public Boolean parseClientMove(String str, State s, Boolean black) {
    Tokenizer t = new Tokenizer(str);
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
        if(black) {
          s.addBlack(loc.getX(), loc.getY());
          return true;
        }
        else {
          s.addWhite(loc.getX(), loc.getY());
          return true;
        }
      }
    }
    return false;

  }

}
