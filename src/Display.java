import java.awt.*;
import javax.swing.*;

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

    setSize(300,500);
    pack();
    setVisible(true);

  }

  private class Board extends JComponent {
    
    Board() {
      super();
      Dimension d = new Dimension(300,300);
      setPreferredSize(d);
    }

    public void paintComponent(Graphics g) {
      g.setColor(Color.black);
      g.drawRect(0,0,20,20);
    } 
  }

  private class MenuBar extends JMenuBar {
    
    MenuBar() {
      super();
      JMenu menu = new JMenu("A Menu");
      add(menu);
    }
  }
}

