package org.example.fundamentos;

public class Plindromo {
       public static boolean esPalindromo(String texto) {
        String limpio = texto.toLowerCase().replace(" ", "");
        String invertido = new StringBuilder(limpio).reverse().toString();
        return limpio.equals(invertido);

    }
    
    public static void main(String[] args) {
         System.out.println("¿'reconocer' es palíndromo? " + esPalindromo("reconocer"));
    }
}