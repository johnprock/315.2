public class Parser {
  
  Tokenizer tokenizer = new Tokenizer();

  public Boolean parse(String _str) {
    return parseExpr(_str);
  }

  public Boolean parseExpr(String _str) {
    return parseCommand(_str) || parseMove(_str) || parseComment(_str);
  }

  public Boolean parseCommand(String _str) {
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

  public Boolean parseMove(String _str) {
    return false;
  }

  public Boolean parseComment(String _str) {
    return false;
  }

  public Boolean parseColumn() {
    return false;
  }

  public Boolean parseRow() {
    return false;
  }

  public Boolean parseDigit() {
    return false;
  }

  public Boolean parseServer() {
    return false;
  }

  public Boolean parsePort() {
    return false;
  }
}
