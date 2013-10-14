public class ServerParser {
  
  Tokenizer tokenizer;


  public Boolean parse(String _str) {
    tokenizer = new Tokenizer(_str);
    return parseExpr();
  }

  public Boolean parseExpr() {
    return parseCommand(tokenizer) || parseMove(tokenizer) || parseComment(tokenizer);
  }

  public Boolean parseCommand(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;    

    Token server;
    Token port;
    Token difficulty1;
    Token difficulty2;

    
    if(val.equals("EXIT")) {
      ret = true;
    } else

    if(val.equals("DISPLAY")) {
      ret = true;
    } else

    if(val.equals("UNDO")) {
      ret = true;
    } else
   
    if(val.equals("REDO")) {
      ret = true;
    } else

    if(val.equals("HUMAN-AI")) {
        Tokenizer tzer = new Tokenizer(t);
        server = tzer.nextToken();
        port = tzer.nextToken();

        if(parseDifficulty(tzer)) {
          difficulty1 = t.nextToken();
          ret = true;   
      }
    } else 
  
    if(val.equals("AI-AI")) {
      Tokenizer tzer = new Tokenizer(t);
      server = tzer.nextToken();
      port = tzer.nextToken();

      if(parseDifficulty(tzer)) {
        difficulty1 = t.nextToken();
        if(parseDifficulty(tzer)) {
          difficulty2 = t.nextToken();
          ret = true;
        }
      }
    }
 
    return ret;
  }

  public Boolean parseDifficulty(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;

    if(val.equals("EASY")) {
      ret = true;
    } else
    if(val.equals("MEDIUM")) {
      ret = true;
    } else
    if(val.equals("HARD")) {
      ret = true;
    }

    return ret;
  }

  public Boolean parseMove(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token row;
    Token col;   

    if(parseColumn(t)) {
      col = t.nextToken();
      if(parseRow(t)) {
        row = t.nextToken();
        return true;
      }
    }
    return false;
  }

  public Boolean parseComment(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    if(token.type.equals("comment")) {
      return true;
    }
    else return false;
  }

  private Boolean parseColumn(Tokenizer _tokenizer) {
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

  private Boolean parseRow(Tokenizer _tokenizer) {
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
}
