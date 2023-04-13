import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manipulador {
    private final List<String> archivo;
    private final String regex;

    public Manipulador(List<String> archivo, String regex) {
        this.archivo = archivo;
        this.regex = regex;
    }

    public int countExactMatchesByLine() {
        int contador = 0;
        for (String s : archivo) {
            if (s.matches(regex)) {
                contador++;
            }
        }
        return contador;
    }

    public void printExactMatchesByLine() {
        System.out.print("Computa si se corresponde con la expresión regular, linea por línea.\n");
        if (this.countExactMatchesByLine() == 0) {
            System.out.println("No hay matches");
        } else {
            for (int i = 0; i < archivo.size(); i++) {
                if (archivo.get(i).matches(regex)) {
                    System.out.printf("La línea número %d ", i + 1);
                    System.out.print("se corresponde con la expresión regular.\n");
                }
            }
        }
    }

    public void printExactMatchesByLine(boolean printNonMatches) {
        System.out.print("Computa si se corresponde con la expresión regular, linea por línea.\n");
        if (this.countExactMatchesByLine() == archivo.size()) {
            System.out.println("Todas las lineas son matches");
        } else {
            for (int i = 0; i < archivo.size(); i++) {
                if (archivo.get(i).matches(regex) && !printNonMatches) {
                    System.out.printf("La línea número %d ", i + 1);
                    System.out.print("se corresponde con la expresión regular.\n");
                } else if (!archivo.get(i).matches(regex) && printNonMatches) {
                    System.out.printf("La línea número %d ", i + 1);
                    System.out.print("NO se corresponde con la expresión regular.\n");
                }
            }
        }
    }

    public int countMatches() {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String texto = String.join("\n", archivo);
        Matcher matcher = pattern.matcher(texto);
        int contador = 0;
        while (matcher.find()) {
            contador++;
        }
        return contador;
    }

    public void printMatches() {
        System.out.print("Colapsa el texto, e imprime los grupos por match.\n");
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String texto = String.join("\n", archivo);
        Matcher matcher = pattern.matcher(texto);

        if (!matcher.find()) System.out.println("\tNo matches.");
        matcher.reset();
        int i = 1;
        while (matcher.find()) {
            System.out.printf("Match number %d: ", i++);
            System.out.printf("%n\tFull match: %s%n", matcher.group(0));
        }
    }

    public void printMatches(boolean printGroups) {
        System.out.print("Colapsa el texto, e imprime los grupos por match.\n");
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String texto = String.join("\n", archivo);
        Matcher matcher = pattern.matcher(texto);

        if (!matcher.find()) System.out.println("\tNo matches.");
        matcher.reset();
        int i = 1;
        while (matcher.find()) {
            System.out.printf("Match number %d: ", i++);
            System.out.printf("%n\tFull match: %s%n", matcher.group(0));
            if (printGroups) {
                for (int j = 1; j <= matcher.groupCount(); j++) {
                    System.out.printf("\t\tGroup %d : %s%n", j, matcher.group(j));
                }
            }
        }
    }
}
