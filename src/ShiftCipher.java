public class ShiftCipher {
    // Fields
    private int key;
    private String cipherText;
    private String decryptedText;

    // Constructors

    /**
     * ShiftCipher constructor for ShiftCipher with known key
     * @param key key for decryption (must be a positive value)
     * @param cipherText text that needs to be decrypted
     */
    public ShiftCipher(int key, String cipherText) {
        if (key > 0)
            this.key = key;
        this.cipherText = cipherText;
    }

    /**
     * ShiftCipher constructor for ShiftCipher with unknown key
     * @param cipherText text that needs to be decrypted
     */
    public ShiftCipher(String cipherText) {
        this.cipherText = cipherText;
    }

    // Getters & Setters

    /**
     * Returns the value of ShiftCipher key
     * @return ShiftCipher key
     */
    public int getKey() {
        return key;
    }


    /**
     * Changes ShiftCipher key to key
     * @param key new key for ShiftCipher
     */
    public void setKey(int key) {
        this.key = key;
    }


    /**
     * Returns cipher text
     * @return cipher text
     */
    public String getCipherText() {
        return cipherText;
    }

    /**
     * Changes cipher text to cipherText
     * @param cipherText new cipher text
     */
    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    /**
     * Returns decrypted text
     * @return decrypted text
     */
    public String getDecryptedText() {
        return decryptedText;
    }

    public void setDecryptedText(String decryptedText) {
        this.decryptedText = decryptedText;
    }

    /**
     * Decrypt cipher text with known key
     */
    public void decryptKnownKey() {
            StringBuilder result = new StringBuilder();
            this.cipherText = this.cipherText.toUpperCase();

            /*
             * use 26 - key instead of using negative keys so that it won't have a problem when looping back
             * decrypting is the same as encrypting text using negative key
             * encrypting text with shift of 3 is the same as decrypting text with key of 26 - 3 = 23
             * used (this.key % 26) so if user enter number > 26 shift will not be negative
             */

            int shift = 26 - (this.key % 26);

            for (char c : this.cipherText.toCharArray()) { // Loops through text by converting each letter to an Array
                if (c != ' ') { // to preserve space characters by only encrypting non-space characters
                    result.append((char) (((int) c + shift - 'A') % 26 + 'A')); // Modular arithmetic algorithm to encrypt text, uses % 26 to loop back to 'A'
                } else { // if character is space, add without encrypting anything
                    result.append(c);
                }
            }

            this.decryptedText = result.toString();
    }


    /**
     * Decrypt cipher text using exhaustive search (unknown key)
     * @param word Word that is in the decrypted cipher text
     */
    public void decryptExhaustiveKeySearch(String word) {
        String result = "";
        int tempKey = 0;
        word = word.toUpperCase();

        for (int i=1; i <= 26; i++) { // Loops through all possible keys
            this.key = i;
            decryptKnownKey(); // decrypt with every key
            String temp = this.decryptedText;
            if (temp.contains(word)) { // if the text contains word it is the answer, set discovered key and decrypted text
                tempKey = i;
                result = temp;
            }
        }
        this.key = tempKey;
        this.decryptedText = result;
    }


}
