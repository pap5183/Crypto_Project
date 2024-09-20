import java.io.*;

public class Encrypter {
    String key;

    public Encrypter(String key){
        if (key.length() != 26){
            throw new IllegalArgumentException("Key must be of length 26");
        }
        this.key = key.toLowerCase();
    }

    public void encryptFile(String plaintextFilePath, String outputFilePath){

        BufferedReader plaintextReader = null;

        try {
            plaintextReader = new BufferedReader(new FileReader(plaintextFilePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + plaintextFilePath);
            return;
        }

        String plaintext = "";
        String line;

        try {
            while ((line = plaintextReader.readLine()) != null) {
                plaintext += line + "\n";
            }
            plaintextReader.close();
        }
        catch (IOException e) {
            System.err.println("Error reading file: " + plaintextFilePath);
        }

        String ciphertext = encryptText(plaintext);

        try {
            BufferedWriter ciphertextWriter = new BufferedWriter(new FileWriter(outputFilePath));
            ciphertextWriter.write(ciphertext);
            ciphertextWriter.close();
        }
        catch (IOException e) {
            System.err.println("Error writing file: " + outputFilePath);
        }
    }

    private String encryptText(String plaintext) {
        plaintext = plaintext.toLowerCase();

        String ciphertext = "";
        int currentShift = 0;

        int i = 0;

        for(char currentChar : plaintext.toCharArray()){
            char currentEncryptedChar;

            if (currentChar < 'a' || currentChar > 'z'){
                currentEncryptedChar = currentChar;
            }
            else if (i % 6 == 0){
                currentEncryptedChar = key.charAt(currentChar - 'a');

                currentShift = (26 + currentEncryptedChar - currentChar + 1) % 26;
                i++;
            }
            else {
                currentEncryptedChar = (char) (((currentChar - 'a' + currentShift) % 26) + 'a');
                i++;
            }

            ciphertext += currentEncryptedChar;
        }
        return ciphertext;
    }
}
