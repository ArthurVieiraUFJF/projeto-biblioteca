package view;

import javax.swing.*;

public class usuario extends JFrame {
	private JPanel container;
	private JButton adminButton;
	private JButton usuarioButton;
	private JButton cadastroButton;
    private JButton voltarButton;
    private JPanel form;
	private JPanel submitContainer;
	private JTable zoneTable;
	private JTextField nameField;
	private JTextArea descriptionField;

	private JButton submitButton;
	private JButton removeButton;
	private JButton backButton;

	private JPanel Waves;

	public void render() {
		setContentPane(container);
		setTitle("Tela inicial");
		setSize(640, 480);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}





}
