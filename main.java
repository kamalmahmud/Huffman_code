import java.io.IOException;


public class main {

    public static void main(String[] args) throws IOException {

        HuffmanCode s = new HuffmanCode("letter.txt");
        String source = "source.txt";
        String encodedFile = "encoded.txt";
        String decodedFile = "decoded.txt";

        s.enCoded(source,encodedFile);
        s.deCoded(encodedFile,decodedFile);

        s.display();
//



    }

}
