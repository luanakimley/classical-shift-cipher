// Reference: https://www.baeldung.com/java-caesar-cipher
public class ShiftCipher {
    public String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        int shift = 26 - (key % 26);

        for (char c : text.toCharArray()) {
            if (c != ' ') {
                result.append((char) (((int) c + shift - 'A') % 26 + 'A'));
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public String decryptExhaustiveKeySearch(String message) {
        String result = "";

        for (int i=1; i <= 26; i++) {
            String temp = decrypt(message, i);
            if (temp.contains("DONE"))
                result = temp;
        }

        return result;
    }
}
