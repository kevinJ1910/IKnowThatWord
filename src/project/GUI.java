package project;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


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
    private JTextField nombre, nivel;
    private JLabel etiquetaNombre, etiquetaDificultad;
    private JComboBox dificultad;
    String[] niveles = { "Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4", "Nivel 5", "Nivel 6", "Nivel 7", "Nivel 8", "Nivel 9", "Nivel 10"};
    private JPanel panelDatos, panelResultados;
    private Escucha escucha;
    private ModeloIKnowThatWord controlIKnowThatWorld;
    private PanelPalabras panelPalabras;  //Llama el panelPalabras de la clase PanelPalabras
    private FileManager fileManager;
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
        constraints.gridy = 2;
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
        constraints.gridy = 2;
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
        public Escucha(){
            random = new Random();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fileManager.writer(nombre.getText()); //Cuando se haga (enter), se va a sacar el texto del JTextField y lo guarda en el archivo de juagdores
            nombre.setText("");
            if (e.getSource()==botonJugar){
                cambiarPalabra(); //ejecuta el metodo cambiarPalabra
            } else if (e.getSource()==botonSalir) {
                System.exit(0); //Sale del programa
            } else if (e.getSource()==botonAyuda){
                JOptionPane.showMessageDialog(null, Ayuda); //Muestra las ayudas
            }
        }

        private void cambiarPalabra() {
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
                    panelPalabras.pintarTexto(controlIKnowThatWorld.pintarPalabra()); //muestra una palabra alatoria
                    panelPalabras.setBackground(randomColor); //se cambia el color del fondo del panel de las palabras
                    botonJugar.setEnabled(false); //Se deshabilita el botonJugar
                    count++;  //aumenta el contador
                }
            }, 0, 5000);  //La palabras se muestran con un intervalo de 5 segundos
        }


        private Color getRandomColor() {  //Obtiene el un color alatorio

            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return new Color(r,g,b);
        }
    }
}