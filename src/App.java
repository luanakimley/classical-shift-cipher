import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();
    }

    public void start() throws IOException {
        try {
            File file = new File("ciphertext.txt");
            Scanner input = new Scanner(file);

            String ciphertext1 = input.nextLine();
            String ciphertext2 = input.nextLine();

            ShiftCipher shiftCipher1 = new ShiftCipher(3, ciphertext1);

            System.out.println("Cipher text 1: " + shiftCipher1.getEncryptedText());
            shiftCipher1.decryptKnownKey();
            System.out.println(shiftCipher1.getDecryptedText());

            ShiftCipher shiftCipher2 = new ShiftCipher(ciphertext2);

            System.out.println("Cipher text 2: " + shiftCipher2.getEncryptedText());
            shiftCipher2.decryptExhaustiveKeySearch("done");
            System.out.println("Discovered key: " + shiftCipher2.getKey());
            System.out.println(shiftCipher2.getDecryptedText());
        }
        catch (NoSuchElementException e) {
            System.out.println("Your input must include 2 lines of cipher text.");
        }

    }




}
