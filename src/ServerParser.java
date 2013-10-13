public class ServerParser {
  
  Tokenizer tokenizer;

  public Boolean parse(String _str) {
    tokenizer = new Tokenizer(_str);
    return parseExpr(_str, tokenizer);
  }

  public Boolean parseExpr(String _str, Tokenizer _tokenizer) {
    return parseCommand(_str, tokenizer) || parseMove(_str, tokenizer) || parseComment(_str, tokenizer);
  }

  public Boolean parseCommand(String _str, Tokenizer _tokenizer) {
    Boolean ret = false;
    
    ret = parseDifficulty(_str);
    
    if(_str.equals("EXIT")) {
      ret = true;
    } else
    if(_str.equals("DISPLAY")) {
      ret = true;
    } else
    if(_str.equals("UNDO")) {
      ret = true;
    } else
    if(_str.equals("REDO")) {
      ret = true;
    } else
    if(_str.startsWith("HUMAN-AI")) {
    } else   
    if(_str.startsWith("AI-AI")) {
      // ?
    }
   
    return ret;
  }

  public Boolean parseDifficulty(String _str) {
    Boolean ret = false;

    if(_str.equals("EASY")) {
      ret = true;
    } else
    if(_str.equals("MEDIUM")) {
      ret = true;
    } else
    if(_str.equals("HARD")) {
      ret = true;
    }

    return ret;
  }

  public Boolean parseMove(String _str, Tokenizer _tokenizer) {
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

  public Boolean parseComment(String _str, Tokenizer _tokenizer) {
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

  private Boolean parseDigit() {
    return false;
  }

  private Boolean parseServer() {
    return false;
  }

  private Boolean parsePort() {
    return false;
  }
}
