import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// archivo archivo.txt, regex: [a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*
// archivo shakespeare.txt, regex: (?<before>(?>\w+)[^\w]?\s(?>\w+)[^\w]?\s(?>\w+)[^\w]?)\sbreath[^\w]?\s(?<after>(?>\w+)[^\w]?\s(?>\w+)[^\w]?\s(?>\w+))

public class Main {
    public static void main(String[] args) {
        List<String> archivo = null;
        String regex = null;
        try {
            archivo = FileReader(args[0]);
            regex = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR! Argumentos incorrectos.");
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("ERROR! Archivo no encontrado.");
            System.exit(1);
        }
        Manipulador mani = new Manipulador(archivo, regex);

        System.out.println("Exact matches by line = " + mani.countExactMatchesByLine());
        System.out.println();
        mani.printExactMatchesByLine();
        System.out.println();
        // mani.printExactMatchesByLine(true);
        System.out.println();
        System.out.println("Matches = " + mani.countMatches());
        System.out.println();
        mani.printMatches(true);
    }

    public static List<String> FileReader(String archivo) throws IOException {
        return Files.readAllLines(Paths.get("src/main/resources/" + archivo));
    }
}
