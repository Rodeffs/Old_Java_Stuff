package project1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TYu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TYu frame = new TYu();
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
	public TYu() {
		setTitle("SSG");
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 193, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField frmtdtxtfldDdddd = new JFormattedTextField();
		frmtdtxtfldDdddd.setEditable(false);
		frmtdtxtfldDdddd.setBounds(10, 11, 114, 29);
		frmtdtxtfldDdddd.setForeground(Color.DARK_GRAY);
		frmtdtxtfldDdddd.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD | Font.ITALIC, 18));
		frmtdtxtfldDdddd.setText("Input login:");
		contentPane.add(frmtdtxtfldDdddd);
		
		JFormattedTextField frmtdtxtfldInputPassword = new JFormattedTextField();
		frmtdtxtfldInputPassword.setEditable(false);
		frmtdtxtfldInputPassword.setText("Input password:");
		frmtdtxtfldInputPassword.setForeground(Color.DARK_GRAY);
		frmtdtxtfldInputPassword.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD | Font.ITALIC, 18));
		frmtdtxtfldInputPassword.setBounds(10, 86, 156, 29);
		contentPane.add(frmtdtxtfldInputPassword);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		formattedTextField.setBounds(10, 55, 156, 29);
		contentPane.add(formattedTextField);
		
		JFormattedTextField frmtdtxtfldS = new JFormattedTextField();
		frmtdtxtfldS.setText("s");
		frmtdtxtfldS.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		frmtdtxtfldS.setBounds(10, 126, 156, 29);
		contentPane.add(frmtdtxtfldS);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Success!");
				System.exit(0);
			}
		});
		btnDone.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		btnDone.setBounds(10, 159, 89, 23);
		contentPane.add(btnDone);
	}
}
