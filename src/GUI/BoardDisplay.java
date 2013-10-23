import java.awt.*;
import javax.swing.*;

   public class BoardDisplay extends JPanel{
     public BoardDisplay(){
    	 this.setSize(600,600);
     }
     public void paint(Graphics g){
 		super.paint(g);
 		g.setColor(Color.BLACK);
     	for(int i=20;i<=580;i=i+70)
     	g.drawLine(i, 20, i,580);
        for(int j=20;j<=580;j=j+70)
     	g.drawLine(20,j,580,j);
	}
	
}
		//set black white
		/*
		for (int row = 0; row < 8(); row ++)
			for (int col = 0; col < 8; col++)
				new ImageIcon(getClass().getResource("Blank.jpg"))).paintIcon(this, g, 84*col, 84*row);
		
		for (int i = 0; i < 8; i++)
		{

				if(currentState.getPiece(row,column).isBlack())
					new ImageIcon(getClass().getResource("Black.jpg"))).paintIcon(this, g, 84*col, 84*row);
				else if(currentState.getPiece(row,column).isWhite())
					new ImageIcon(getClass().getResource("White.jpg"))).paintIcon(this, g, 84*col,84*row);
				else if (currentState.getPiece(row,column).isEmpty())
					new ImageIcon(getClass().getResource("Blank.jpg"))).paintIcon(this, g, 84*col, 84*row);
					
		
		}
	}
		*/	
