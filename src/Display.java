import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display extends JFrame {

  Board board;
  Control control;
  Client client;

  Display(String name) {
    super(name);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    board = new Board();
    control = new Control();
    client = new Client();

    
    getContentPane().add(board, BorderLayout.CENTER);
    getContentPane().add(control, BorderLayout.LINE_END);
    Dimension d = new Dimension(800,600);
    setPreferredSize(d);


    pack();
    setVisible(true);

  }

  private class Control extends JPanel {
    // data used to communicate with server
    Boolean black;
    Boolean white;

    Boolean human;
    Boolean ai;

    Boolean easy;
    Boolean medium;
    Boolean hard;

    String serverID;
    String optionalServerID;

    String row;
    String col;

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

      blackButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          black = true;
          white = false;
        }
      });

      whiteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          black = false;
          white = true;
        }
      });

      add(new JSeparator(SwingConstants.HORIZONTAL));

      // radio buttons for game type
      JRadioButton humanButton = new JRadioButton("Human vs AI");
      JRadioButton aiButton = new JRadioButton("AI vs AI");
      ButtonGroup group2 = new ButtonGroup();
      group2.add(humanButton);
      group2.add(aiButton);
      add(humanButton);
      add(aiButton);

      humanButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          human = true;
          ai = false;
        }
      });

      aiButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          human = false;
          ai = true;
        }
      });

      final JTextField optionalServer = new JTextField("Optional AI Server IP", 20);
      optionalServer.setMaximumSize( optionalServer.getPreferredSize() );
      add(optionalServer);

      optionalServer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          optionalServerID = optionalServer.getText();
        }
      });


      add(new JSeparator(SwingConstants.HORIZONTAL));

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

      easyButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          easy = true;
          medium = false;
          hard = false;
        }
      });

      mediumButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          easy = false;
          medium = true;
          hard = false;
        }
      });

      hardButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //Execute when button is pressed
          easy = false;
          medium = false;
          hard = true;
        }
      });

      // text field for server name or ip
      // must press enter to save entered text
      final JTextField textField = new JTextField("Server name or IP", 20);
      textField.setMaximumSize( textField.getPreferredSize() );
      add(textField);

      textField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          serverID = textField.getText();
        }
      });

      // begin game
      JButton start = new JButton("Start Game");
      add(start);

      start.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

          client.connect(serverID, 8123);

          if(black) {
            client.write("BLACK");
          }
          else if(white) {
            client.write("WHITE");
          }

          if(human) {
            client.write("HUMAN-AI");
          }
          else if(ai) {
            client.write("AI-AI");
          }

          if(easy) {
            client.write("EASY");
          }
          else if(medium) {
            client.write("MEDIUM");
          }
          else if(hard) {
            client.write("HARD");
          }

        }
      });


      //----------------------------------------------//
      add(new JSeparator(SwingConstants.HORIZONTAL));
      //----------------------------------------------//

      // title
      add(new JLabel("Move Menu"));


      // row field
      // must press enter
      final JTextField rowField = new JTextField("Row", 20);
      rowField.setMaximumSize( rowField.getPreferredSize() );
      add(rowField);

      rowField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          row = rowField.getText();
        }
      });

      // col field
      // must press enter
      final JTextField colField = new JTextField("Column", 20);
      colField.setMaximumSize( colField.getPreferredSize() );
      add(colField);

      colField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          col = colField.getText();
        }
      });

      // move
      JButton move = new JButton("Move");
      add(move);

      move.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("Row: " + row);
          System.out.println("Col: " + col);

          String move = client.write(row + " " + col);
          // update client state
          if( !move.equals("ILLEGAL") ) { 
            board.move(row + " " + col, black);
            board.move(move, !black);
            board.repaint();
          }
        }
      });

      // undo
      JButton undo = new JButton("Undo");
      add(undo);

      undo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          client.write("UNDO");

        }
      });



    }
  }

  private class Board extends JComponent {

    State undoState;
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
      // add letters
      g.drawString("a", width*8, width*1);
      g.drawString("b", width*8, width*2);
      g.drawString("c", width*8, width*3);
      g.drawString("d", width*8, width*4);
      g.drawString("e", width*8, width*5);
      g.drawString("f", width*8, width*6);
      g.drawString("g", width*8, width*7);
      g.drawString("h", width*8, width*8);

      int width1;
      width1 = width;
      width = width+2;
      g.drawString("1", width1*0,  width*8);
      g.drawString("2", width1*1, width*8);
      g.drawString("3", width1*2, width*8);
      g.drawString("4", width1*3, width*8);
      g.drawString("5", width1*4, width*8);
      g.drawString("6", width1*5, width*8);
      g.drawString("7", width1*6, width*8);
      g.drawString("8", width1*7, width*8);


      

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

    public void move(String move, Boolean black) {
      undoState = new State(state);
      ClientParser p = new ClientParser();
      p.parseClientMove(move, state, black);          
    }

    public void undo() {
      state = undoState;
    }
  }
}

