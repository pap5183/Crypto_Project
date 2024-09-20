import java.io.*;

public class Decrypter {
    String key;

    public Decrypter(String key){
        if (key.length() != 26){
            throw new IllegalArgumentException("Key must be of length 26");
        }
        this.key = key.toLowerCase();
    }

    public void decryptFile(String ciphertextFilePath, String outputFilePath){
        BufferedReader ciphertextReader = null;

        try {
            ciphertextReader = new BufferedReader(new FileReader(ciphertextFilePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + ciphertextFilePath);
            return;
        }

        String ciphertext = "";
        String line;

        try {
            while ((line = ciphertextReader.readLine()) != null) {
                ciphertext += line + "\n";
            }
            ciphertextReader.close();
        }
        catch (IOException e) {
            System.err.println("Error reading file: " + ciphertextFilePath);
        }

        String plaintext = decryptText(ciphertext);

        try {
            BufferedWriter plaintextWriter = new BufferedWriter(new FileWriter(outputFilePath));
            plaintextWriter.write(plaintext);
            plaintextWriter.close();
        }
        catch (IOException e) {
            System.err.println("Error writing file: " + outputFilePath);
        }
    }


    public String decryptText(String ciphertext){
        ciphertext = ciphertext.toLowerCase();

        String plaintext = "";
        int currentShift = 0;

        int i = 0;

        for (char currentCipherChar : ciphertext.toCharArray()){
            char currentPlainChar;

            if (currentCipherChar < 'a' || currentCipherChar > 'z'){
                currentPlainChar = currentCipherChar;
            }
            else if (i % 6 == 0){
                currentPlainChar = (char) ('a' + this.key.indexOf(currentCipherChar));
                currentShift = (26 + currentCipherChar - currentPlainChar + 1) % 26;
                i++;
            }
            else{
                currentPlainChar = (char) (((currentCipherChar - 'a' + 26 - currentShift) % 26) + 'a');
                i++;
            }

            plaintext += currentPlainChar;
        }

        return plaintext;
    }
}
