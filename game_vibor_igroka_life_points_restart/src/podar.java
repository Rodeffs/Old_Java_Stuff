
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class podar {

	public Image img;
	public int x, y;
	public Boolean act;
	Timer timerUpdate;
	public int speed = 1;

	public podar(Image img) {
		timerUpdate = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vniz();
			}
		});
		this.img = img;
		act = false;
	}

	public void start() {
		timerUpdate.start();
		y = 40;
		x = (int) (Math.random() * 700);
		act = true;
	}

	public void vniz() {
		if (act == true) {
			y += speed;
		}
		if ((y + img.getHeight(null)) >= 550) {
			timerUpdate.stop();
		}
	}

	public void draw(Graphics gr) {
		if (act == true) {
			gr.drawImage(img, x, y, null);
		}
	}
}
