import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            File file = new File("ciphertext.txt");
            Scanner input = new Scanner(file);

            String ciphertext1 = input.nextLine();
            String ciphertext2 = input.nextLine();

            System.out.println("Cipher text 1: " + ciphertext1);
            System.out.println("Cipher text 2: " + ciphertext2);
        }
        catch (NoSuchElementException e) {
            System.out.println("That is not a cipher text.");
        }
    }

}
