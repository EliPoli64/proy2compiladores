import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java_cup.runtime.Symbol;

public class Main {
    private static Map<Integer, String> symbolNames = new HashMap<>(); // mapeo de id de token a su nombre

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso incorrecto. Ejecutar: java Main <archivo_fuente>");
            System.exit(1);
        }

        String fileName = args[0];
        String outputFileName = "salida.txt";

        loadSymbolNames(); // carga los nombres de simbolos en mapa

        System.out.println("Iniciando análisis léxico...");
        System.out.println("Entrada: " + fileName);

        try (FileReader reader = new FileReader(fileName);
            PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {

            LexerProyUno lexer = new LexerProyUno(reader);
            writer.println("-- Tokens encontrados --");
            writer.printf("%-20s %-40s %-10s %-10s%n", "Token", "Lexema", "Linea", "Columna");
            writer.println("_________________________________________________________________________________");

            while (true) {
                Symbol token = lexer.next_token(); // itera por todos los tokens encontrados

                if (token.sym == sym.EOF) {
                    writer.println("EOF (Fin de archivo)");
                    break;
                }

                String tokenName = getSymbolName(token.sym);
                String lexeme = token.value != null ? token.value.toString() : "";
                    writer.printf("%-20s %-40s %-10d %-10d%n",
                            tokenName, lexeme, token.left + 1, token.right + 1);
            }

            System.out.println("Análisis completado exitosamente.");
            System.out.println("Tokens guardados en: " + outputFileName);

        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + fileName);
        } catch (Exception e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadSymbolNames() {
        try {
            Field[] fields = sym.class.getFields();
            for (Field field : fields) {
                if (field.getType() == int.class) {
                    int value = field.getInt(null);
                    String name = field.getName();
                    symbolNames.put(value, name);
                }
            }
        } catch (Exception e) {
            System.err.println("Advertencia: No se pudieron cargar los nombres de los símbolos de la clase 'sym'."); // por si falla
        }
    }

    private static String getSymbolName(int id) {
        return symbolNames.getOrDefault(id, "SYM_" + id); // por si no encuentra el nombre de un simbolo
    }
}
