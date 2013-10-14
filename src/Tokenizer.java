import java.util.ArrayList;

public class Tokenizer {
  
  String str;
  int index;
  int length;
  String[] sarray;

  Tokenizer(String _str) {
    str = _str;
    sarray = str.split("\\s+");
    index = 0;
    length = sarray.length;
  }

  Tokenizer(Tokenizer _t) {
    this.str = _t.str;
    this.index = _t.index;
    this.sarray = _t.sarray;
    this.length = _t.length;
  }

  public Token nextToken() {
    Token t;
    if(index < sarray.length) { 
      t = new Token("type", sarray[index]);
      index++;
    }
    else {
      t = new Token("","");
    }
    return t;
  }

}
