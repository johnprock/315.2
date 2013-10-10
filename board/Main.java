public class Main {
    public static void main(String[] args) {
		State state;
		state = new State();
		Board board;
		board = new Board(state);
		board.initiateGameBoard(state);//add initial pieces on board
		board.DrawBoard(); // draw board on screen
		//board.doPrint(state); // prints out a list of values for the board(for testing purposes)
		
    }
}