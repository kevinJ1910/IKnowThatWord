package project;

import javax.swing.*;
import java.awt.*;
/**
 * Encabezado del proyecto
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */

public class Header extends JLabel {
    /**
     * Constructor of the Header class
     */
    public Header(String title, Color colorBackground) {
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(new Color(255, 255, 255));
        this.setFont(new Font("Tahoma", Font.BOLD+Font.ITALIC,50));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}
