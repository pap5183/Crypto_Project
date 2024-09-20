public class Test {
    public static void main(String[] args) {
        Encrypter encrypter = new Encrypter("hilwmkbdpcvazusjgrynqxofte");

        encrypter.encrypt("src/plaintext.txt", "src/ciphertext.txt");
    }
}
