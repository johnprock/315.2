import java.util.ArrayList;

public class Tokenizer {
  
  String str;

  Tokenizer(String _str) {
    str = _str;
    str = str.replace(" ","");
  }

  Tokenizer(Tokenizer _t) {
    this.str = _t.str;
  }

  public Token nextToken() {
    Token t = new Token("empty", "");

    if(str.startsWith("EXIT")) {
      t = new Token("command", "EXIT");
      consume("EXIT");
    } else
    
    if(str.startsWith("DISPLAY")) {
      t = new Token("command", "DISPLAY");
      consume("DISPLAY");
    } else
     
    if(str.startsWith("UNDO")) {
      t = new Token("command", "UNDO");
      consume("UNDO");
    } else
   
    if(str.startsWith("REDO")) {
      t = new Token("command", "REDO");
      consume("REDO");
    } else
   
    if(str.startsWith("HUMAN-AI")) {
      t = new Token("command", "HUMAN-AI");
      consume("HUMAN-AI");
    } else
   
    if(str.startsWith("AI-AI")) {
      t = new Token("command", "AI-AI");
      consume("AI-AI");
    } else

    if(str.startsWith("EASY")) {
      t = new Token("difficulty", "EASY");
      consume("EASY");
    } else

    if(str.startsWith("MEDIUM")) {
      t = new Token("difficulty", "MEDIUM");
      consume("MEDIUM"); 
    } else

    if(str.startsWith("HARD")) {
      t = new Token("difficulty", "HARD");
      consume("HARD");
    } else

    if(str.startsWith("WELCOME")) {
      t = new Token("ack", "WELCOME");
      consume("WELCOME");
    } else

    if(str.startsWith("OK")) {
      t = new Token("ack", "OK");
      consume("OK");
    } else

    if(str.startsWith("ILLEGAL")) {
      t = new Token("ack", "ILLEGAL");
      consume("ILLEGAL");
    } else

    if(str.startsWith(";")) {
      t = new Token("comment", ";");
      consume(";"); 
    } else

    if(str.length() != 0) {
      t = new Token("char", str.substring(0,1));
      consume(" ");
    }

    return t;      
  }

  private void consume(String _tokenString) {
    str = str.substring(_tokenString.length());
  }
}
