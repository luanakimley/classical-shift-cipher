public class ShiftCipher {
    // Fields
    private int key;
    private String encryptedText;
    private String decryptedText;

    // Constructors

    /**
     * ShiftCipher constructor for ShiftCipher with known key
     * @param key key for decryption
     * @param encryptedText text that needs to be decrypted
     */
    public ShiftCipher(int key, String encryptedText) {
        this.key = key;
        this.encryptedText = encryptedText;
    }


    /**
     * ShiftCipher constructor for ShiftCipher with unknown key
     * @param encryptedText text that needs to be decrypted
     */
    public ShiftCipher(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    // Getters & Setters
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    public String getDecryptedText() {
        return decryptedText;
    }

    public void setDecryptedText(String decryptedText) {
        this.decryptedText = decryptedText;
    }

    // Decrypt with known key
    public void decryptKnownKey() {
        StringBuilder result = new StringBuilder();
        this.encryptedText = this.encryptedText.toUpperCase();

        /*
         * use 26 - key instead of using negative keys so that it won't have a problem when looping back
         * use '% 26' so shift won't be negative
         */

        int shift = 26 - (this.key % 26);

        for (char c : this.encryptedText.toCharArray()) {
            if (c != ' ') { // to preserve space characters
                result.append((char) (((int) c + shift - 'A') % 26 + 'A'));
            }
            else { // if character is space, add without modifying anything
                result.append(c);
            }
        }

        this.decryptedText = result.toString();
    }

    // Decrypt with exhaustive key search
    public void decryptExhaustiveKeySearch(String word) {
        String result = "";
        int tempKey = 0;
        word = word.toUpperCase();
        for (int i=1; i <= 26; i++) {
            this.key = i;
            decryptKnownKey();
            String temp = this.decryptedText;
            if (temp.contains(word)) {
                tempKey = i;
                result = temp;
            }
        }
        this.key = tempKey;
        this.decryptedText = result;
    }


}
