import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();

    }

    public void start() throws FileNotFoundException {

        menu();

    }

    public static void menu() throws FileNotFoundException {

        System.out.println("**** MENU ****");
        System.out.println("** OPTIONS **");
        System.out.println("1. decrypt message\n" +
                            "2. decrypt message exhaustive key\n"+
                            "3. decrpyt your own message\n"+
                            "4. decrpyt your own message using exhaustive key\n");

        int option = inputValidInt(1, 4, "Input option: ", "Invalid menu option");

        while (option != 5) {
            switch (option) {
                case 1:
                    menuDecrypt();
                case 2:
                    menuDecryptExhaustiveKeySearch();
                case 3:
                    decryptYourOwnMessage();
                case 4:
                    decryptYourOwnMessageUsingExhaustiveKey();

            }
        }
    }
        public static void menuDecrypt () throws FileNotFoundException {
            System.out.println("**** DECRYPT MESSAGE ****");
            try {
                File file = new File("ciphertext.txt");
                Scanner input = new Scanner(file);

                ShiftCipher shiftCipher = new ShiftCipher();

                String ciphertext1 = input.nextLine();
                System.out.println("Cipher text 1: " + ciphertext1);
                System.out.println(shiftCipher.decrypt(ciphertext1, 3));

            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            }
            menu();


        }
        public static void menuDecryptExhaustiveKeySearch () throws FileNotFoundException {
            System.out.println("**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");

            try {
                File file = new File("ciphertext.txt");
                Scanner input = new Scanner(file);

                ShiftCipher shiftCipher = new ShiftCipher();

                String ciphertext2 = input.nextLine();
                System.out.println("Cipher text 2: " + ciphertext2);
                System.out.println(shiftCipher.decryptExhaustiveKeySearch(ciphertext2));
            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            }
            menu();

        }
        public static void decryptYourOwnMessage() throws FileNotFoundException {
            System.out.println("**** DECRYPT YOUR OWN MESSAGE ****");
            try {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter a message you wish to decrypt: ");
                String ciphertext3 = keyboard.nextLine();


                ShiftCipher shiftCipher = new ShiftCipher();


                System.out.println("Cipher text 3-your own: " + ciphertext3);
                System.out.println(shiftCipher.decrypt(ciphertext3, 3));
            } catch (NoSuchElementException e) {
                System.out.println("Your input must include 2 lines of cipher text.");
            }
            menu();

    }
    public static void decryptYourOwnMessageUsingExhaustiveKey() throws FileNotFoundException {
        System.out.println("**** DECRYPT EXHAUSTIVE KEY SEARCH MESSAGE ****");

        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter a message you wish to decrpt using exhaustive key: ");
            String ciphertext4 = keyboard.nextLine();

            ShiftCipher shiftCipher = new ShiftCipher();
            System.out.println("Cipher text 4: " + ciphertext4);
            System.out.println(shiftCipher.decryptExhaustiveKeySearch(ciphertext4));
        } catch (NoSuchElementException e) {
            System.out.println("Your input must include 2 lines of cipher text.");
        }
        menu();
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

