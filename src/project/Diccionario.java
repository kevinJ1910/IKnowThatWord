package project;

import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase es usada para acceder a las palabras.
 * Obitene las palabras
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class Diccionario {
    private ArrayList<String> diccionario = new ArrayList<>();
    /**
     * Constructor de la clase
     */
    public Diccionario() {
        FileManager fileManager = new FileManager(); //Usa el fileManger que creamos
        diccionario = fileManager.lecturaFile();
    }

    public String obtenerPalabra() {
        Random aleatorio = new Random();

        return diccionario.get(aleatorio.nextInt(diccionario.size())); //Devuelve el String que está contenido en la position de la lista usando un random
    }
}
