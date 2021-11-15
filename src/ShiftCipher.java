// Reference: https://www.baeldung.com/java-caesar-cipher
public class ShiftCipher {
    public String decipher(String message, int key) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();

        int shift = 26 - (key % 26);

        for (char c : message.toCharArray()) {
            if (c != ' ') {
                result.append((char) (((int) c + shift - 'A') % 26 + 'A'));
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public String decipherExhaustiveKeySearch(String message) {
        String result = "";
        String temp;

        for (int i=1; i <= 26; i++) {
            temp = decipher(message, i);
            if (temp.contains("DONE"))
                result = temp;
        }

        return result;
    }
}
