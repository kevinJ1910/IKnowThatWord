package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.util.*;
import java.util.Timer;

public class PanelPalabras extends JPanel {
    public static final int WIDTH = 650;
    public static final int HEIGTH = 330;
    private Font font;
    private String palabra, errores;
    private int count;


    //Constructor de la clase
    public PanelPalabras(String palabra){  //Trae la clave
        this.palabra = palabra;
        font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC,70);

        this.setBackground(new Color(128,128,155));
        setPreferredSize(new Dimension(WIDTH, HEIGTH));  //Establece el tamaño por defecto del panel
        count = 0;
    }

    public void pintarTexto(String palabra) {  //pinta la clave
        this.palabra = palabra;
        count++;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (count>0) {
            g.setFont(font);
            g.setColor(new Color(0, 0, 0));
            g.drawString(palabra, 220, HEIGTH / 2);
        }
        /*
        if (count>=1){
            for (count = 0; count < 10; count++) {
                g.setFont(font);
                g.setColor(new Color(0, 0, 0));
                g.drawString(palabra, 220, HEIGTH / 2);
            }
        }

         */

    }




    public void reset(String palabra){
        this.palabra = palabra;
        errores = " ";
        repaint();
    }

}
