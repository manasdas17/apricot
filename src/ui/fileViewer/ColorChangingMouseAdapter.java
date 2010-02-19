package ui.fileViewer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

/**
 * <br><br>User: Anton Chepurov
 * <br>Date: 18.12.2008
 * <br>Time: 16:11:57
 */
public class ColorChangingMouseAdapter extends MouseAdapter {
    private final static ColorStore colorStore = new ColorStore();
    private final TableForm tableForm;

    public ColorChangingMouseAdapter(TableForm tableForm) {
        this.tableForm = tableForm;
    }

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (e.getButton() == MouseEvent.BUTTON3 && source instanceof JCheckBox) {
            toggleColor(((JCheckBox) source));
        }
    }

    private void toggleColor(JCheckBox checkBox) {
        tableForm.setColorFor(checkBox, colorStore.next(checkBox.getBackground()));
    }

    private static class ColorStore {
        private Color next(Color currentColor) {
            return ColorsEnum.deriveEnum(currentColor).next();
        }

        private enum ColorsEnum {
            YELLOW (Color.YELLOW),
            MAGENTA (Color.MAGENTA),
            CYAN (Color.CYAN),
            ORANGE (Color.ORANGE),
            PINK (Color.PINK),
            LIGHT_GREY (Color.LIGHT_GRAY),
            GREEN (Color.GREEN);

            private final Color color;

            ColorsEnum(Color color) {
                this.color = color;
            }

            Color next() {
                int nextIndex = (ordinal() + 1) % values().length;
                return values()[nextIndex].color;
            }

            static ColorsEnum deriveEnum(Color color) {
                for (ColorsEnum colorEnum : values()) {
                    if (colorEnum.color == color) {
                        return colorEnum;
                    }
                }
                return null;
            }
        }
    }
}