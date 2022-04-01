package hang;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;

public class hangGame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int player = 0;
public String guess, wordforguessing;
public static int firstletter ='a';
private static char[] miss, letters;
public static JPanel mainpanel, secondarypanel;
private static int WIDTH = 700;
private static int HEIGHT = 500;
public static int count=0;
public static int numb=0;
Label text = new Label();

public hangGame() {
	this.setTitle("HangMan game!");
	this.setSize(WIDTH, HEIGHT);
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenWidth = tk.getScreenSize().width;
	int screenHeight = tk.getScreenSize().height;
	setLocation(screenWidth / 2 - WIDTH / 2 , screenHeight / 2  - HEIGHT / 2 );
	
	mainpanel = new JPanel();
	mainpanel.setLayout(new GridLayout(2, 2));
	secondarypanel = new JPanel();

	add(mainpanel, BorderLayout.SOUTH);
	add(secondarypanel, BorderLayout.CENTER);
	
	JButton[] letters = new JButton[26];
	char temp; 
	for (int i = 0; i < letters.length; i++) {
		temp = (char)firstletter++;
		letters[i] = new JButton(Character.toString(temp));
		letters[i].setBackground(Color.BLACK);
		letters[i].setForeground(Color.GRAY);
		letters[i].setActionCommand(Character.toString(temp));
		letters[i].addActionListener(this);
		mainpanel.add(letters[i]);
	}
	
	JButton newGame = new JButton("New game!");
	newGame.setPreferredSize(new Dimension(150,25));
	newGame.setBackground(Color.GRAY);
	newGame.setForeground(Color.WHITE);
	newGame.setActionCommand("New game!");
	newGame.addActionListener(this);
	secondarypanel.add(newGame);

	//secondarypanel.add(text);
	
	
	var = 1;
	window();
	
	for(int j=0; j < wordforguessing.length(); j++) {
		miss[j] = '_';
	}
	
	repaint();
	}
public void window() {
	if(player==0) { 
		wordforguessing = JOptionPane.showInputDialog(null,"Word for guessing." ,"Player 1", JOptionPane.QUESTION_MESSAGE) ;
		guess = JOptionPane.showInputDialog(null,"What am I guesing?" ,"Guess", JOptionPane.QUESTION_MESSAGE) ;
	//player=1;
	} else {
	    wordforguessing = JOptionPane.showInputDialog(null,"Word for guessing." ,"Player 2", JOptionPane.QUESTION_MESSAGE) ;
		guess = JOptionPane.showInputDialog(null,"What am I guesing?" ,"Guess", JOptionPane.QUESTION_MESSAGE) ;
	//player = 0;
	}
	letters = wordforguessing.toCharArray();
	int size = wordforguessing.length();
	miss = new char[size];
}
  public void endofgame(Graphics g){
	    super.paint(g);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		if(player == 0) {
		    JOptionPane.showMessageDialog(null,"Player 1 wins!");
		    player=1;
				} else {
			JOptionPane.showMessageDialog(null,"Player 2 wins!");
		    player=0;
		    }
		mainpanel.setVisible(true);
		secondarypanel.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if ((Arrays.equals(letters, miss)) && count < 6){
			if(player == 0) {
	    JOptionPane.showMessageDialog(null,"Player 2 wins!");
	    player=1;
			} else {
			JOptionPane.showMessageDialog(null,"Player 1 wins!");
		    player=0;
			}
		mainpanel.setVisible(true);
		secondarypanel.setVisible(true);
		} 
		String res = new String();
	    for (int i = 0; i < miss.length; i++) {
		res = res + miss[i] + " ";}
	    g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(res, 220, 330);
		g.setColor(new Color(128,128,128));
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(guess, 240, 380);
		g.setColor(new Color(0,0,0));
		g.drawLine(450, 100, 450, 50);
		g.drawLine(450, 50, 550, 50);
		g.drawLine(550, 50, 550, 350);
		
		if (count >= 1) {
		g.setColor(new Color(255,0,0));
		g.drawOval(418, 100, 65, 55);
		g.setColor(new Color(0,0,0));
		g.drawOval(434, 115, 8, 8);
		g.drawOval(454, 115, 8, 8);
		g.setColor(new Color(255,0,0));
	    g.drawLine(440, 145, 460, 145);
	    
		if (count >= 2) {
		g.drawLine(450, 155, 450, 250);
		}
        if (count >= 3) {
			g.drawLine(450, 180, 420, 220);
		}
	    if (count >= 4) {
		  g.drawLine(450, 180, 480, 220);
		  }
		if (count >= 5) {
		g.drawLine(450, 250, 420, 300);
		}
		if (count >= 6) {
		g.drawLine(450, 250, 480, 300);
		endofgame(g);
		}
	}
}

	public void actionPerformed(ActionEvent e) {
		String input = e.getActionCommand();
		if (input.length() == 1 && var == 1){
			if (wordforguessing.contains(input)){
			for (int i = 0; i < letters.length; i++) {
			  if (input.charAt(0) == letters[i]) 
				  miss[i] = input.charAt(0);
			  }
			numb++;
			text = new Label(String.valueOf(numb));
			secondarypanel.add(text);
			secondarypanel.setVisible(true);
			}
			else if (!wordforguessing.equals(input)) 
				count++;
			repaint();
			}
		else if (input.equals("New game!")) {
			count = 0;
			mainpanel.setVisible(true);
			var = 1;
			window();
			repaint();		
		}
	}

	private int var = 0;
	
	public static void main(String[] args) {
		hangGame frame = new hangGame();
		frame.setVisible(true);

	}
}
