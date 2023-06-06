package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Esta clase pinta las palabras a memorizar.
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class PanelPalabras extends JPanel {
    public static final int WIDTH = 600;
    public static final int HEIGTH = 330;
    private Font font;
    private String palabra, errores;
    private ArrayList<String> palabras;
    private int indiceActual;

    //Constructor de la clase
    public PanelPalabras(String palabra){  //Trae la palabra
        this.palabra = "";
        palabras = new ArrayList<>();
        indiceActual = 0;
        font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC,70);
        this.setBackground(new Color(128,128,155));
        setPreferredSize(new Dimension(WIDTH, HEIGTH));  //Establece el tamaño por defecto del panel

    }

    public void pintarTexto(String palabra) {  //pinta la palabra
        this.palabra = palabra;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (palabra != null) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(palabra, WIDTH / 3, HEIGTH / 2);
        }
/*
        g.setFont(font);
        g.setColor(new Color(0, 0, 0));
        g.drawString(palabra, WIDTH/3, HEIGTH / 2); //Pintal al palabra y la ubica en el centro

 */
    }

    public void reset(String palabra){  //restablece el juego al inicio
        this.palabra = palabra;
        errores = " ";
        repaint();
    }

    public String obtenerTexto() {
        return palabra;
    }
}
