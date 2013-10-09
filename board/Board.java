
public class Board {


	public static void DrawSquare()	
	{
		System.out.print("_|");	
	}

	public static void DrawBoard()
	{
		String newLine = System.getProperty("line.separator");
		System.out.println("  _ _ _ _ _ _ _ _");
		
		for (int row = 1; row<9; row++){
			System.out.print(row+"|");
			for (int column = 0; column<8; column++)
			{
				DrawSquare();
			}
			System.out.println("");
		}
		System.out.println("  a b c d e f g h");
	}
	
	


    public static void main(String[] args) {
        DrawBoard();
    }

}