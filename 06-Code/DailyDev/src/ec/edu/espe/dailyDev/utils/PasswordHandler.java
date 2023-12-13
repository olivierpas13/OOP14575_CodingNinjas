package ec.edu.espe.dailyDev.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 *
 * @author CodingNinjas 
 */

public class PasswordHandler {

    private static final int SALT_LENGTH = 16;

    public static EncryptedPassword encryptPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            byte[] passwordWithSalt = new byte[password.length() + salt.length];
            System.arraycopy(password.getBytes(), 0, passwordWithSalt, 0, password.length());
            System.arraycopy(salt, 0, passwordWithSalt, password.length(), salt.length);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(passwordWithSalt);

            StringBuilder hexPassword = new StringBuilder();
            for (byte b : hashedPassword) {
                hexPassword.append(String.format("%02x", b));
            }

            return new EncryptedPassword(hexPassword.toString(), bytesToHexString(salt));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting password.");
        }
    }

        private static String bytesToHexString(byte[] bytes) {
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        }

        public static String decryptPassword(String encryptedPassword, String salt) {
            try {
                byte[] saltBytes = hexStringToBytes(salt);
                byte[] passwordWithSalt = hexStringToBytes(encryptedPassword);

                byte[] passwordBytes = new byte[passwordWithSalt.length - saltBytes.length];
                System.arraycopy(passwordWithSalt, 0, passwordBytes, 0, passwordBytes.length);

                return new String(passwordBytes);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error decrypting password.");
            }
        }

        private static byte[] hexStringToBytes(String hexString) {
            int len = hexString.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                        + Character.digit(hexString.charAt(i + 1), 16));
            }
            return data;
        }

    public static boolean verifyPassword(String password, String encryptedPassword, String salt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }

    class EncryptedPassword {
        private final String encryptedPassword;
        private final String salt;

        public EncryptedPassword(String encryptedPassword, String salt) {
            this.encryptedPassword = encryptedPassword;
            this.salt = salt;
        }

        public String getEncryptedPassword() {
            return encryptedPassword;
        }

        public String getSalt() {
            return salt;
        }
    }