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
    return lineDown(_piece) || lineUp(_piece) ||
           lineLeft(_piece) || lineRight(_piece);
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

  private Boolean lineDown(Piece _piece) {
    Boolean opColor = false; 
    Location loc = _piece.getLoc();

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

    // at least one piece of opposite color
    Piece first = getPiece(loc.getX()+1, loc.getY());    
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
}
