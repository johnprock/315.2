import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display extends JFrame {

  Board board;
  MenuBar menuBar;
  Control control;
  Client client;

  Display(String name) {
    super(name);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    board = new Board();
    menuBar  = new MenuBar();
    control = new Control();

    
    setJMenuBar(menuBar);
    getContentPane().add(board, BorderLayout.CENTER);
    getContentPane().add(control, BorderLayout.LINE_END);
    Dimension d = new Dimension(1000,1000);
    setPreferredSize(d);


    pack();
    setVisible(true);

  }

  private class Control extends JPanel {

    Control() {
      super();

      setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


      // title
      add(new JLabel("Game Menu"));

      // radio buttons for player color
      JRadioButton blackButton = new JRadioButton("Black");
      JRadioButton whiteButton = new JRadioButton("White");
      ButtonGroup group1 = new ButtonGroup();
      group1.add(blackButton);
      group1.add(whiteButton);
      add(whiteButton);
      add(blackButton);

      // radio buttons for game type
      JRadioButton humanButton = new JRadioButton("Human vs AI");
      JRadioButton aiButton = new JRadioButton("AI vs AI");
      ButtonGroup group2 = new ButtonGroup();
      group2.add(humanButton);
      group2.add(aiButton);
      add(humanButton);
      add(aiButton);

      // radio buttons for difficulty
      JRadioButton easyButton = new JRadioButton("Easy");
      JRadioButton mediumButton = new JRadioButton("Medium");
      JRadioButton hardButton = new JRadioButton("Hard");
      ButtonGroup group3 = new ButtonGroup();
      group3.add(easyButton);
      group3.add(mediumButton);
      group3.add(hardButton);
      add(easyButton);
      add(mediumButton);
      add(hardButton);

      // text field for server name or ip
      JTextField textField = new JTextField("Server name or IP", 20);
      textField.setMaximumSize( textField.getPreferredSize() );
      add(textField);

      // begin game
      JButton start = new JButton("Start Game");
      add(start);

      //----------------------------------------------//
      add(new JSeparator(SwingConstants.HORIZONTAL));
      //----------------------------------------------//

      // title
      add(new JLabel("Move Menu"));


      // row field
      JTextField rowField = new JTextField("Row", 20);
      rowField.setMaximumSize( rowField.getPreferredSize() );
      add(rowField);

      // col field
      JTextField colField = new JTextField("Column", 20);
      colField.setMaximumSize( colField.getPreferredSize() );
      add(colField);

      // move
      JButton move = new JButton("Move");
      add(move);

      // text field for row and col move 

      add(new JSeparator(SwingConstants.HORIZONTAL));


    }
  }

  private class Board extends JComponent {

    State state;    

    Board() {
      super();
    //  Dimension d = new Dimension(1000,1000);
    //  setPreferredSize(d);
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
 
          // draw square
          drawSquare(g, row*width, col*width, width);

          // draw piece
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


  private class MenuBar extends JMenuBar {
    
    MenuBar() {
      super();
      JMenu menu = new JMenu("Options");
      

      JMenuItem newGameItem = new JMenuItem("New Game");
      NewGameListener newGameListener = new NewGameListener();
      newGameItem.addActionListener(newGameListener);

      add(menu);
      menu.add(newGameItem);

    }
  }

  private class NewGameListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      JPanel p = new JPanel(new BorderLayout()); 
      p.setVisible(true);
    }
  }

}

