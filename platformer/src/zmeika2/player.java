package zmeika2;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class player {
	
	int mas[][];
	public int direction;
	public int headX,headY;
	public int points; 
	public boolean endg, apple;
	public int n=10;
	
	public player() { 
		mas=new int[n][n];
		
		
	}
	
	public void start(int headX,int headY, boolean apple) {		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				
				mas[i][j]=0;
				
			}
		}
		mas[headX][headY] = 1;
	
		this.headX = headY;
		this.headY = headX;
		endg = false;
		
		if(apple==true) apple();
	}
	
	public void apple() {
		
		while(true) {
			int x=(int)(Math.random()*27)+1;
			int y=(int)(Math.random()*27)+1;
			 
			if(mas[x][y]==0){
			   mas[x][y]=-1;
			   break;
			}
		}
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
	public void move_head() {
		mas[headY][headX]=0;
		
		if (direction == 0)//  влево
		{
			if ((headX - 1) >= 0)  headX--;
			else headX = n-1;

		} else if (direction == 1)//  вверх
		{
			if ((headY - 1) >= 0)  headY--;
			else headY = n-1;

		} else if (direction == 2)//  вправо
		{
			if ((headX + 1) <n)  headX++;
			else  headX = 0;
		} else if (direction == 3)//  вниз
		{
			if ((headY + 1) < n)  headY++;
			else { headY = 0;
			points+=10;
			}
		}
		if(mas[headY][headX]==-1)//Если съеден объект
		{
			apple();//Генерируем новый объект для поедания
			
			points+=10;//Увеличиваем количество очков на 10
		}

		mas[headY][headX]=1;//Помещаем голову змейки в новую позицию
	}
	
	
	
	public void eat() {
		
  	  apple();
  	  points+=10;
  	  playSound("sounds/omnomnom.wav");
  	  
	}
	
	
	}
	
	


