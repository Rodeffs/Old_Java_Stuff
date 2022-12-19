package zmeika2;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Zombie {
	
	int mas[][];
	public int direction,new_direction;
	public int headX,headY;
	public int points; 
	public int length;
	public boolean endg;
	
	
	public Zombie() { 
		mas=new int[10][10];
		
		
	}
	
	public void start(int headX,int headY, int length) {		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				
				mas[i][j]=0;
				
			}
		}
	
			
		mas[headX][headY] = 1;
		this.length =length;
		this.headX = headY;
		this.headY = headX;
		endg = false;
		
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
	public int move_head() {
		if (direction == 0)//  влево
		{
			if ((headX - 1) >= 0)  headX--;
			else headX = 9;

		} else if (direction == 1)//  вверх
		{
			if ((headY - 1) >= 0)  headY--;
			else headY = 9;

		} else if (direction == 2)//  вправо
		{
			if ((headX + 1) <= 9)  headX++;
			else  headX = 0;
		} else if (direction == 3)//  вниз
		{
			if ((headY + 1) <= 9)  headY++;
			else  headY = 0;
		}
		int rez=0;
		if(mas[headY][ headX]==-1)rez=1;
		else if(mas[headY][ headX]==0) rez=2;
		else if(mas[headY][ headX]>0) rez=3;
		mas[headY][ headX]=-2;
		return rez;
	}
	
	public void move()
	{
		int flag=move_head();
		
		if(flag==3) endg=true;
		
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				
				if(mas [i][j]>0) mas[i][j]++;
				
				else if (mas[i][j]==-2) mas[i][j]=1;
				
				if (flag!=1)
				{
					if(mas[i][j]==(length+1)) mas[i][j]=0;
				}
			}
		}  
      
	   } }
	
	
	
	
	
	


