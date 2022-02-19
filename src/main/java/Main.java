import View.GUI;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
                GUI window = new GUI();
                window.frmLoanInvestment.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}