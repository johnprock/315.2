public class Board {
	private State state;
	public Board(State state)
	{
		this.state = state;
	}

	public static void DrawSquare()	
	{
		System.out.print("_|");	
	}

	public static void DrawBoard()
	{
		String newLine = System.getProperty("line.separator");
		System.out.println("  _ _ _ _ _ _ _ _");//top of the board
		
		for (int row = 1; row<9; row++){
			System.out.print(row+"|");
			for (int column = 0; column<8; column++)
			{
				DrawSquare();
			}
			System.out.println("");
		}
		System.out.println("  a b c d e f g h");//bottom of the board
	}
	
	//prints the state of each element in the board
	public static void doPrint(State state) {
		state.printBoardState();
	}


}