public class ServerParser extends Parser {
   
  public Engine e;
  Boolean humanColor; // black is false, white is true

  ServerParser() {
    humanColor = false; // default to black
    e = new Engine();
  }

  public Boolean parse(String _str) {
    tokenizer = new Tokenizer(_str);
    return parseExpr();
  }

  private Boolean parseExpr() {
    return parseCommand(tokenizer) || parseMove(tokenizer) || parseComment(tokenizer);
  }

  private Boolean parseCommand(Tokenizer _tokenizer) {
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

    if(val.equals("WHITE")) {
      ret = true;
      humanColor = true;
    } else

    if(val.equals("BLACK")) {
      humanColor = false;
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

  private Boolean parseDifficulty(Tokenizer _tokenizer) {
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

}
