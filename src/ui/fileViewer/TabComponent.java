package ui.fileViewer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Anton Chepurov
 */
public class TabComponent extends JPanel {

	private final JTabbedPane tabbedPane;
	
	private final TabButton button;

	public TabComponent(JTabbedPane tabbedPane, String title, String toolTip, MouseListener mouseListener) {

		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.tabbedPane = tabbedPane;
		setOpaque(false);

		/* Make JLabel read title from tabbedPane */
		JLabel label = new JLabel(title);
		setToolTipText(toolTip);

		add(label);

		add(Box.createHorizontalStrut(5));

		add(button = new TabButton());

		addMouseListener(mouseListener);
	}

	public JButton getButton() {
		return button;
	}

	private class TabButton extends JButton implements ActionListener {

		private TabButton() {
			int size = 17;
			setPreferredSize(new Dimension(size, size));
			setUI(new BasicButtonUI());
			/* Make button transparent */
			setContentAreaFilled(false);
			/* No need to be focusable */
			setFocusable(false);
			/* Create future animation border */
			setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);
			/* Same listener for all buttons */
			addMouseListener(BUTTON_MOUSE_LISTENER);
			setRolloverEnabled(true);

			/* Close the proper tab by clicking the button */
			addActionListener(TabButton.this);
		}

		/**
		 * Disable update of the component
		 */
		public void updateUI() {
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			/* Shift the image for pressed buttons */
			if (getModel().isPressed()) {
				g2.translate(1, 1);
			}

			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.RED);

			int delta = 6;
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
			g2.dispose();
		}

		public void actionPerformed(ActionEvent e) {
			int index = tabbedPane.indexOfTabComponent(TabComponent.this);
			if (index != -1) {
				tabbedPane.remove(index);
				System.gc();
			}
		}
	}

	private final static MouseListener BUTTON_MOUSE_LISTENER = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				((AbstractButton) component).setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				((AbstractButton) component).setBorderPainted(false);
			}
		}
	};
}
