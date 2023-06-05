package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;

public class PanelPalabras extends JPanel {
    public static final int WIDTH = 600;
    public static final int HEIGTH = 330;
    private Font font;
    private String palabra, errores;

    //Constructor de la clase
    public PanelPalabras(String palabra){  //Trae la clave
        this.palabra = palabra;
        font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC,70);
        this.setBackground(new Color(128,128,155));
        setPreferredSize(new Dimension(WIDTH, HEIGTH));  //Establece el tamaño por defecto del panel
    }

    public void pintarTexto(String palabra) {  //pinta la clave
        this.palabra = palabra;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(font);
        g.setColor(new Color(0,0,0));
        g.drawString(palabra,220, HEIGTH/2);
    }

    public void reset(String palabra){
        this.palabra = palabra;
        errores = " ";
        repaint();
    }

}
