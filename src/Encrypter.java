public class Encrypter {
    String key;

    public Encrypter(String key){
        if (key.length() != 26){
            throw new IllegalArgumentException("Key must be of length 26");
        }
        this.key = key.toLowerCase();
    }

    public String encrypt(String text){
        text = text.toLowerCase();

        String encrypted = "";
        int currentShift = 0;

        int i = 0;

        for(char currentChar : text.toCharArray()){
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

            encrypted += currentEncryptedChar;
        }

        return encrypted;
    }
}
