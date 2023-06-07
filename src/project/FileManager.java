package project;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase nos ayuda a leer las palabras del diccionario y nos ayuda a guardar y leer los jugadores.
 *
 * @version v.1.0.0 date:28/05/2023
 * @autor Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
 * @autor Junior Cantor Arevalo junior.cantor@correounivalle.edu.co
 */
public class FileManager {
    public static final String PATH = "src/project/files/diccionario.txt";
    private FileReader fileReader, fileReaderJugadores;
    private BufferedReader input, inputJugadores;
    private FileWriter fileWriter;
    private BufferedWriter output;
    /**
     * Lee las palabras del diccionario.txt
     */
    public ArrayList<String> lecturaFile() {
        ArrayList<String> palabras = new ArrayList<String>(); //crea una coleccion de elementos que van a albergar como informacion un String y va a tener una referencia a otro elemnto del mismmo tipo

        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader); //Se captura el contenido del archivo
            String line = input.readLine();  //Lee cada linea y se almacena en line

            while (line != null) {  //Mientras siga habiendo mas datos que siga leyendo
                palabras.add(line); //Agrega las palabras del archivo a la lista
                line = input.readLine();  //lee la linea
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palabras;
    }
    /**
     * Lee el alias del jugador ingresado
     */
    public String reader(){
        String text = "";

        try {
            fileReader = new FileReader("src/project/files/jugadores.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            while(line != null){
                text += line;
                text += "\n";
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Estoy dentro de la excepcion");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                System.out.println("Estoy dentro del finally");
                e.printStackTrace();
            }
        }

        return text;
    }
    /**
     * Escribe el alias del jugador en jugadores.txt
     */
    public void writer(String line){
        try {
            String text = reader();
            text += line + "\n";
            fileWriter = new FileWriter("src/project/files/jugadores.txt");
            output = new BufferedWriter(fileWriter);
            output.write(text);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
