package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Biblioteca.Biblioteca;

public class adminMenu extends JFrame {
	private JPanel container;
	private JButton cadastroButton;
	private JTextField textField1;
	private JTextField textField2;
	private JButton sairButton;
	private JButton desbloquearUsuarioButton;
	private JButton bloquearUsuarioButton;
	private JButton cadastrarFuncionarioButton;
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
