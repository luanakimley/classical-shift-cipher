public class ShiftCipher {
    private int key;
    private String encryptedText;
    private String decryptedText;

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

    public ShiftCipher(int key, String encryptedText) {
        this.key = key;
        this.encryptedText = encryptedText;
    }

    public ShiftCipher(String encryptedText) {
        this.encryptedText = encryptedText;
    }




    public void decryptKnownKey() {
        StringBuilder result = new StringBuilder();
        this.encryptedText = this.encryptedText.toUpperCase();

        int shift = 26 - (this.key % 26);

        for (char c : this.encryptedText.toCharArray()) {
            if (c != ' ') {
                result.append((char) (((int) c + shift - 'A') % 26 + 'A'));
            }
            else {
                result.append(c);
            }
        }

        this.decryptedText = result.toString();

    }

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
