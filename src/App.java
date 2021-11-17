import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();

    }

    public void start() {
        menu();
    }

    public static void menu() {
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

        public static void displayMenu() {
            System.out.println("**** MENU ****");
            System.out.println("** OPTIONS **");
            System.out.println("1. Decrypt message\n" +
                    "2. Decrypt message exhaustive key\n"+
                    "3. Decrypt your own message\n"+
                    "4. Decrypt your own message using exhaustive key\n" +
                    "5. Exit");
        }

        public static void menuDecrypt() {
            System.out.println("**** DECRYPT MESSAGE ****");
            String fileName = "ciphertext.txt";
            try {
                File file = new File(fileName);
                Scanner input = new Scanner(file);

                String ciphertext1 = input.nextLine();
                ShiftCipher shiftCipher = new ShiftCipher(3, ciphertext1);

                System.out.println("Cipher text 1: " + ciphertext1);
                shiftCipher.decryptKnownKey();
                System.out.println(shiftCipher.getDecryptedText());

            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
        public static void menuDecryptExhaustiveKeySearch(){
            System.out.println("**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");
            String fileName = "ciphertext.txt";
            try {
                File file = new File(fileName);
                Scanner input = new Scanner(file);

                input.nextLine();
                String ciphertext2 = input.nextLine();
                ShiftCipher shiftCipher = new ShiftCipher(ciphertext2);

                System.out.println("Cipher text 2: " + ciphertext2);
                shiftCipher.decryptExhaustiveKeySearch("done");
                System.out.println("Key: " + shiftCipher.getKey());
                System.out.println(shiftCipher.getDecryptedText());
            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
        public static void decryptYourOwnMessage(){
            System.out.println("**** DECRYPT YOUR OWN MESSAGE ****");
            try {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter a message you wish to decrypt: ");
                String ciphertext3 = keyboard.nextLine();

                ShiftCipher shiftCipher = new ShiftCipher(ciphertext3);

                System.out.println("Cipher text 3-your own: " + ciphertext3);
                shiftCipher.decryptKnownKey();
                System.out.println(shiftCipher.getDecryptedText());
            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            }
    }

    public static void decryptYourOwnMessageUsingExhaustiveKey() {
        System.out.println("**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");

        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter a message you wish to decrypt using exhaustive key: ");
            String ciphertext4 = keyboard.nextLine();

            ShiftCipher shiftCipher = new ShiftCipher(ciphertext4);
            System.out.println("Cipher text 4: " + ciphertext4);
            shiftCipher.decryptExhaustiveKeySearch("");
            System.out.println(shiftCipher.getDecryptedText());
        } catch (NoSuchElementException e) {
            System.out.println("Your input must include 2 lines of cipher text.");
        }
    }

        public static int inputValidInt ( int min, int max, String prompt, String errorMessage)
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

