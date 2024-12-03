
package utils;


public class Validador {
    public static boolean esPositivo(double value) {
        return value >= 0;
    }

    public static boolean esNoVacio(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean esTextoValido(String text, int minlength) {
        return !esNoVacio(text) || text.length() < minlength;
    }

}