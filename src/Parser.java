public abstract class Parser {

  Tokenizer tokenizer;
  
  public abstract Boolean parse(String _str);

  protected Boolean parseComment(Tokenizer _tokenizer) {
    Tokenizer t = new Tokenizer(_tokenizer);
    Token token = t.nextToken();
    if(token.value.startsWith(";")) {
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
        return true;
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
}