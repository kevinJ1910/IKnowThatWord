package project;

public class ControlIKnowThatWorld {

    private Diccionario palabras;
    private String palabra, clave, errores;
    private int conteoErrores;
    private boolean fallo, ganar;

    /**
     * Constructor de la clase
     */
    public ControlIKnowThatWorld() {
        palabras = new Diccionario();
    }

    public String pintarPalabra() {
        //initialization de las variables de control de la ronda de juego
        clave = "";
        palabra = palabras.getPalabra(); //Obtiene/Saca aleatoriamente la palabra
        errores = "Palabras erroneas: ";
        conteoErrores = 0;



        //Crear la clave de la palabra
        for(int i=0; i<palabra.length(); i++) {  //Recorre la palabra obtenida
            if(palabra.charAt(i) == ' ') {
                clave += palabra.charAt(i);      // Si hay un espacio en blanco se agrega ese espacio en blanco
            }else {
                clave += palabra.charAt(i);     // Si no hay un espacio en blanco se agrega el caracter de la palabra
            }
        }
        return clave;           //Devuelve la clave de la palabra

    }

    public String validarPalabra(char letra) {
        fallo = false;
        int index = palabra.indexOf(letra);
        if (index != -1) {
            while (index > -1) {
                index = palabra.indexOf(index+1, letra);
            }
        }else {
            fallo = true;
            conteoErrores++;

        }
        return clave;
    }
/*
    public boolean esGanador() {
        if (clave.indexOf() == -1) {
            ganar = true;
        }else {
            ganar = false;
        }
        return ganar;
    }

 */

    public String getClave() {
        return clave;
    }

    public String getErrores() {
        return errores;
    }

    public int getConteoErrores() {
        return conteoErrores;
    }

    public boolean isFallo() {
        return fallo;
    }

    public String getPalabra() {
        return palabra;
    }
}
