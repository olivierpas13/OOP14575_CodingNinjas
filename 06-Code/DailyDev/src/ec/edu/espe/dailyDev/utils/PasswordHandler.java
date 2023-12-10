package ec.edu.espe.dailyDev.utils;

/**
 *
 * @author CodingNinjas 
 */

public class PasswordHandler {

    // Función para encriptar la contraseña mediante codificación UTF-8
    public static String encryptPassword(String password, int shift) {
        try {
            // Convertir la contraseña a bytes
            byte[] bytes = password.getBytes("UTF-8");

            // Aplicar el desplazamiento a cada byte
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] += shift;
            }

            // Convertir los bytes de nuevo a String
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Función para desencriptar la contraseña mediante codificación UTF-8
    public static String decryptPassword(String encryptedPassword, int shift) {
        try {
            // Convertir la contraseña a bytes
            byte[] bytes = encryptedPassword.getBytes("UTF-8");

            // Aplicar la reversión del desplazamiento a cada byte
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] -= shift;
            }

            // Convertir los bytes de nuevo a String
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
