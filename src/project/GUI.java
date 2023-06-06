package project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used for ...
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class GUI extends JFrame {
    private Header headerProject;
    private JButton botonJugar, botonSalir, botonSi, botonNo, botonSiguienteNivel;
    private Escucha escucha;
    private Timer timer;
    private ControlIKnowThatWord controlIKnowThatWorld;
    private PanelPalabras panelPalabras;  //Llama el panelPalabras de la clase PanelPalabras
    private  List<String> palabrasMostradas;
    private boolean primerJuego;
    private int contadorPalabras;
    private int contadorPuntos;
    private Diccionario diccionario;

    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setSize(650, 444);
        this.setUndecorated(true);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        controlIKnowThatWorld = new ControlIKnowThatWord();
        escucha = new Escucha();
        //Set up JComponents
        headerProject = new Header("I Know That Word", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        panelPalabras = new PanelPalabras(controlIKnowThatWorld.pintarPalabra());
        panelPalabras.setBorder(BorderFactory.createTitledBorder(null, "Memoriza las Palabras", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.orange));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        panelPalabras.setFocusable(true);
        add(panelPalabras, constraints);
        //this.add(headerProject, BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        botonJugar = new JButton("Jugar");
        botonJugar.setPreferredSize(new Dimension(325,50));
        botonJugar.setFont(new Font("MONOSPACED", Font.BOLD,30));
        botonJugar.setBackground(Color.ORANGE);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonJugar,constraints);

        botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(325,50));
        botonSalir.setFont(new Font("MONOSPACED", Font.BOLD,30));
        botonSalir.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonSalir,constraints);

        botonSalir.addActionListener(escucha);
        botonJugar.addActionListener(escucha);

        botonSi = new JButton("Si");
        botonSi.setPreferredSize(new Dimension(325, 50));
        botonSi.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        botonSi.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonSi.setEnabled(false); // Botón desactivado al inicio
        botonSi.addActionListener(escucha);
        this.add(botonSi, constraints);

        botonNo = new JButton("No");
        botonNo.setPreferredSize(new Dimension(325, 50));
        botonNo.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        botonNo.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonNo.setEnabled(false); // Botón desactivado al inicio
        botonNo.addActionListener(escucha);
        this.add(botonNo, constraints);

        botonSiguienteNivel = new JButton("Siguiente Nivel");
        botonSiguienteNivel.setPreferredSize(new Dimension(650, 50));
        botonSiguienteNivel.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        botonSiguienteNivel.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonSiguienteNivel.setVisible(false); // Botón oculto al inicio
        botonSiguienteNivel.addActionListener(escucha);
        this.add(botonSiguienteNivel, constraints);


    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        private Timer timer;
        private Random random;
        public Escucha(){
            random = new Random();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==botonJugar){
                cambiarColor();
                botonJugar.setEnabled(false);
            } else if (e.getSource()==botonSalir) {
                System.exit(0);
            }
        }

        private void cambiarColor() {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                private int count = 0;
                @Override
                public void run() {
                    if (count>=10) {
                        timer.cancel();
                        return;
                    }

                    Color randomColor = getRandomColor();
                    String nuevaPalabra = controlIKnowThatWorld.pintarPalabra();
                    panelPalabras.pintarTexto(nuevaPalabra);
                    panelPalabras.setBackground(randomColor);
                    botonJugar.setEnabled(false);
                    count++;
                }
            }, 0, 5000);
        }


        private Color getRandomColor() {

            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return new Color(r,g,b);
        }
    }
}
