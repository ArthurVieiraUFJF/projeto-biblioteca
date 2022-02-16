package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Biblioteca.Biblioteca;

public class usuarioMenu extends JFrame {
	private JPanel container;
	private JButton cadastroButton;
	private JTextField textField1;
	private JTextField textField2;
	private JButton sairButton;
	private JButton checarDisponibilidadeButton;
	private JButton requisitarDesbloqueioButton;
	private JButton prolongarPrazoButton;
	private JButton reservarObraButton;
	private JButton alugarObraButton;
	private JButton verObrasButton;
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

		cadastroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField1.getText();
				String senh = textField2.getText();
				Biblioteca envio = new Biblioteca();
				try {
					envio.cadastro();
				} catch (Exception ex) {
					ex.printStackTrace();
				}


			}
		});

	}




}
