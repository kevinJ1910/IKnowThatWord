package project;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static final String PATH = "src/project/files/diccionario.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

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

    public void escribirTexto(String linea) {
        try {
            fileWriter = new FileWriter(PATH, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
