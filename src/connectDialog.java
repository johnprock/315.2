import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class connectDialog extends JDialog implements ActionListener{
	private JPanel textP,buttonP,noticeP;
	private JLabel hostL,noticeL;
	private JTextField hostT;
	private JButton yesB,noB;
	public String host;
	public boolean yes=false;
public connectDialog(Frame owner)
	{
		super(owner,"connect",true);    
		
		hostL=new JLabel("IP address");
		hostT=new JTextField("127.0.0.1",8123);
		textP=new JPanel();
		textP.add(hostL);
		textP.add(hostT);

		
		yesB=new JButton("OK");
		noB=new JButton("Cancel");
		buttonP=new JPanel();
		buttonP.add(yesB);
		buttonP.add(noB);
		yesB.addActionListener(this);
		noB.addActionListener(this);
		
		noticeL=new JLabel("Connect Information");
		noticeP=new JPanel();
		noticeP.add(noticeL);
		
		getContentPane().setLayout(new GridLayout(4,1));    
		getContentPane().add(noticeP);
		getContentPane().add(textP);                          
		getContentPane().add(buttonP);
		Point p;
		p=owner.getLocation();
		int x=(int)p.getX()+200;
		int y=(int)p.getY()+150;
		this.setLocation(x,y);
		setResizable(false);
		pack();                                      
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==yesB){
			host=hostT.getText();
			yes=true;
		}
		setVisible(false);                          
	}
}


