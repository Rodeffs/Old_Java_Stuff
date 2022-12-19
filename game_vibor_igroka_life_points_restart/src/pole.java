import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class pole extends JPanel {
	private Image shapka, fon, end_game, life_img, fon2, fon3, fon4, fon5;
	public int x = 400, points = 0, life = 3, timer = 1500;
	private int slogn;
	public podar[] gamePodar;
	public Timer timerUpdate, timerDraw;
	String points_str;
	public boolean restart = false;
	private Image player1, player2;
	public int player = 0, player_img_number;
	public JButton btn_player1, btn_player2;
	public boolean start = false;
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension dm = tk.getScreenSize();

	public pole(int slogn) {
		this.slogn = slogn;
		try {
			player1 = ImageIO.read(new File("./pic_starwars//player//bb8.png"));
			player2 = ImageIO.read(new File("./pic_starwars//player//r2d2.png"));
			fon = ImageIO.read(new File("./pic_starwars//background//fon7.jpg"));
			fon2 = ImageIO.read(new File("./pic_starwars//background//fon2.jpg"));
			fon3 = ImageIO.read(new File("./pic_starwars//background//fon3.jpg"));
			fon4 = ImageIO.read(new File("./pic_starwars//background//fon4.jpg"));
			fon5 = ImageIO.read(new File("./pic_starwars//background//fon5.jpg"));
			end_game = ImageIO.read(new File("./pic_starwars//endg.png"));
			life_img = ImageIO.read(new File("./pic_starwars//ser.png"));
		} catch (IOException ex) {
		}
		gamePodar = new podar[7];
		for (int i = 0; i < 7; i++) {
			try {
				gamePodar[i] = new podar(ImageIO.read(new File("./pic_starwars//objects//object" + i + ".png")));
			} catch (IOException ex) {
			}
		}
		timerUpdate = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				updateStart();
			}
		});
		timerUpdate.setDelay(1700);
		timerDraw = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
				if ((player == 1) || (player == 2)) {
					remove(btn_player1);
					remove(btn_player2);
					timerUpdate.start();
					start = true;
					player_img_number = player;
					player = 0;
				}
			}
		});
		timerDraw.start();
		setLayout(null);
		btn_player1 = new JButton();
		btn_player1.setLocation(100, 100);
		btn_player1.setSize(250, 250);
		btn_player1.setFocusable(false);
		btn_player1.setContentAreaFilled(false);
		btn_player1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_player1.setIcon(new ImageIcon(player1));
		btn_player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = 1;
				life = 3;
				points = 0;
				for (int i = 0; i < 7; i++) {
					gamePodar[i].speed = 1;
				}
			}
		});
		add(btn_player1);
		btn_player2 = new JButton();
		btn_player2.setLocation(400, 100);
		btn_player2.setSize(250, 250);
		btn_player2.setFocusable(false);
		btn_player2.setContentAreaFilled(false);
		btn_player2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_player2.setIcon(new ImageIcon(player2));
		btn_player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = 2;
				life = 3;
				points = 0;
				for (int i = 0; i < 7; i++) {
					gamePodar[i].speed = 1;
				}
			}
		});
		add(btn_player2);
	}

	public void paintComponent(Graphics gr) {
		if (start == false) {
			gr.setColor(Color.lightGray);
			gr.fillRect(0, 0, 800, 100);
			gr.drawImage(fon, 0, 40, dm.width, dm.height, null);
			gr.setColor(Color.white);
			gr.setFont(new Font("serif", 1, 50));
			gr.drawString("Выберете игрока:", 200, 90);
		} else {
			super.paintComponent(gr);
			if (points < 10)
				gr.drawImage(fon, 0, 40, dm.width, dm.height, null);
			if ((points >= 10) && (points < 20))
				gr.drawImage(fon2, 0, 40, dm.width, dm.height, null);
			if ((points >= 20) && (points < 30))
				gr.drawImage(fon3, 0, 40, dm.width, dm.height, null);
			if ((points >= 30) && (points < 40))
				gr.drawImage(fon4, 0, 40, dm.width, dm.height, null);
			if (points >= 40)
				gr.drawImage(fon5, 0, 40, dm.width, dm.height, null);
			if (player_img_number == 1)
				gr.drawImage(player1, x, dm.height-150, null);
			if (player_img_number == 2)
				gr.drawImage(player2, x, dm.height-150, null);
			setBackground(Color.GRAY);
			points_str = "очки:" + points;
			gr.setColor(Color.WHITE);
			gr.setFont(new Font("serif", 1, 35));
			gr.drawString(points_str, 10, 30);
			if (life == 3) {
				gr.drawImage(life_img, 720, 10, null);
				gr.drawImage(life_img, 745, 10, null);
				gr.drawImage(life_img, 770, 10, null);
			}
			if (life == 2) {
				gr.drawImage(life_img, 745, 10, null);
				gr.drawImage(life_img, 770, 10, null);
			}
			if (life == 1) {
				gr.drawImage(life_img, 770, 10, null);
			}
			for (int i = 0; i < 7; i++) {
				if (points == 10) {
					gamePodar[i].speed = 5;
					timerUpdate.setDelay(1600);
				} else if (points == 20) {
					gamePodar[i].speed = 5;
					timerUpdate.setDelay(1100);
				} else if (points == 30) {
					gamePodar[i].speed = 5;
					timerUpdate.setDelay(1000);
				} else if (points == 40) {
					gamePodar[i].speed = 5;
					timerUpdate.setDelay(700);
				}
				gamePodar[i].draw(gr);
				if (gamePodar[i].act == true) {
					if ((gamePodar[i].y + gamePodar[i].img.getHeight(null)) >= 550) {
						if (Math.abs(gamePodar[i].x - x) > 120) {
							life--;
							gamePodar[i].act = false;
							if (life <= 0) {
								gr.drawImage(end_game, 200, 50, null);
								timerDraw.stop();
								timerUpdate.stop();
								restart = true;
								break;
							}
						} else {
							gamePodar[i].act = false;
							points += 1;
							points_str = "ОЧКИ:" + points;
							gr.setColor(Color.WHITE);
							gr.drawString(points_str, 10, 30);
						}
					}
				}
			}
		}
	}

	private void updateStart() {
		int kol = 0;
		for (int i = 0; i < 7; i++) {
			if (gamePodar[i].act == false) {
				if (kol < slogn) {
					gamePodar[i].start();
					break;
				}
			} else
				kol++;
		}
	}
}