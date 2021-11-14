// Reference: https://www.baeldung.com/java-caesar-cipher
public class ShiftCipher {
    public String decipher(String message, int key) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();

        int shiftCount = 26 - key;

        for (char c : message.toCharArray()) {
            if (c != ' ') {
                result.append((char) (((int) c + shiftCount - 'A') % 26 + 'A'));
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
