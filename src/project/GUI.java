package project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.util.List;
import java.util.Timer;


/**
 * This class is used for ...
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class GUI extends JFrame {
    private Header headerProject;
    private JButton botonJugar, botonSalir, botonAyuda, botonSi, botonNo;
    private JTextField nombre;
    private JLabel etiquetaNombre, etiquetaDificultad;
    private JComboBox dificultad;
    String[] niveles = { "Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4", "Nivel 5", "Nivel 6", "Nivel 7", "Nivel 8", "Nivel 9", "Nivel 10"};
    private JPanel panelDatos, panelResultados;
    private Escucha escucha;
    private ModeloIKnowThatWord controlIKnowThatWorld;
    private PanelPalabras panelPalabras;  //Llama el panelPalabras de la clase PanelPalabras
    private FileManager fileManager;
    private int contadorPalabras;
    private boolean responderSi;
    private List<String> palabrasMostradas;
    public static final String Ayuda = "-Al oprimir jugar se mostrar una palabra diferente cada 5 segundos las cuales tendras que \n"
            + " memorizar, luego se mostraran el doble de palabras y tendras que decidir cuales se mostraron y cuales no. \n"
            + "-Deberás presionar 'Si' si la palabra se mostro y 'No' si no fue asi. \n"
            + " Si quieres guardar tu progreso ingresa tu alias. \n"
            + "-Si te cansas de jugar o por el contrario sufres de epilepcia usa el boton 'Salir' para abandonas el juego.";
    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setSize(650, 444);
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        palabrasMostradas = new ArrayList<>();
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
        controlIKnowThatWorld = new ModeloIKnowThatWord();
        escucha = new Escucha();
        fileManager = new FileManager();
        //Set up JComponents
        headerProject = new Header("I Know That Word", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        //Se crea el panePalabras
        panelPalabras = new PanelPalabras(controlIKnowThatWorld.pintarPalabra());
        panelPalabras.setBorder(BorderFactory.createTitledBorder(null, "Memoriza las Palabras", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.orange));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        panelPalabras.setFocusable(true);
        add(panelPalabras, constraints);
        //this.add(headerProject, BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        //Se crea el botonJugar
        botonJugar = new JButton("Jugar");
        botonJugar.setPreferredSize(new Dimension(200,50));
        botonJugar.setFont(new Font("MONOSPACED", Font.BOLD,20));
        botonJugar.setBackground(Color.ORANGE);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight= 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonJugar,constraints);

        //Se crea el botonSalir
        botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(200,50));
        botonSalir.setFont(new Font("MONOSPACED", Font.BOLD,20));
        botonSalir.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight= 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(botonSalir,constraints);

        //Se crea el botonAyuda
        botonAyuda = new JButton("Ayuda");
        botonAyuda.setPreferredSize(new Dimension(200,50));
        botonAyuda.setFont(new Font("MONOSPACED", Font.BOLD, 20));
        botonAyuda.setBackground(Color.ORANGE);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight= 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonAyuda.addActionListener(escucha);
        this.add(botonAyuda,constraints);
        
        //Se crea el panel de los datos
        panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(220, 160));
        panelDatos.setBorder(BorderFactory.createTitledBorder(null, "Datos", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.orange));
        panelDatos.setBackground(new Color(128, 128, 155));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDatos, constraints);
        
        //Se crea el panel de los resultados
        panelResultados = new JPanel();
        panelResultados.setPreferredSize(new Dimension(220, 250));
        panelResultados.setBorder(BorderFactory.createTitledBorder(null, "Resultados", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.orange));
        panelResultados.setBackground(new Color(128, 128, 155));
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelResultados, constraints);
        
        //Se le asigna un actionListener a los botones
        botonSalir.addActionListener(escucha);
        botonAyuda.addActionListener(escucha);
        botonJugar.addActionListener(escucha);

        //Se crea el botonJugar
        botonSi = new JButton("Si");
        botonSi.setPreferredSize(new Dimension(200, 50));
        botonSi.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        botonSi.setBackground(Color.ORANGE);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonSi.setEnabled(false); // Botón desactivado al inicio
        botonSi.addActionListener(escucha);
        this.add(botonSi, constraints);

        //Se crea el botonNo
        botonNo = new JButton("No");
        botonNo.setPreferredSize(new Dimension(200, 50));
        botonNo.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        botonNo.setBackground(Color.ORANGE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        botonNo.setEnabled(false); // Botón desactivado al inicio
        botonNo.addActionListener(escucha);
        this.add(botonNo, constraints);

        //Se crea una etiqueta
        etiquetaNombre = new JLabel("Ingrese su Alias: ");
        etiquetaDificultad = new JLabel("Dificultad: ");
        Component espacio1 = Box.createHorizontalStrut(400);
        Component espacio2 = Box.createHorizontalStrut(400);
        Component espacio3 = Box.createHorizontalStrut(400);
        Component espacio4 = Box.createHorizontalStrut(400);
        Component espacio5 = Box.createHorizontalStrut(400);

        //Se crea un campo de texto para ingresar el alias
        nombre = new JTextField();
        nombre.setLayout(new FlowLayout());
        nombre.setPreferredSize(new Dimension(190,20));
        nombre.addActionListener(escucha);
        panelDatos.add(nombre, BorderLayout.CENTER);

        panelDatos.add(etiquetaNombre);
        panelDatos.add(espacio1);
        panelDatos.add(espacio2);
        panelDatos.add(espacio3);
        panelDatos.add(espacio4);
        panelDatos.add(espacio5);


        dificultad = new JComboBox<>(niveles);
        panelDatos.add(etiquetaDificultad);
        panelDatos.add(dificultad, BorderLayout.SOUTH);

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
        private int totalPoints;
        public Escucha(){
            totalPoints = 0; // Inicializa los puntos totales en 0
            random = new Random();
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            fileManager.writer(nombre.getText()); //Cuando se haga (enter), se va a sacar el texto del JTextField y lo guarda en el archivo de jugadores
            nombre.setText("");

            if (e.getSource() == botonJugar) {
                cambiarPalabra();
            } else if (e.getSource() == botonSalir) {
                System.exit(0); // Sale del programa
            } else if (e.getSource() == botonAyuda) {
                JOptionPane.showMessageDialog(null, Ayuda); // Muestra las ayudas
            } else if  (e.getSource() == botonSi && responderSi) {
                // Realiza la acción correspondiente al responder "Si"
                totalPoints++;
                //String palabraActual = controlIKnowThatWorld.pintarPalabra();
                /*
                if (palabrasMostradas.contains(palabraActual)) {
                    totalPoints++; // Incrementa los puntos totales si la palabra se mostró anteriormente
                }
                 */
            } else if (e.getSource() == botonNo && !responderSi){
                // Realiza la acción correspondiente al responder "No"
                totalPoints--;
                totalPoints++;
            }
        }

        private void cambiarPalabra() {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                private int count = 0;

                @Override
                public void run() {
                    if (count >= 10) {
                        timer.cancel();
                        cambiarEstadoJuego();
                        return;
                    }

                    Color randomColor = getRandomColor();

                    String palabra;

                    if (count < 10) {
                        palabra = controlIKnowThatWorld.pintarPalabra();
                        palabrasMostradas.add(palabra); // Agrega la palabra a la lista
                    } else {
                        int index = count - 10;
                        if (index >= palabrasMostradas.size()) {
                            index = index % palabrasMostradas.size();
                        }
                        palabra = palabrasMostradas.get(index);
                    }
                    panelPalabras.pintarTexto(palabra);
                    panelPalabras.setBackground(randomColor);
                    botonJugar.setEnabled(false);
                    if (count == 10) {
                        botonJugar.setVisible(false);
                        botonSalir.setVisible(false);
                        botonSi.setVisible(true);
                        botonNo.setVisible(true);
                        botonSi.setEnabled(true);
                        botonNo.setEnabled(true);
                    }
                    count++;
                }
            }, 0, 5000);  //La palabras se muestran con un intervalo de 5 segundos
        }


        private Color getRandomColor() {  //Obtiene el un color alatorio

            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return new Color(r,g,b);
        }
        private void cambiarEstadoJuego() {
            panelPalabras.pintarTexto(""); // Borra la palabra mostrada
            botonSi.setEnabled(true);
            botonNo.setEnabled(true);
            botonJugar.setVisible(false);
            botonSalir.setVisible(false);
            responderSi = true; // Cambia el estado del juego a responder "Si"

            timer = new Timer();
            timer.schedule(new TimerTask() {
                private int count = 0;

                @Override
                public void run() {
                    if (count >= 20) {
                        timer.cancel();
                        mostrarTotalPuntos();
                        //reiniciarJuego();
                        botonSi.setVisible(false);
                        botonNo.setVisible(false);
                        botonJugar.setVisible(true);
                        botonSalir.setVisible(true);
                        return;
                    }

                    Color randomColor = getRandomColor();
                    String palabra;

                    if (count < 10) {
                        palabra = palabrasMostradas.get(count);
                    } else {
                        palabra = controlIKnowThatWorld.pintarPalabra();
                        palabrasMostradas.add(palabra);
                    }

                    panelPalabras.pintarTexto(palabra);
                    panelPalabras.setBackground(randomColor);

                    // Cambiar los botones Jugar y Salir por los botones Si y No después de mostrar las primeras 10 palabras
                    if (count == 0) {
                        botonSi.setEnabled(true);
                        botonNo.setEnabled(true);
                    }

                    // Restablecer los botones Si y No después de mostrar las 20 palabras
                    if (count == 20) {
                        botonSi.setVisible(true);
                        botonNo.setVisible(true);
                        botonJugar.setVisible(false);
                        botonSalir.setVisible(false);
                    }

                    count++;
                }
            }, 0, 7000);  // Las siguientes 10 palabras se muestran con un intervalo de 7 segundos
        }
        private void reiniciarJuego() {
            contadorPalabras = 0;
            responderSi = false; // Cambia el estado del juego a mostrar palabras
            botonSi.setEnabled(false);
            botonNo.setEnabled(false);
            botonJugar.setVisible(true);
            botonSalir.setVisible(true);
            botonJugar.setEnabled(true);
        }
        public int getPuntos() {
            return totalPoints;
        }
        private void mostrarTotalPuntos() {
            panelResultados.removeAll();
            JLabel labelPuntos = new JLabel("Puntos totales: " + totalPoints);
            labelPuntos.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
            if (totalPoints>=14){
                JLabel labelGanaste = new JLabel("¡GANASTE!");
                labelGanaste.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
                panelResultados.add(labelGanaste);
            }else{
                JLabel labelPerdiste = new JLabel("¡Oh Peridiste! :(");
                labelPerdiste.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
                panelResultados.add(labelPerdiste);
            }
            panelResultados.add(labelPuntos);
            panelResultados.revalidate();
            panelResultados.repaint();
        }
    }
}