public class Main {
    public static void main(String[] args) {
		State state;
		state = new State();
		Board board;
		board = new Board(state);
		board.DrawBoard();
		board.doPrint(state);
    }
}