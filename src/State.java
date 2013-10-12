public class State {
  Piece[][] board; 

  // regular constructor
  State() {
    board = new Piece[8][8];

    // fill the board with empty squares    
    for(int i=0; i<8; i++) {
      for(int j=0; j<8; j++) {
        board[i][j] = new EmptyPiece(new Location(i,j));
      }
    }

    // set middle square
    addBlack(4,5);
    addBlack(5,4);
    addWhite(4,4);
    addWhite(5,5);

  }

  // copy constructor
  State(State state) {
    this.board = state.board;
  }

  public Piece getPiece(int _x, int _y) {
    return board[_x][_y];
  }

  public void flipPiece(int _x, int _y) {
    board[_x][_y] = board[_x][_y].flip();
  }

  public void addBlack(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new BlackPiece(loc);
  }

  public void addWhite(int _x, int _y) {
    Location loc = new Location(_x, _y);
    board[_x][_y] = new WhitePiece(loc);
  }

  public Boolean isValidMove(Piece _piece) {
    // a move is valid if there is a line of pieces
    // of the opposing color terminated by a piece
    // of the same color
    return //lineDown(_piece) || lineUp(_piece) ||
          // lineLeft(_piece) || lineRight(_piece) ||
           topLeftDiag(_piece) || topRightDiag(_piece) ||
           bottomLeftDiag(_piece) || bottomRightDiag(_piece);
  }

  // returns true if the game is in an end state
  public Boolean isOver(Boolean _color) {
    // true for white, false for black
    // the game is over when there are no
    // valid moves left
    Boolean canMove = false;
    Piece piece;
    
    if(_color) {
      piece = new WhitePiece(new Location(0,0));
    }
    else {
      piece = new BlackPiece(new Location(0,0));
    }     

    for(int i=0; i<8; i++) {
      for(int j=0; j<8; j++) {
        Location loc = new Location(i,j);
        piece.setLoc(loc);
        if( isValidMove(piece) ) {
          canMove = true;
        }
      }
    } 
  
  return !canMove; 
  }

  private Boolean topLeftDiag(Piece _piece) {
    Boolean line = false;
    Location loc = _piece.getLoc();
    Piece current = pieceTopLeft(_piece);

    if( current.isEmpty() ) {
      return false;
    }

    if( _piece.sameColor(current) ) {
      return false;
    }

    current = pieceTopLeft(current);
    while( !current.isEmpty() ) {
      if( current.sameColor(_piece) ) {
        line = true;
        break;
      }
      current = pieceTopLeft(current);
    }

    return line;
  }

  private Boolean topRightDiag(Piece _piece) {
    return false;
  }

  private Boolean bottomRightDiag(Piece _piece) {
    return false;
  }

  private Boolean bottomLeftDiag(Piece _piece) {
    return false;
  }

  private Boolean lineDown(Piece _piece) {
    Boolean opColor = false; 
    Location loc = _piece.getLoc();

    // pieces at the bottom of the board can't have a down line
    if(loc.getY() == 7) {
      return false;
    }

    // at least one piece of opposite color
    Piece first = getPiece(loc.getX(), loc.getY()+1);    
    if( first.sameColor(_piece) ) {
      return false;
    }

   // look for terminating piece of same color
   for(int i=loc.getY()+2; i<8; i++) {
     Piece linePiece = getPiece(loc.getX(), i);
     
     if( linePiece.sameColor(_piece) ) {
       opColor = true;
       break;
     }
     
     if( linePiece.isEmpty() ) {
       break;
     }
   }    

   return opColor;

  } 

  private Boolean lineUp(Piece _piece) {
    Boolean opColor = false; 
    Location loc = _piece.getLoc();

    // pieces at the top of the board can't have an up line
    if(loc.getY() == 0) {
      return false;
    }

    // at least one piece of opposite color
    Piece first = getPiece(loc.getX(), loc.getY()-1);    
    if( first.sameColor(_piece) ) {
      return false;
    }

   // look for terminating piece of same color
   for(int i=loc.getY()-2; i>=0; i--) {
     Piece linePiece = getPiece(loc.getX(), i);
     
     if( linePiece.sameColor(_piece) ) {
       opColor = true;
       break;
     }
     
     if( linePiece.isEmpty() ) {
       break;
     }
   }    

   return opColor;
 
  }

  private Boolean lineLeft(Piece _piece) {
    Boolean opColor = false; 
    Location loc = _piece.getLoc();

    if(loc.getX() == 0) {
      return false;
    }

    // at least one piece of opposite color
    Piece first = getPiece(loc.getX()-1, loc.getY());    
    if( first.sameColor(_piece) ) {
      return false;
    }

   // look for terminating piece of same color
   for(int i=loc.getX()-2; i>=0; i--) {
     Piece linePiece = getPiece(i, loc.getY());
     
     if( linePiece.sameColor(_piece) ) {
       opColor = true;
       break;
     }
     
     if( linePiece.isEmpty() ) {
       break;
     }
   }    

   return opColor;
 
  }

  private Boolean lineRight(Piece _piece) {
    Boolean opColor = false; 
    Location loc = _piece.getLoc();

    if(loc.getX() == 7) {
      return false;
    }

    // at least one piece of opposite color
     Piece first = getPiece(loc.getX()-1, loc.getY());      
    if( first.sameColor(_piece) ) {
      return false;
    }

   // look for terminating piece of same color
   for(int i=loc.getX()+2; i<8; i++) {
     Piece linePiece = getPiece(i, loc.getY());
     
     if( linePiece.sameColor(_piece) ) {
       opColor = true;
       break;
     }
     
     if( linePiece.isEmpty() ) {
       break;
     }
   }    

   return opColor;
  
  }

  // these functions are used to get pieces relative
  // to eachother, return empty if out of bounds
  private Piece pieceUp(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()+1 > 7) 
      return new EmptyPiece(_piece);

    return getPiece(loc.getX(), loc.getY() + 1);
  }

  private Piece pieceDown(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()-1 < 0) 
      return new EmptyPiece(_piece);

    return getPiece(loc.getX(), loc.getY() - 1);
  }

  private Piece pieceLeft(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()+1 > 7) 
      return new EmptyPiece(_piece);

    return getPiece(loc.getX(), loc.getY() + 1);
  }

  private Piece pieceRight(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getX()+1 > 7) 
      return new EmptyPiece(_piece);

    return getPiece(loc.getX() + 1, loc.getY());
  }

  private Piece pieceTopLeft(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getX()-1 < 0) 
      return new EmptyPiece(_piece);
    if (loc.getY()+1 > 7)
      return new EmptyPiece(_piece);

    return getPiece(loc.getX()-1, loc.getY() + 1);
  }

  // diagonals
  private Piece pieceTopRight(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()+1 > 7) 
      return new EmptyPiece(_piece);
    if (loc.getX()+1 > 7)
      return new EmptyPiece(_piece);

    return getPiece(loc.getX() + 1, loc.getY() + 1);
  }

  private Piece pieceBottomLeft(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()-1 < 0)
      return new EmptyPiece(_piece);
    if (loc.getX()-1 < 0)
      return new EmptyPiece(_piece);

    return getPiece(loc.getX() - 1, loc.getY() - 1);
  }

  private Piece pieceBottomRight(Piece _piece) {
    Location loc = _piece.getLoc();
    if (loc.getY()-1 < 0) 
      return new EmptyPiece(_piece);
    if(loc.getX()+1 > 7)
      return new EmptyPiece(_piece);

    return getPiece(loc.getX() + 1, loc.getY() - 1);
  }

  private interface Command {
    public Piece get(Piece _piece);
  }

  private class Up implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getY()+1 > 7) 
        return new EmptyPiece(_piece);

      return getPiece(loc.getX(), loc.getY() + 1);
    } 
  }

  private class Down implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getY()-1 < 0) 
        return new EmptyPiece(_piece);

      return getPiece(loc.getX(), loc.getY() - 1);
    }
  }

  private class Left implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getY()+1 > 7) 
        return new EmptyPiece(_piece);

      return getPiece(loc.getX(), loc.getY() + 1);
    }
  }

  private class Right implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getX()+1 > 7) 
        return new EmptyPiece(_piece);

      return getPiece(loc.getX() + 1, loc.getY());
    }
  }

  private class UpLeft implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getX()-1 < 0) 
        return new EmptyPiece(_piece);
      if (loc.getY()+1 > 7)
        return new EmptyPiece(_piece);

      return getPiece(loc.getX()-1, loc.getY() + 1);
    }
  }

  private class UpRight implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getY()+1 > 7) 
        return new EmptyPiece(_piece);
      if (loc.getX()+1 > 7)
        return new EmptyPiece(_piece);

      return getPiece(loc.getX() + 1, loc.getY() + 1);
    }
  }

  private class DownLeft implements Command {
    public Piece get(Piece _piece) {
      Location loc = _piece.getLoc();
      if (loc.getY()-1 < 0)
        return new EmptyPiece(_piece);
      if (loc.getX()-1 < 0)
        return new EmptyPiece(_piece);

      return getPiece(loc.getX() - 1, loc.getY() - 1);
    }
  }

  private class DownRight implements Command {
    public Piece get(Piece _piece) {
    Location loc = _piece.getLoc();
      if (loc.getY()-1 < 0) 
        return new EmptyPiece(_piece);
      if(loc.getX()+1 > 7)
        return new EmptyPiece(_piece);

      return getPiece(loc.getX() + 1, loc.getY() - 1);
    }
  }
}

