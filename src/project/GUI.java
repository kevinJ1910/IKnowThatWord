package project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class GUI extends JFrame {

    private Header headerProject;
    private JPanel panelPrincipal;
    private JButton botonJugar, botonSalir, botonSi, botonNo;
    private Escucha escucha;
    private String clave;
    private ControlIKnowThatWorld controlIKnowThatWorld;
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
        controlIKnowThatWorld = new ControlIKnowThatWorld();
        //Set up JComponents
        headerProject = new Header("I Know That Word", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);


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

        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(650,330));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(null, "Memoriza las Palabras", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.orange));
        panelPrincipal.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelPrincipal,constraints);

        escucha = new Escucha();
        botonSalir.addActionListener(escucha);
        botonJugar.addActionListener(escucha);
    }

    public void reset(String clave) {
        this.clave = clave;
        repaint();
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
        JTextField cajaTexto;
        private String clave;
        @Override
        public void actionPerformed(ActionEvent e) {
          if (e.getSource()==botonJugar){
              cajaTexto = new JTextField("Ingrese su alias");
              cajaTexto.setBounds(230,100,200,30);
              //cajaTexto.getText();
              cajaTexto.setHorizontalAlignment(JTextField.CENTER);
              panelPrincipal.add(cajaTexto);
              //panelPrincipal.repaint();
              }

          else if (e.getSource()==botonSalir){
              System.exit(0); //cierra el programa
          }
        }
    }
}
