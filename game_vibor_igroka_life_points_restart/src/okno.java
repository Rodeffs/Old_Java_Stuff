
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class okno extends JFrame {
	private pole gameP;
	private int slogn;
	JButton btn;
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension dm = tk.getScreenSize();

	private class myKey implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int key_ = e.getKeyCode();
			if (key_ == 27)
				System.exit(0);
			else if (key_ == KeyEvent.VK_LEFT) {
				if (gameP.x - 30 > -48)
					gameP.x  -= 30;
				else
					gameP.x = dm.width-30;
			} else if (key_ == KeyEvent.VK_RIGHT) {
				if (gameP.x + 30 < dm.width - 30)
					gameP.x += 45;
				else
					gameP.x = -88;
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	public okno(int slogn) {
		this.slogn = slogn;
		addKeyListener(new myKey());
		setFocusable(true);
		setBounds(0, 0, dm.width, dm.height);
		setTitle("Игра: STAR WARS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameP = new pole(slogn);
		Container con = getContentPane();
		con.add(gameP);
		setVisible(true);
		gameP.setLayout(null);
		btn = new JButton();
		btn.setLocation(250, 1);
		btn.setFont(new Font("serif", 1, 20));
		btn.setFocusable(false);
		btn.setContentAreaFilled(false);
		btn.setSize(250, 30);
		btn.setText("начать игру заново");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameP.start = false;
				gameP.add(gameP.btn_player1);
				gameP.add(gameP.btn_player2);
				gameP.timerUpdate.stop();
				gameP.timerDraw.start();
				for (int i = 0; i < 7; i++)
					gameP.gamePodar[i].act = false;
			}
		});
		gameP.add(btn);
	}
}