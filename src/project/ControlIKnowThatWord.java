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
        palabra = palabras.obtenerPalabra(); //Obtiene/Saca aleatoriamente la palabra por medio del metodo obtenerPalabra de la clase Diccionario
        errores = "Palabras erroneas: ";
        conteoErrores = 0;

        return palabra;
    }

    public String validarPalabra(String frase) {
        fallo = false;
        int index = palabra.indexOf(frase); //recibe la frase y busca la primera incidencia que encuentra de esa palabra en la cadena "palabra" y me devuelve el indice de esa primera incidencia

        if (index != -1) {
            index = palabra.indexOf(frase);
        }else {
            fallo = true;
            conteoErrores++;
            errores += frase + "";

        }
        return palabra;
    }

    public boolean esGanador() {
        if (palabra.indexOf(" ") == -1) {
            ganar = true;
        }else{
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
