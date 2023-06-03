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
        ArrayList<String> palabras = new ArrayList<String>();

        //String texto = "";

        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader); //Se captura el contenido del archivo
            String line = input.readLine();

            while (line != null) {
                palabras.add(line);
                //texto += line;
                //texto += "\n";
                line = input.readLine();
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
