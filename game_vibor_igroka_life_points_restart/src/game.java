
import javax.swing.*;

public class game {

	public static void main(String[] args) {
		String rez = JOptionPane.showInputDialog(null, "¬ведите сложность игры от 1 до 7:", "—ложность игры", 1);
		int slogn = rez.charAt(0) - '0';
		if ((slogn >= 1) && (slogn <= 7)) {
			okno window = new okno(slogn);
		}
	}
}