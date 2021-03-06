package ui;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

/**
 * @author Anton Chepurov
 */
public class ConsolePanel extends JPanel implements AdjustmentListener {

	private static final URL BACKGROUND_PICTURE_URL = ConsolePanel.class.getResource("BackgroundPicture2.png");
	private BufferedImage image;
	private boolean doDrawImage = true;

	public ConsolePanel() {
		try {
			image = javax.imageio.ImageIO.read(BACKGROUND_PICTURE_URL);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
	}

	public void add(Component comp, Object constraints) {
		((JComponent) comp).setOpaque(false);

		if (comp instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane) comp;
			JViewport viewPort = scrollPane.getViewport();
			viewPort.setOpaque(false);
			Component component = viewPort.getView();

			if (component instanceof JComponent) {
				((JComponent) component).setOpaque(false);
			}
		}

		super.add(comp, constraints);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (doDrawImage) {
			drawToViewPort(g);
		}
	}

	private void drawToViewPort(Graphics g) {
		JViewport viewPort = (JViewport) getParent();
		Rectangle visibleRectangle = viewPort.getVisibleRect();
		g.drawImage(image, 0, viewPort.getViewPosition().y, 190/*visibleRectangle.width / 3*/, visibleRectangle.height, this);
	}

	@SuppressWarnings({"UnusedDeclaration"})
	private void drawScaled(Graphics g) {
		Dimension size = getSize();
		g.drawImage(image, 0, 0, size.width / 3, size.height, null);
	}

	@SuppressWarnings({"UnusedDeclaration"})
	private void drawImage(Graphics g) {
		Dimension size = getSize();
		int x = size.width - image.getWidth();
		int y = size.height - image.getHeight();
		g.drawImage(image, x, y, this);
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		repaint();
	}

	public MouseAdapter getConsoleMouseAdapter() {
		return consoleMouseAdapter;
	}

	private final MouseAdapter consoleMouseAdapter = new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					doDrawImage = !doDrawImage;
					ConsolePanel.this.repaint();
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					Component component = e.getComponent();
					if (component instanceof JTextArea) {
						((JTextArea) component).setText("");
					}
				}
			} else {
				super.mouseClicked(e);
			}
		}
	};

}
