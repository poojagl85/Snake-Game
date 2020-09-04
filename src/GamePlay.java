import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;



public class GamePlay extends JPanel implements KeyListener,ActionListener{

	private ImageIcon titleImage ; 
	private int[] snakexLength = new int[750] ; 
	private int[] snakeyLength = new int[750] ; 
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;


	private ImageIcon rightmouth ; 
	private ImageIcon leftmouth ; 	
	private ImageIcon downmouth ; 
	private ImageIcon upmouth ; 

	private ImageIcon snakeimage ; 
	private ImageIcon enemyImage  ; 

	private Timer timer ; 
	private int delay = 100 ;

	private int lengthOfSnake = 3 ; 
	private int moves = 0 ;
	private int score =0 ;

	private Random random = new Random() ;

	private int xpos = random.nextInt(33) + 1;
	private int ypos = random.nextInt(20) + 3 ;




	public GamePlay() {

		addKeyListener(this);

		setFocusable(true) ; 
		setFocusTraversalKeysEnabled(true) ; 
		timer = new Timer(delay , this) ; 
		timer.start() ; 




	}

	//drawing on JPanel
	public void paint(Graphics g) {

		if(moves == 0) {
			snakexLength[0] = 100 ; 
			snakeyLength[0] = 100 ; 
			snakexLength[1] = 75 ; 
			snakeyLength[1] = 100 ; 
			snakexLength[2] = 50; 
			snakeyLength[2] = 100 ; 

		}

		//border of title image
		g.setColor(Color.white) ;
		g.drawRect(24,10,851,55);
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g , 25 ,11) ; 

		//border of snake game
		g.setColor(Color.white) ;
		g.drawRect(24,74,851,577);
		g.setColor(Color.BLACK);
		g.fillRect(25,75,850,575);

		if(snakexLength[0] < 25 || snakeyLength[0] < 75 || snakexLength[0] > 850 || snakeyLength[0] > 625 ) {
			right = false;
			left = false; 
			up = false;
			down = false;
			g.setColor(Color.white) ;
			g.setFont(new Font("arial",Font.BOLD, 50)) ;
			g.drawString("GAME OVER!", 300 , 300) ;
			g.setFont(new Font("arial",Font.BOLD, 25)) ;
			g.drawString("Press SPACE to restart", 310 , 330) ;
			return ; 
		}


		//draw score
		g.setColor(Color.white) ;
		g.setFont(new Font("arial",Font.PLAIN , 14)) ;
		g.drawString("Scores : " + score , 760 , 30) ;

		//draw length
		g.setColor(Color.white) ;
		g.setFont(new Font("arial",Font.PLAIN , 14)) ;
		g.drawString("Length : " + lengthOfSnake , 760 , 50) ;






		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this , g , snakexLength[0] , snakeyLength[0]);

		rightmouth.paintIcon(this , g , snakexLength[0] , snakeyLength[0]);

		for(int a = 0 ; a < lengthOfSnake ; a++) {
			if(a == 0 && right) {

				rightmouth.paintIcon(this , g , snakexLength[a] , snakeyLength[a]);
			}
			if(a == 0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this , g , snakexLength[a] , snakeyLength[a]);
			}

			if(a == 0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this , g , snakexLength[a] , snakeyLength[a]);
			}

			if(a == 0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this , g , snakexLength[a] , snakeyLength[a]);
			}

			if(a!=0) {
				snakeimage = new ImageIcon("snakeimage.png") ;  

				snakeimage.paintIcon(this , g , snakexLength[a] , snakeyLength[a]);

			}

		}



		enemyImage = new ImageIcon("enemy.png") ;
		if(xpos*25 == snakexLength[0] && ypos*25 == snakeyLength[0]) {
			lengthOfSnake++;
			score++;
			xpos = random.nextInt(33)+1 ;

			ypos = random.nextInt(20)+3 ;
			System.out.println(xpos + " " + ypos);




		}

		enemyImage.paintIcon(this,g,xpos*25 ,ypos*25 ) ; 
		for(int b =1 ; b < lengthOfSnake ; b++) {
			if(snakexLength[b] == snakexLength[0] && snakeyLength[b] == snakeyLength[0]) {
				right = false;
				left = false; 
				up = false;
				down = false;
				g.setColor(Color.white) ;
				g.setFont(new Font("arial",Font.BOLD, 50)) ;
				g.drawString("GAME OVER!", 300 , 300) ;
				g.setFont(new Font("arial",Font.BOLD, 25)) ;
				g.drawString("Press SPACE to restart", 310 , 330) ;

			}
		}



		g.dispose() ;


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();




		if(right) {


			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				snakeyLength[i+1] = snakeyLength[i] ;  
			}

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				if(i == 0) {
					snakexLength[i] = snakexLength[i] + 25 ; ; 

				}else {

					snakexLength[i] = snakexLength[i-1] ;  
				}

				if(snakexLength[i] > 850) {
					break ;
				}
			}

			repaint() ; 

		}

		if(left) {

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				snakeyLength[i+1] = snakeyLength[i] ;  
			}

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				if(i == 0) {
					snakexLength[i] = snakexLength[i] - 25 ; ; 

				}else {

					snakexLength[i] = snakexLength[i-1] ;  
				}

				if(snakexLength[i] < 25) {
					break ; 
				}
			}

			repaint() ; 



		}

		if(up) {

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				snakexLength[i+1] = snakexLength[i] ;  
			}

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				if(i == 0) {
					snakeyLength[i] = snakeyLength[i] - 25 ; ; 

				}else {

					snakeyLength[i] = snakeyLength[i-1] ;  
				}

				if(snakeyLength[i] < 75) {
					break ; 
				}
			}

			repaint() ; 



		}

		if(down) {

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				snakexLength[i+1] = snakexLength[i] ;  
			}

			for(int i = lengthOfSnake-1 ; i>=0 ; i--) {
				if(i == 0) {
					snakeyLength[i] = snakeyLength[i] + 25 ; ; 

				}else {

					snakeyLength[i] = snakeyLength[i-1] ;  
				}

				if(snakeyLength[i] > 625) {
					break ; 
				}
			}

			repaint() ; 



		}

	}



	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_SPACE) {

			moves = 0 ; 
			right = false;
			left = false;
			up= false;
			down = false;
			lengthOfSnake =3 ; 
			xpos = random.nextInt(33)+1 ;

			ypos = random.nextInt(20)+3 ;
			repaint() ;




		}


		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			moves++;
			if(!left) {
				right = true ; 


			}else {
				right = false; ; 
				left = true;
			}
			up = false; 
			down = false;

		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
			moves++;
			if(!right) {

				left = true;
			}else {
				right = true; ; 
				left = false;
			}
			up = false; 
			down = false;

		}
		if(e.getKeyCode() == KeyEvent.VK_UP ) {
			moves++;
			if(!down) {

				up = true;
			}else {
				down= true; ; 
				up = false;
			}
			right = false; 
			left = false;

		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
			moves++;
			if(!up) {

				down = true;
			}else {
				up= true; ; 
				down  = false;
			}
			right = false; 
			left = false;

		}

	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
