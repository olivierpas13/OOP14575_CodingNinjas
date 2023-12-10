package ec.edu.espe.dailyDev.utils;

/**
 *
 * @author CodingNinjas 
 */
public class PasswordHandler {
    
    // Función para encriptar la contraseña sumando un número a cada carácter según la tabla ASCII
    public static String encryptPassword(String password, int shift) {
        StringBuilder encryptedPassword = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);
            // Sumar el número proporcionado al valor ASCII de cada carácter
            char encryptedChar = (char) (currentChar + shift);
            encryptedPassword.append(encryptedChar);
        }

        return encryptedPassword.toString();
    }

    // Función para desencriptar la contraseña restando un número a cada carácter según la tabla ASCII
    public static String decryptPassword(String encryptedPassword, int shift) {
        StringBuilder decryptedPassword = new StringBuilder();

        for (int i = 0; i < encryptedPassword.length(); i++) {
            char currentChar = encryptedPassword.charAt(i);
            // Restar el número proporcionado al valor ASCII de cada carácter
            char decryptedChar = (char) (currentChar - shift);
            decryptedPassword.append(decryptedChar);
        }

        return decryptedPassword.toString();
    }
}
