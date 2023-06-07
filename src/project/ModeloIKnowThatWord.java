package project;

import java.util.List;

/**
 * Esta clase implementa el modelo para la lógica del juego.
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class ModeloIKnowThatWord {
    private Diccionario palabras, jugadores;
    private String palabra, errores;
    private int conteoErrores;
    private boolean fallo, ganar;
    /**
     * Constructor de la clase
     */
    public ModeloIKnowThatWord() {
        palabras = new Diccionario();
    }

    /**
     * Obtiene la palabra del diccionario
     */
    public String pintarPalabra() {
        //initialization de las variables de control de la ronda de juego
        //clave = "";
        palabra = palabras.obtenerPalabra(); //Obtiene/Saca aleatoriamente la palabra por medio del metodo obtenerPalabra de la clase Diccionario
        errores = "Palabras erroneas: ";
        conteoErrores = 0;

        return palabra;
    }
    /**
     * Define si la palabra se mostro anteriormente
     */
    public String validarPalabra(String frase) {
        fallo = false;
        int index = this.palabra.indexOf(frase); //recibe la frase y busca la primera incidencia que encuentra de esa palabra en la cadena "palabra" y me devuelve el indice de esa primera incidencia

        if (index != -1) {
            index = this.palabra.indexOf(frase);
        } else {
            fallo = true;
            conteoErrores++;
            errores += palabra + "";

        }
        return palabra;
    }

    /**
     * Determina si el jugador gano el nivel
     */
    public boolean esGanador() {
        if (palabra.indexOf(" ") == -1) {
            ganar = true;
        } else {
            ganar = false;
        }
        return ganar;
        /*
        if (clave.indexOf() == -1) {
            ganar = true;
        }else {
            ganar = false;
        }
        return ganar;
         */
    }
    /**
     * Obtiene errores
     */
    public String getErrores() {
        return errores;
    }
    /**
     * Contea errores
     */
    public int getConteoErrores() {
        return conteoErrores;
    }
    /**
     * Determina si fallo o no
     */
    public boolean isFallo() {
        return fallo;
    }

}
