//Haoqing Yan
//set up the GUI.
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TemperatureConverterGUI extends JFrame {

	public static void main(String[] args) {
		// Construct an object that has all the methods of JFrame
		TemperatureConverterGUI aWindow = new TemperatureConverterGUI();
		aWindow.setVisible(true);
	}
	private JLabel labelCelsius;
	private JLabel lblFahrenheit;
	private JTextField textField1;
	private JTextField textField2;

	// Set up the GUI
	public TemperatureConverterGUI() {
		// Make sure the program terminates when window closes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CtoF FtoC");
		setSize(238, 150);
		setLocation(200, 200);
		getContentPane().setLayout(null);
		labelCelsius = new JLabel("Celsius");
		labelCelsius.setBounds(10, 25, 105, 22);
		labelCelsius.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(labelCelsius);
		textField1 = new JTextField("");
		textField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double C = Double.parseDouble(textField1.getText());
				textField2.setText(TemperatureConverter.CtoF(C) + "");
			}
		});
		textField1.setBounds(107, 22, 105, 31);
		getContentPane().add(textField1);

		lblFahrenheit = new JLabel("Fahrenheit");
		lblFahrenheit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFahrenheit.setBounds(10, 74, 87, 22);
		getContentPane().add(lblFahrenheit);

		textField2 = new JTextField("");
		textField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double F = Double.parseDouble(textField2.getText());
				textField1.setText(TemperatureConverter.FtoC(F) + "");
			}
		});
		textField2.setBounds(107, 71, 105, 31);
		getContentPane().add(textField2);

	}
}
