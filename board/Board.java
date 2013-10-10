public class Board {
	static private State state;
		
	public Board(State state)
	{
		this.state = state;
	}

	public static void DrawBoard()
	{
		String newLine = System.getProperty("line.separator");
		System.out.println("  _ _ _ _ _ _ _ _");//top of the board
		
		for (int row = 0; row<8; row++){
			System.out.print(row+"|");
			for (int column = 0; column<8; column++)
			{
				if (state.board[row][column]!= null){
					if (state.getPiece(row,column).isBlack()){
						System.out.print("@|");
					}
					if (state.getPiece(row,column).isWhite()){
						System.out.print("O|");
					}
				}
				else
					System.out.print("_|");	
			}
			System.out.println("");
		}
		System.out.println("  a b c d e f g h");//bottom of the board
	}
	
	//prints the state of each element in the board
	public static void doPrint(State state) {
		state.printBoardState();
	}
	
	public void initiateGameBoard(State state) {
		state.addWhite(3,3);
		state.addBlack(3,4);
		state.addBlack(4,3);
		state.addWhite(4,4);
	}
}
