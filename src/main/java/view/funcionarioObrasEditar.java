package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Biblioteca.Biblioteca;

public class funcionarioObrasEditar extends JFrame {
	private JPanel container;
	private JButton cadastroButton;
	private JTextField textField1;
	private JTextField textField2;
	private JComboBox comboBox1;
	private JCheckBox checkBox1;
	private JPanel reservadaCheck;
	private JCheckBox alugadaCheck;
	private JTextField textField3;
	private JTextField textField4;
	private JButton confirmarButton;
    private JComboBox comboBox2;
    private JButton voltarButton;
    private JPasswordField passwordField1;


	public  void render() {
		initialization();
		setContentPane(container);
		setTitle("Tela inicial");
		setSize(640, 480);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initialization() {

	}




}
