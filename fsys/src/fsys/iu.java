package fsys;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class iu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					iu frame = new iu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public iu() {
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"Z:\\home\\localhost\\www\\puh\\img\\\u0422\u0435\u043A\u0441\u0442\u0443\u0440\u044B\\cvet.jpg"));
		setFont(new Font("Arial", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		textField.setBounds(208, 10, 367, 32);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(
				"\u0423\u043A\u0430\u0436\u0438\u0442\u0435 \u0438\u043C\u044F \u0444\u0430\u0439\u043B\u0430:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 13, 188, 29);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText().trim();
				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Вы не указали имя файла", "Ошибка", 0);
				} else {
					File newfile = new File(str);
					try {
						newfile.createNewFile();
						JOptionPane.showMessageDialog(null, "Файл создан!", "Сообщение", 1);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Файл не создан!", "Ошибка", 0);
					}
				}

			}

		});
		btnNewButton.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		btnNewButton.setBounds(20, 63, 157, 20);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText().trim();
				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Путь файла не известен", "Ошибка", 0);
				} else {
					File nf = new File(str);
					if (nf.exists()) {
						nf.delete();
						JOptionPane.showMessageDialog(null, "Файл удалён!", "Сообщение", 1);
					} else {
						{
							JOptionPane.showMessageDialog(null, "Ошибка 404: файл не найден!", "Ошибка", 0);
						}
					}
				}
			}
		});
		button.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		button.setBounds(20, 117, 157, 20);
		contentPane.add(button);

		JButton button_1 = new JButton(
				"\u041F\u0435\u0440\u0435\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u0442\u044C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText().trim();

				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Вы не указали имя файла", "Ошибка", 0);
				} else {
					File newfile = new File(str);
					if (newfile.exists()) {
						String newName = JOptionPane.showInputDialog("Введите новое имя:");
						if (newName == null)
							newName = "";
						if (!newName.trim().isEmpty()) {
							File newFile2 = new File(newfile.getParent() + "\\" + newName);
							newfile.renameTo(newFile2);
							JOptionPane.showMessageDialog(null, "Файл переименован!", "Сообщение", 1);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ошибка 404: файл не найден!", "Ошибка", 0);
					}
				}

			}
		});
		button_1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		button_1.setBounds(20, 169, 157, 20);
		contentPane.add(button_1);

		JButton button_3 = new JButton("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText().trim();
				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Путь файла не известен", "Ошибка", 0);
				} else {
					File nf = new File(str);
					if (nf.exists()) {
						try {
							InputStream obj = new FileInputStream(str);
							BufferedReader in = new BufferedReader(new InputStreamReader(obj));
							String tmp = "";
							while (in.ready()) {
								tmp += (in.readLine() + "\n"); // Конкатенация
								textArea.setText(tmp);
								in.close();
								obj.close();
							}
						} catch (Exception e) {
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ошибка 404: файл не найден!", "Ошибка", 0);
					}
				}
			}
		});
		button_3.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		button_3.setBounds(20, 220, 157, 20);
		contentPane.add(button_3);

		JButton button_4 = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText().trim();
				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Путь файла не известен", "ОшибBufferedReaderка", 0);
				} else {
					File nf = new File(str);
					if (nf.exists()) {
						try {
							OutputStream obj = new FileOutputStream(str);
							BufferedWriter out = new BufferedWriter(new OutputStreamWriter(obj));
							out.write(textArea.getText());
							out.close();
							obj.close();
							JOptionPane.showMessageDialog(null, "Сохранено", "Сообщение", 1);
						} catch (Exception arg0) {
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ошибка 404: файл не найден!", "Ошибка", 0);
					}
				}
			}
		});
		button_4.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		button_4.setBounds(20, 272, 157, 20);
		contentPane.add(button_4);

		textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		textArea.setBounds(208, 59, 367, 293);
		contentPane.add(textArea);
	}
}
