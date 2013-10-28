public class ServerParser extends Parser {
   
  Boolean humanColor; // black is false, white is true

  ServerParser() {
    humanColor = false; // default to black
  }

  public Boolean parse(String _str) {
    tokenizer = new Tokenizer(_str);
    return parseExpr();
  }

  private Boolean parseExpr() {
    return parseCommand(tokenizer) || parseMove(tokenizer) || parseComment(tokenizer) || parseDifficulty(tokenizer);
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
      e = new Engine(humanColor);
      e.aiMove();
    } else

    if(val.equals("BLACK")) {
      ret = true;
      humanColor = false;
      e = new Engine(humanColor);
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
      ret = true;
    } else 
  
    if(val.equals("AI-AI")) {
      Tokenizer tzer = new Tokenizer(t);
      server = tzer.nextToken();
      port = tzer.nextToken();

    }
    if(ret) pw.println("OK");
    return ret;
  }

  private Boolean parseDifficulty(Tokenizer _tokenizer) {
    Boolean ret = false;
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    String val = token.value;

    if(val.equals("EASY")) {
      e.setDifficulty(0);
      ret = true;
    } else
    if(val.equals("MEDIUM")) {
      e.setDifficulty(1);
      ret = true;
    } else
    if(val.equals("HARD")) {
      e.setDifficulty(2);
      ret = true;
    }
    if(ret) pw.println("OK");
    return ret;
  }

}
