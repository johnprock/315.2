public class ClientParser extends Parser {

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

}
