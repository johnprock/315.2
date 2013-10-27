import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//http://forum.codecall.net/topic/45301-jcombobox-simple-version/
public class Display extends JFrame {

  Board board;
  MenuBar menuBar;
  Display(String name) {
    super(name);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    board = new Board();
    menuBar  = new MenuBar();
    setJMenuBar(menuBar);
    getContentPane().add(board, BorderLayout.CENTER);

    pack();
    setVisible(true);

  }

  private class Board extends JComponent {

    State state;    

    Board() {
      super();
      Dimension d = new Dimension(1000,1000);
      setPreferredSize(d);
      state = new State();
    }

    public void paintComponent(Graphics g) {
      drawBoard(g);
    } 

    public void drawBoard(Graphics g) {
      int width = 50;    

      // add squares
      for(int row=0; row<8; row++) {
        for(int col=0; col<8; col++) {
 
          drawSquare(g, row*width, col*width, width);
          if(state.getPiece(row,col).isBlack()) {
            drawPiece(g, row*width, col*width, width, 1);
          }
          else if(state.getPiece(row,col).isWhite()) {
            drawPiece(g, row*width, col*width, width, 2);
          }
          else if(state.getPiece(row,col).isEmpty()) {
            drawPiece(g, row*width, col*width, width, 3);
          }
        }
      } 

    }

    public void drawPiece(Graphics g, int x, int y, int width, int color) {
        // 1 - black
        // 2 - white
        // 3 - empty

        if(color == 1) {
          g.setColor(Color.black);
          g.fillOval(x+5, y+5, width-10, width-10);
        }
        else if(color == 2) {
          g.setColor(Color.white);
          g.fillOval(x+5, y+5, width-10, width-10);
        }
        else if(color == 3) {
        }

    }

    public void drawSquare(Graphics g, int x, int y, int width) {
        int borderWidth = width/20;

        // draw green square
        g.setColor(Color.green);
        g.fill3DRect(x, y, width, width, false);
        
        // draw left border
        g.setColor(Color.black);
        g.fill3DRect(x, y, borderWidth, width, false);
       
        // draw right border
        g.fill3DRect(x+width-borderWidth, y, borderWidth,  width, false);    

        // draw top border
        g.fill3DRect(x, y, width, borderWidth, false);
        
        // draw bottom border
        g.fill3DRect(x, y+width-borderWidth, width, borderWidth, false);
    }

    public void updateState(State _state) {
      state = _state;
    }


  }


  private class MenuBar extends JMenuBar{
    
    MenuBar() {
      super();
      JMenu menu = new JMenu("Options");
	  add(menu);
	  
	  JMenu connect = new JMenu("connect");
	  add(connect);
	  JMenuItem connect1=new JMenuItem("connect   ");
	 //connect.addActionListener(this);
	 // connect.add(connect1);
	//connectDialog connectD= new connectDialog;
	 //connectD.setVisible(true);
	  // if(conD.yes){
				// create new game
				// restart();
			// }
    }
  }

}