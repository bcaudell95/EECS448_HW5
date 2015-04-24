import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;

import javax.swing.ButtonGroup;

import java.awt.Insets;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {

	private JPanel mainPanel;
	private final ButtonGroup methodTypeButtonGroup = new ButtonGroup();
	private JRadioButton[] radioButtons = new JRadioButton[5];

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{120, 25, 140, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setBorder(new TitledBorder(null, "Classification Method", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		GridBagConstraints gbc_radioButtonPanel = new GridBagConstraints();
		gbc_radioButtonPanel.insets = new Insets(0, 0, 5, 5);
		gbc_radioButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_radioButtonPanel.gridx = 0;
		gbc_radioButtonPanel.gridy = 0;
		mainPanel.add(radioButtonPanel, gbc_radioButtonPanel);
		GridBagLayout gbl_radioButtonPanel = new GridBagLayout();
		gbl_radioButtonPanel.columnWidths = new int[]{0, 0};
		gbl_radioButtonPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_radioButtonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_radioButtonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		radioButtonPanel.setLayout(gbl_radioButtonPanel);
		
		JRadioButton euclidianDistanceRadioButton = new JRadioButton("Euclidian Distance");
		euclidianDistanceRadioButton.setSelected(true);
		GridBagConstraints gbc_euclidianDistanceRadioButton = new GridBagConstraints();
		gbc_euclidianDistanceRadioButton.anchor = GridBagConstraints.WEST;
		gbc_euclidianDistanceRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_euclidianDistanceRadioButton.gridx = 0;
		gbc_euclidianDistanceRadioButton.gridy = 0;
		radioButtonPanel.add(euclidianDistanceRadioButton, gbc_euclidianDistanceRadioButton);
		methodTypeButtonGroup.add(euclidianDistanceRadioButton);
		this.radioButtons[0] = euclidianDistanceRadioButton;
		
		JRadioButton mahalanokianDistanceRadioButton = new JRadioButton("Mahalanokian Distance ");
		GridBagConstraints gbc_mahalanokianDistanceRadioButton = new GridBagConstraints();
		gbc_mahalanokianDistanceRadioButton.anchor = GridBagConstraints.WEST;
		gbc_mahalanokianDistanceRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_mahalanokianDistanceRadioButton.gridx = 0;
		gbc_mahalanokianDistanceRadioButton.gridy = 1;
		radioButtonPanel.add(mahalanokianDistanceRadioButton, gbc_mahalanokianDistanceRadioButton);
		methodTypeButtonGroup.add(mahalanokianDistanceRadioButton);
		this.radioButtons[1] = mahalanokianDistanceRadioButton;
		
		JRadioButton euclidianVotingRadioButton = new JRadioButton("Euclidian Voting");
		GridBagConstraints gbc_euclidianVotingRadioButton = new GridBagConstraints();
		gbc_euclidianVotingRadioButton.anchor = GridBagConstraints.WEST;
		gbc_euclidianVotingRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_euclidianVotingRadioButton.gridx = 0;
		gbc_euclidianVotingRadioButton.gridy = 2;
		radioButtonPanel.add(euclidianVotingRadioButton, gbc_euclidianVotingRadioButton);
		methodTypeButtonGroup.add(euclidianVotingRadioButton);
		this.radioButtons[2] = euclidianVotingRadioButton;
		
		JRadioButton mahalanokianVotingRadioButton = new JRadioButton("Mahalanokian Voting");
		GridBagConstraints gbc_mahalanokianVotingRadioButton = new GridBagConstraints();
		gbc_mahalanokianVotingRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_mahalanokianVotingRadioButton.anchor = GridBagConstraints.WEST;
		gbc_mahalanokianVotingRadioButton.gridx = 0;
		gbc_mahalanokianVotingRadioButton.gridy = 3;
		radioButtonPanel.add(mahalanokianVotingRadioButton, gbc_mahalanokianVotingRadioButton);
		methodTypeButtonGroup.add(mahalanokianVotingRadioButton);
		this.radioButtons[3] = mahalanokianVotingRadioButton;
		
		JRadioButton customMethodRadioButton = new JRadioButton("Custom Method");
		methodTypeButtonGroup.add(customMethodRadioButton);
		customMethodRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_customMethodRadioButton = new GridBagConstraints();
		gbc_customMethodRadioButton.anchor = GridBagConstraints.WEST;
		gbc_customMethodRadioButton.gridx = 0;
		gbc_customMethodRadioButton.gridy = 4;
		radioButtonPanel.add(customMethodRadioButton, gbc_customMethodRadioButton);
		this.radioButtons[4] = customMethodRadioButton;
		
		final JPanel graphPanel = new GraphPanel();
		graphPanel.setBorder(new TitledBorder(null, "Ratio (AA/AN)", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		GridBagConstraints gbc_graphPanel = new GridBagConstraints();
		gbc_graphPanel.insets = new Insets(0, 0, 5, 0);
		gbc_graphPanel.gridheight = 3;
		gbc_graphPanel.fill = GridBagConstraints.BOTH;
		gbc_graphPanel.gridx = 1;
		gbc_graphPanel.gridy = 0;
		mainPanel.add(graphPanel, gbc_graphPanel);
		
		JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int radioButtonSelected = getRadioButtonSelected();
				double ratio=0;
				switch(radioButtonSelected) {
				case 0:
					ratio = ClassificationMethods.EuclidianDistanceMethod();
					break;
				case 1:
					ratio = ClassificationMethods.MahalanokiDistanceMethod();
					break;
				case 2:
					ratio = ClassificationMethods.EuclidianVotingMethod();
					break;
				case 3:
					ratio = ClassificationMethods.MahalanokiVotingMethod();
					break;
                case 4:
                    ratio = ClassificationMethods.CustomMethod();
                    break;
				}
				if(ratio!=0)
					((GraphPanel) graphPanel).setGraphPoint(radioButtonSelected, ratio);
			}
		});
		GridBagConstraints gbc_calculateButton = new GridBagConstraints();
		gbc_calculateButton.insets = new Insets(0, 0, 5, 5);
		gbc_calculateButton.gridx = 0;
		gbc_calculateButton.gridy = 1;
		mainPanel.add(calculateButton, gbc_calculateButton);
	}

	protected int getRadioButtonSelected() {
		for(int i=0;i<this.radioButtons.length;i++) {
			if(this.radioButtons[i].isSelected())
				return i;
		}
		return -1;
	}

}
