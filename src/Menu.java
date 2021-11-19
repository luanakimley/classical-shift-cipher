import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    public void menu() {
        displayMenu();
        try {
            int option = inputValidInt(1, 5, "Input option: ", "Invalid menu option");

            while (option != 5) {
                switch (option) {
                    case 1:
                        menuDecrypt();
                        break;
                    case 2:
                        menuDecryptExhaustiveKeySearch();
                        break;
                    case 3:
                        decryptYourOwnMessage();
                        break;
                    case 4:
                        decryptYourOwnMessageUsingExhaustiveKey();
                        break;
                }
                displayMenu();
                option = inputValidInt(1, 5, "Input option: ", "Invalid menu option");
            }
            System.out.println("Program ending - thank you");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!\n");
            displayMenu();
        }
    }

    public void displayMenu() {
        System.out.println("\n**** CLASSICAL SHIFT CIPHER MENU ****");
        System.out.println("** OPTIONS **");
        System.out.println("1. Decrypt message from text file\n" +
                "2. Decrypt message exhaustive key from text file\n"+
                "3. Decrypt your own message\n"+
                "4. Decrypt your own message using exhaustive key\n" +
                "5. Quit");
    }

    public void menuDecrypt() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n**** DECRYPT MESSAGE ****");
        System.out.println("Enter file name:");
        String fileName = keyboard.nextLine();
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            String ciphertext = input.nextLine();
            ShiftCipher shiftCipher = new ShiftCipher(3, ciphertext);

            System.out.println("Cipher text: " + ciphertext);
            shiftCipher.decryptKnownKey();
            System.out.println(shiftCipher.getDecryptedText());
            System.out.println();

        } catch (NoSuchElementException e) {
            System.out.println("Your input must include 2 lines of cipher text.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void menuDecryptExhaustiveKeySearch(){
        System.out.println("\n**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = keyboard.nextLine();
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            input.nextLine();
            String ciphertext = input.nextLine();
            ShiftCipher shiftCipher = new ShiftCipher(ciphertext);

            System.out.println("Cipher text: " + ciphertext);
            shiftCipher.decryptExhaustiveKeySearch("done");
            System.out.println("Key: " + shiftCipher.getKey());
            System.out.println(shiftCipher.getDecryptedText());
            System.out.println();

        } catch (NoSuchElementException e) {
            System.out.println("Your input must include 2 lines of cipher text.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void decryptYourOwnMessage(){
        System.out.println("\n**** DECRYPT YOUR OWN MESSAGE ****");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a message you wish to decrypt: ");
        String ciphertext = keyboard.nextLine();
        System.out.println("Enter key: ");
        int key = keyboard.nextInt();

        ShiftCipher shiftCipher = new ShiftCipher(key, ciphertext);

        System.out.println("Cipher text - your own: " + ciphertext);
        shiftCipher.decryptKnownKey();
        System.out.println(shiftCipher.getDecryptedText());
        System.out.println();
    }

    public void decryptYourOwnMessageUsingExhaustiveKey() {
        System.out.println("\n**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a message you wish to decrypt using exhaustive key: ");
        String ciphertext = keyboard.nextLine();
        System.out.println("Enter a word that is inside your message: ");
        String word = keyboard.nextLine();

        ShiftCipher shiftCipher = new ShiftCipher(ciphertext);
        System.out.println("Cipher text: " + ciphertext);
        shiftCipher.decryptExhaustiveKeySearch(word);
        System.out.println("Key: " + shiftCipher.getKey());
        System.out.println(shiftCipher.getDecryptedText());

    }

    /**
     * This method allows the user to input a number and checks if the number is
     * in the range
     *
     * @param min minimum limit of numbers in the range
     * @param max maximum limit of numbers in the range
     * @param prompt prompt message
     * @param errorMessage error message
     * @return number entered
     */
    public int inputValidInt ( int min, int max, String prompt, String errorMessage)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(prompt);
        int num = keyboard.nextInt();
        while (num < min || num > max) {
            System.out.println(errorMessage + " - must be in range" + "[" + min + "," + max + "]  ");
            System.out.print(prompt);
            num = keyboard.nextInt();
        }

        return num;
    }

}
