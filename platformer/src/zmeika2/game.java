package zmeika2;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.*;

public class game {

	public static void main(String[] args) {
		myFrame okno=new myFrame();
	}	
}

class myFrame extends JFrame
{
	public myFrame()
	{
		myPanel pan=new myPanel();
		Container cont=getContentPane();
		cont.add(pan);
		setTitle("Змейка");
		setBounds(0,0,820,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
	}
}

class myPanel extends JPanel
{
	private JLabel lb1,lb2;
	private JButton btn1,btn2;	
	private myPanel pan;
	private Image background,endg,panel,ramka,newgame,exit,box;
	private Image player,zombie;
	public Zombie zombie1,zombie2,zombie3,zombie4,zombie5,zombie6,zombie7,zombie8,zombie9,zombie10;
	public player Player;
	public Timer timerZombie,timerDraw;
	public int cellsize=100;
	
	private class myKey implements KeyListener{
	
		public void keyPressed(KeyEvent arg0) {
		
			int key=arg0.getKeyCode();
			//if((key==KeyEvent.VK_UP)|| (key==KeyEvent.VK_W)) {Player.direction=1;	Player.move_head();}
			if((key==KeyEvent.VK_DOWN)||(key==KeyEvent.VK_S)) {Player.direction=3;	Player.move_head();}
			if((key==KeyEvent.VK_RIGHT)||(key==KeyEvent.VK_D)) {Player.direction=2;	Player.move_head();}
			if((key==KeyEvent.VK_LEFT)||(key==KeyEvent.VK_A))  {Player.direction=0;	Player.move_head();}
			
	
			if(key==KeyEvent.VK_ESCAPE) System.exit(0);
			
		}
		public void keyReleased(KeyEvent e) {			}
		public void keyTyped(KeyEvent e) {}
		}
	
	public void start() {
		Player=new player();
		Player.start(2, 2, false);
	//	playSound("sounds/theme.mid");
		level1();
	}
	
	public void level1() {
		zombie1=new Zombie();
		zombie1.start(3, 0,3);
	
			
		zombie2=new Zombie();
		zombie2.start(5, 0,4);
	
		zombie3=new Zombie();
		zombie3.start(3, 5, 1);
	
		
	}
	
	
	public myPanel()
	{
		
		pan=this;
		this.setFocusable(true);
		addKeyListener(new myKey());
		start();
		
		
		try
		{
			
			
			exit=ImageIO.read(getClass().getResource("/img/Exid.png"));
			newgame=ImageIO.read(getClass().getResource("/img/new game1.png"));
			ramka=ImageIO.read(getClass().getResource("/img/ramka.png"));
			background=ImageIO.read(getClass().getResource("/road/road1.png"));
			panel=ImageIO.read(getClass().getResource("/img/fon1.png"));
			player=Toolkit.getDefaultToolkit().createImage("./gif/police.gif");
			zombie=Toolkit.getDefaultToolkit().createImage("./gif/zomb1.gif");
			endg=ImageIO.read(getClass().getResource("/img/endg.png"));
			box=ImageIO.read(getClass().getResource("/img/box1.jpg"));
		}
		catch(Exception ex){}
		
		setLayout(null);
		
		lb1=new JLabel("Player 1:0");
		lb1.setForeground(Color.white);
		lb1.setFont(new Font("serif",1,30));
		lb1.setBounds(70,10,150,50);
		add(lb1);
		
		
		btn1=new JButton();
		//btn1.setText("Новая игра");
		btn1.setForeground(Color.white);
		btn1.setFont(new Font("serif",1,30));
		btn1.setBounds(610,30,200,100);
		btn1.setBorder(null);
		btn1.setContentAreaFilled(false);
		btn1.setIcon(new ImageIcon(newgame));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){		
				
				btn1.setFocusable(false);
				btn2.setFocusable(false);
				start();
				timerZombie.start();
				timerDraw.start();
			}});
		add(btn1);
		
		btn2=new JButton();
		btn2.setIcon(new ImageIcon(exit));
		//btn2.setText("Выход");
		btn2.setForeground(Color.white);
		btn2.setFont(new Font("serif",1,30));
		btn2.setBounds(610,150,200,100);
		btn2.setBorder(null);
		btn2.setContentAreaFilled(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);				
			}});
		add(btn2);
		
		timerDraw=new Timer(100,new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				
			}});
		timerDraw.start();
		
		
		timerZombie=new Timer(900,new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((zombie1.endg!=true) &&(zombie2.endg!=true)&&(zombie3.endg!=true))
				{	
					zombie1.move();
					zombie2.move();
					zombie3.move();
			
				lb1.setText(" "+Player.points);

				}
				
			}});
		timerZombie.start();
		
	}
public void playSound(String str) {
		
		try {
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(new File(str));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		}
		catch (Exception e) { System.out.print("Ошибка! Звук не найден!"); }
	}
		
	public void paintComponent(Graphics gr)	
	{
		 gr.drawImage(background, 0, 0,1150, 850,null);		
		 gr.setColor(Color.white);
		 
		 //фон и рамка
		 /*gr.drawImage(fon1, 10, 10,600, 600,null);
		 gr.drawImage(ramka, 5, 5,605, 605,null);*/		
		 
		 //отображение картинок игроков для счетчика очков
		 gr.drawImage(player, 0,0, cellsize, cellsize, null);
		// gr.drawImage(snake2_head_right, 630, 405, 35, 30, null);
		 
		 for(int i=0;i<10;i++){
			 for(int j=0;j<10;j++){	
				 
				 
				 		if (Player.points==10)gr.drawImage(box,50, 800, 25, 25, null);
				 		if (Player.points==20){gr.drawImage(box,80, 800, 25, 25, null);}
				 		if(Player.mas[i][j]==1) {gr.drawImage(player,j*cellsize, i*cellsize, cellsize, cellsize, null);
				 		gr.drawImage(box,30+j*cellsize, 30+i*cellsize, 25, 25, null);
				 		}
					else if(zombie1.mas[i][j]>=1) {
						gr.drawImage(zombie,j*cellsize, i*cellsize, cellsize, cellsize, null);
					}
			
					//Snake2		
					else if(zombie2.mas[i][j]>=1) {
						gr.drawImage(zombie,  j*cellsize, i*cellsize, cellsize, cellsize,null);
					}
					else if(zombie3.mas[i][j]>=1) {
						gr.drawImage(zombie,  j*cellsize, i*cellsize, cellsize, cellsize,null);
					}
					
					if(((zombie1.mas[j][i]>=1)&&(Player.mas[j][i]==1))||((zombie2.mas[j][i]>=1)&&(Player.mas[j][i]==1))||((zombie3.mas[j][i]>=1)&&(Player.mas[j][i]==1)))
		
					{
						zombie2.endg=true;
						zombie1.endg=true;
						zombie3.endg=true;
						Player.endg=true;
						timerZombie.stop();    
						}
			 }
		 }
		 
		 if(zombie1.endg==true) {
			 gr.drawImage(endg, 100,100, null);
		 }
		 if(zombie2.endg==true) {
			 gr.drawImage(endg, 100,100, null);
		 }
	}
		
}