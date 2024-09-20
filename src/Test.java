public class Test {
    public static void main(String[] args) {
        Encrypter encrypter = new Encrypter("hilwmkbdpcvazusjgrynqxofte");

        encrypter.encryptFile("src/plaintext.txt", "src/ciphertext.txt");
    }
}
