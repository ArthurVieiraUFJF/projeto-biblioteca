package view;

import javax.swing.*;
import java.awt.event.*;

public class telaInicial extends JFrame {
	private JPanel container;
	private JButton administradorButton;
	private JButton usuarioButton;
	private JButton funcionarioButton;


	public void render() {
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
