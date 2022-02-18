package View;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the results page within the View.GUI.
 * When a user enters account information, the history of payment periods
 * is shown within the results page.
 *
 * @author Phil Edie
 */
public class ResultsTab extends JPanel {

    private final ResultsPanel panel;

    public ResultsTab(GUI gui) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        add(scrollPane, gbc_scrollPane);

        panel = new ResultsPanel(gui);
        scrollPane.setViewportView(panel);
    }

    public void update() {
        panel.update();
    }
}
