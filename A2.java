/*
 *  ============================================================================================
 *  A2.java : Extends JFrame and contains a panel where shapes move around on the screen.
 *  YOUR UPI: xlad198
 *  ============================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class A2  extends JFrame {
	private AnimationViewer panel;  // panel for bouncing area
	JComboBox<ShapeType> shapesComboBox;
	JComboBox<PathType> pathComboBox;
	JTextField heightText, widthText;
	JButton borderButton, fillButton;

	/** main method for A2 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new A2();
			}
		});
	}
	/** constructor to initialise components */
	public A2() {
		super("Bouncing Application");
		panel = new AnimationViewer(true);
		add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(Shape.DEFAULT_MARGIN_WIDTH, Shape.DEFAULT_MARGIN_HEIGHT));
		add(setUpToolsPanel(), BorderLayout.NORTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					panel.resetMarginSize();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		pack();
		setVisible(true);
	}
	public JPanel setUpToolsPanel() {
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));

		shapesComboBox = new JComboBox<ShapeType>();
		shapesComboBox.setModel(new DefaultComboBoxModel<ShapeType>(ShapeType.values()));
		shapesComboBox.setToolTipText("Set shape");
		//Complete this: set up actionListener
		shapesComboBox.addActionListener( new ShapeActionListener());
		pathComboBox = new JComboBox<PathType>();
		pathComboBox.setModel(new DefaultComboBoxModel<PathType>(PathType.values()));
		pathComboBox.setToolTipText("Set path");
		//Complete this: set up actionListener
		pathComboBox.addActionListener( new PathActionListener());
		heightText = new JTextField(""+ Shape.DEFAULT_HEIGHT);
		heightText.setToolTipText("Set Height (" + Shape.DEFAULT_MARGIN_HEIGHT/2 + ")");
		//Complete this: set up actionListener
		heightText.addActionListener( new HeightActionListener());
		widthText = new JTextField(""+ Shape.DEFAULT_WIDTH);
		widthText.setToolTipText("Set Width (" + Shape.DEFAULT_MARGIN_WIDTH/2 + ")");
		//Complete this: set up actionListener
		widthText.addActionListener( new WidthActionListener());
		fillButton = new JButton("Fill");
		fillButton.setToolTipText("Set Fill Color");
		fillButton.setForeground(panel.getCurrentFillColor());
		//Complete this: set up actionListener
		fillButton.addActionListener( new FillActionListener());
		borderButton = new JButton("Border");
		borderButton.setToolTipText("Set Border Color");
		borderButton.setForeground(panel.getCurrentBorderColor());
		//Complete this: set up actionListener
		borderButton.addActionListener( new BorderActionListener());
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthText);
		toolsPanel.add( new JLabel(" Height: ", JLabel.RIGHT));
		toolsPanel.add(heightText);
		toolsPanel.add(borderButton);
		toolsPanel.add(fillButton);
		return toolsPanel;
	}
	class ShapeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.setCurrentShapeType(shapesComboBox.getSelectedIndex());
		}
	}

	class PathActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.setCurrentPathType(pathComboBox.getSelectedIndex());
		}
	}

	class WidthActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			if (Integer.parseInt(widthText.getText()) > 1 &&
					Integer.parseInt(widthText.getText()) < Shape.DEFAULT_MARGIN_WIDTH / 2) {
				panel.setCurrentWidth(Integer.parseInt(widthText.getText()));
			} else {
				throw new NumberFormatException();
			}
			} catch (NumberFormatException numberFormatException) {
				widthText.setText("" + panel.getCurrentWidth());
			}
		}
	}

	class HeightActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (Integer.parseInt(heightText.getText()) > 1 &&
						Integer.parseInt(heightText.getText()) < Shape.DEFAULT_MARGIN_WIDTH / 2) {
					panel.setCurrentHeight(Integer.parseInt(heightText.getText()));
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException numberFormatException) {
				heightText.setText("" + panel.getCurrentHeight());
			}
		}
	}

	class FillActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Fill Color", panel.getCurrentFillColor());
			if (newColor != null) {
				fillButton.setForeground(newColor);
				panel.setCurrentFillColor(newColor);
				System.out.println("The selected color is : " + newColor);
			} else {
				System.out.println("The selected color is : null");
			}
		}
	}

	class BorderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Border Color", panel.getCurrentBorderColor());
			if (newColor != null) {
				borderButton.setForeground(newColor);
				panel.setCurrentBorderColor(newColor);
				System.out.println("The selected color is : " + newColor);
			} else {
				System.out.println("The selected color is : null");
			}
		}
	}
}

