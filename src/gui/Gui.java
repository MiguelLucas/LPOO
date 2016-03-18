package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import logic.GameState;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui {

	private JFrame mainWindow;
	private JTextField textField_LabyrinthSize;
	private JTextField textField_NumberOfDragons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setBounds(100, 100, 526, 414);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);
		
		textField_LabyrinthSize = new JTextField();
		textField_LabyrinthSize.setText("11");
		textField_LabyrinthSize.setBounds(121, 47, 53, 20);
		mainWindow.getContentPane().add(textField_LabyrinthSize);
		textField_LabyrinthSize.setColumns(10);
		
		JLabel lblLabyrinthSize = new JLabel("Labyrinth size");
		lblLabyrinthSize.setBounds(35, 50, 76, 14);
		mainWindow.getContentPane().add(lblLabyrinthSize);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(10, 85, 101, 14);
		mainWindow.getContentPane().add(lblNumberOfDragons);
		
		textField_NumberOfDragons = new JTextField();
		textField_NumberOfDragons.setText("1");
		textField_NumberOfDragons.setColumns(10);
		textField_NumberOfDragons.setBounds(121, 82, 53, 20);
		mainWindow.getContentPane().add(textField_NumberOfDragons);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 113, 76, 20);
		mainWindow.getContentPane().add(comboBox);
		
		JLabel lblDragonType = new JLabel("Dragon Type");
		lblDragonType.setBounds(35, 116, 62, 14);
		mainWindow.getContentPane().add(lblDragonType);
		
		JButton btnGenerateNewLabyrinth = new JButton("Generate new labyrinth");
		btnGenerateNewLabyrinth.setBounds(297, 46, 200, 21);
		mainWindow.getContentPane().add(btnGenerateNewLabyrinth);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(297, 114, 62, 18);
		mainWindow.getContentPane().add(btnExit);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(23, 147, 302, 227);
		mainWindow.getContentPane().add(textArea);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(380, 148, 89, 23);
		btnUp.setEnabled(false);
		mainWindow.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(335, 182, 89, 23);
		btnLeft.setEnabled(false);
		mainWindow.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(431, 182, 89, 23);
		btnRight.setEnabled(false);
		mainWindow.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(380, 216, 89, 23);
		btnDown.setEnabled(false);
		mainWindow.getContentPane().add(btnDown);
		
		btnGenerateNewLabyrinth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameState game = new GameState();
				game.generateLabyrinth(Integer.parseInt(textField_LabyrinthSize.getText()));
				String lab = "";
				for(int i=0;i<game.getLabyrinth().getLabyrinth()[0].length;i++){
					for(int j=0;j<game.getLabyrinth().getLabyrinth()[1].length;j++){
						lab += game.getLabyrinth().getLabyrinth()[i][j];
						if (game.getLabyrinth().getLabyrinth()[i][j] == "  ")
							lab += " ";
					}
					lab += "\n";	
				}
				textArea.setText(lab);
			}
		});
	}
}
