import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    public void mainMenu() {
        displayMainMenu();
        try {
            int option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");

            while (option != 3) {
                switch (option) {
                    case 1:
                        menuDecryptFromFile();
                        break;
                    case 2:
                        menuDecryptOwnMessage();
                        break;
                }
                displayMainMenu();
                option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");
            }
            System.out.println("Program ending - thank you");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!\n");
            mainMenu();
        }
    }

    public void displayMainMenu() {
        System.out.println("\n**** CLASSICAL SHIFT CIPHER MENU ****");
        System.out.println("** OPTIONS **");
        System.out.println("1. Decrypt message from text file\n" +
                "2. Decrypt your own message\n" +
                "3. Quit");
    }

    public void displayDecryptFromFileMenu() {
        System.out.println();
        System.out.println("**** DECRYPT MESSAGE FROM TEXT FILE ****");
        System.out.println("Text file must contain two lines of cipher text");
        System.out.println("Line 1 of text file will contain cipher text that needs to be decrypted using a known key");
        System.out.println("Line 2 of text file will contain cipher text that needs to be decrypted using exhaustive search");
    }

    public void displayDecryptFromFileMenuOptions() {
        System.out.println();
        System.out.println("**** DECRYPT MESSAGE FROM TEXT FILE ****");
        System.out.println("** OPTIONS **");
        System.out.println("1. Decrypt using known key");
        System.out.println("2. Decrypt using exhaustive search");
        System.out.println("3. Back to main menu");
    }

    public void displayDecryptOwnMessageMenu() {
        System.out.println();
        System.out.println("**** DECRYPT YOUR OWN MESSAGE ****");
        System.out.println("** OPTIONS **");
        System.out.println("1. Decrypt using known key");
        System.out.println("2. Decrypt using exhaustive search");
        System.out.println("3. Back to main menu");
    }


    public void menuDecryptFromFile() {
        displayDecryptFromFileMenu();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = keyboard.nextLine();
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            String ciphertext1 = input.nextLine();
            String ciphertext2 = input.nextLine();

            menuDecryptFromFileOptions(ciphertext1, ciphertext2);
        } catch (NoSuchElementException e) {
            System.out.println("Your file must include 2 lines of cipher text.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            mainMenu();
        }
    }

    public void menuDecryptFromFileOptions(String ct1, String ct2) {
        displayDecryptFromFileMenuOptions();

        try {
            Scanner keyboard = new Scanner(System.in);
            int option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");

            while (option != 3) {
                switch (option) {
                    case 1:
                        System.out.println("Enter key: ");
                        int key = keyboard.nextInt();
                        ShiftCipher sc1 = new ShiftCipher(key, ct1);
                        sc1.decryptKnownKey();
                        System.out.println("Cipher text: " + sc1.getCipherText());
                        System.out.println("Decrypted text: " + sc1.getDecryptedText());
                        break;
                    case 2:
                        ShiftCipher sc2 = new ShiftCipher(ct2);
                        System.out.println("Enter a word that the decrypted cipher text will contain: ");
                        String word = keyboard.next();
                        sc2.decryptExhaustiveKeySearch(word);
                        System.out.println("Cipher text: " + sc2.getCipherText());
                        System.out.println("Discovered key: " + sc2.getKey());
                        System.out.println("Decrypted text: " + sc2.getDecryptedText());
                        break;
                }
                displayDecryptFromFileMenuOptions();
                option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");
            }
            System.out.println("Going back to main menu...");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
            menuDecryptFromFileOptions(ct1, ct2);
        }
    }

    public void menuDecryptOwnMessage() {
        displayDecryptOwnMessageMenu();

        try {
            Scanner keyboard = new Scanner(System.in);
            int option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");

            while (option != 3) {
                switch (option) {
                    case 1:
                        System.out.println("Enter cipher text:");
                        String ciphertext1 = keyboard.nextLine();
                        System.out.println("Enter key:");
                        int key = keyboard.nextInt();
                        ShiftCipher sc1 = new ShiftCipher(key, ciphertext1);
                        sc1.decryptKnownKey();
                        System.out.println("Cipher text: " + sc1.getCipherText());
                        System.out.println("Decrypted text: " + sc1.getDecryptedText());
                        break;
                    case 2:
                        System.out.println("Enter cipher text:");
                        String ciphertext2 = keyboard.nextLine();
                        ShiftCipher sc2 = new ShiftCipher(ciphertext2);
                        System.out.println("Enter word that the decrypted cipher text will contain:");
                        String word = keyboard.nextLine();
                        sc2.decryptExhaustiveKeySearch(word);
                        System.out.println("Cipher text: " + sc2.getCipherText());
                        System.out.println("Discovered key: " + sc2.getKey());
                        System.out.println("Decrypted text: " + sc2.getDecryptedText());
                        break;
                }
                displayDecryptOwnMessageMenu();
                option = inputValidInt(1, 3, "Input option: ", "Invalid menu option");
            }
            System.out.println("Going back to main menu...");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
            menuDecryptOwnMessage();
        }
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
