import java.util.Random;
import java.util.ArrayList;

public  class Engine {
  State state;
  Random rand;
  
  static final Boolean black = false;
  static final Boolean white = true;

  Boolean humanColor;
  Boolean aiColor;
  Boolean turn;
  // false black
  // true white

  int depth;
  Heuristic heuristic;
  ArrayList<State> history;

  Engine(Boolean _color) {
    
    state = new State();
    humanColor = _color;
    aiColor = !humanColor;
    turn = false;
    rand = new Random();
    history = new ArrayList<State>();

    heuristic = new TestHeuristic();

    depth = 0;
  }

  public void undo() {
    if(history.size() >=2) {
      state = history.get(history.size() - 2);
    }
  }

  public Location lastMove() {
    return state.lastMove;
  }

  public void setDifficulty(int level) { 
  // 0 for easy
  // 1 for medium 
  // 2 for hard
    if(level == 0) {
      heuristic = new EasyHeuristic();
    }
    else if(level == 1) {
      depth = 1;
    }
    else if(level == 2) {
      depth = 5;
    }
  }

  public Boolean isOver() {
    return state.isOver(turn);
  }

  public String draw() {
    return DrawBoard(state);
  }

  public Boolean move(Location _loc) {
    if(isOver()) return false;

    if( humanMove(_loc) ) {
      if( aiMove() ) {
        return true;
      }
    }

    return false;
  }

  private Boolean humanMove(Location _loc) {
    if(humanColor == black) { // move black player
      Piece p = new BlackPiece(_loc);
      if(state.isValidMove(p)) {
        state.addBlack(_loc.getX(), _loc.getY());
        history.add(new State(state));
        turn = !turn;
		
        return true;
      }
    } else { // move white player
      Piece p = new WhitePiece(_loc);
      if(state.isValidMove(p)) {
        state.addWhite(_loc.getX(), _loc.getY());
        history.add(new State(state));
        turn = !turn;
        return true;
      }
    }
    return false;
  }

  
  
  
  public Boolean aiMove() {
    Piece p;
    Location loc;
    ArrayList<State> children;
    double bestValue = 0;
    double val = 0;
    State bestState = state; 

    children = state.getChildren(aiColor);
    for(State child : children) {
      val = minimax(child, depth, true, aiColor); 
      bestValue = Math.max(bestValue, val);
      if(bestValue == val) {
        bestState = child;
      }
    }
     
    state = bestState;
    history.add(new State(state));
    return true;
  }

  private Piece randomBlack() {
    int x = rand.nextInt(8);
    int y = rand.nextInt(8);
    Location loc = new Location(x,y);
    return new BlackPiece(loc);
  }

  private Piece randomWhite() {
    int x = rand.nextInt(8);
    int y = rand.nextInt(8);
    Location loc = new Location(x,y);
    return new WhitePiece(loc);
  }

  public String DrawBoard(State currentState) {
    String newLine = "\n";
    String str = "";
    str += "  _ _ _ _ _ _ _ _\n"; //top of the board

    for (int row = 0; row<8; row++){
      str += ( (row+1) + "|");
      for (int column = 0; column<8; column++)
      {
        if(currentState.getPiece(row,column) != null) {
          if (currentState.getPiece(row,column).isBlack()){
            str += "@|";
          } 
          if (currentState.getPiece(row,column).isWhite()){
            str += "O|";
          }
          if (currentState.getPiece(row,column).isEmpty()) {
            str += "_|";
          } 
        }
      }
      str += "\n";
    }
    str += "  a b c d e f g h\n";//bottom of the board
	
	
	
	
	
    return str;
  }

  // algorithm taken largely from wikipedia
  private double minimax(State _state, int _depth, Boolean _player, Boolean _color) {
    double bestValue;
    double val;
    ArrayList<State> children;

    if(depth == 0 || _state.isOver(_color)) {
      return heuristic.evaluate(_state);
    }
    
    if(_player) {
      bestValue = 0;
      children = _state.getChildren(_color);
      for(State child : children) {
        val = minimax(child, depth-1, false, !_color);
        bestValue = Math.max(bestValue, val);
      } 
      return bestValue;
    }
        
    else {
      bestValue = 1;
      children = _state.getChildren(_color);
      for(State child : children) {
        val = minimax(child, depth-1, true, !_color);
        bestValue = Math.min(bestValue, val);
      }
      return bestValue;
    }
  }

  private interface Heuristic {
    public double evaluate(State _state);
  }
  
  private class EasyHeuristic implements Heuristic {
      public double evaluate(State _state) {
        return .5;
      }
 }

  private class TestHeuristic implements Heuristic {
    public double evaluate(State _state) {
		
		double bestValue = 0;
		double potentialMoveValue = 0;
		double pieceValue = 0;
		double edgeListValue = 0;
		double edgeValue = 0;
		
		ArrayList<State> children;
		children = _state.getChildren(true);
		double childrenSize = children.size();
		
		
		for(State child : children) {
		
			//checks for potential mobility	
			ArrayList<State> subchildren;
			subchildren = child.getChildren(false);
			double potVal = (childrenSize)/(subchildren.size()) ;
			potentialMoveValue = Math.max(potentialMoveValue, potVal);
			
			
			double whitePieceCount = 0;
			double blackPieceCount = 0;
			double edgeAccessTotal = 0;
			
			for(int row=0; row<8; row++) {
				for(int col=0; col<8; col++) {
					if(child.getPiece(row,col) != null) {
					
						//counts number of pieces 
						if (child.getPiece(row,col).isBlack()){
							blackPieceCount++;
						}
						if (child.getPiece(row,col).isWhite()){
							whitePieceCount++;
						}
					
						//checks for corner accessibility from corner(corner ratio)
						if ((row==1||row==7)&&(col==1||col==6)){
							if (child.getPiece(row,col).isWhite()){
								edgeAccessTotal += -0.5;
							}
						}
							
							//checks for corner accessibility from edge(edge ratio)
						if ((row==0||row==1||row==6||row==7)&&(col==0||col==1||col==6||col==7)){
							if (child.getPiece(row,col).isWhite()){
								edgeAccessTotal += -0.5;
							}
						}

					}
				}
			}
			
			
			double pieceTotal = (whitePieceCount - blackPieceCount)  /(whitePieceCount + blackPieceCount);
			pieceValue = Math.max(pieceValue, pieceTotal);
			//edge list value
			edgeValue = Math.max(edgeValue, edgeAccessTotal);
		}
		
		bestValue = (potentialMoveValue)+(pieceValue)+(edgeListValue);  
		return bestValue;
    }
  }
}
