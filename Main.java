import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//------------------------------------------
// Summary: Main class to run the spell checker program
//------------------------------------------
public class Main {
    //------------------------------------------
    // Summary: Main method that initializes the spell checker and processes user input
    // Args: args - Command line arguments
    //------------------------------------------
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <dictionary file>");
            System.exit(1);
        }

        SpellCheck spellCheck = new SpellCheck(args[0]);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String word;
            while (true) {
                System.out.print("Enter a word (or 'exit' to quit): ");
                word = reader.readLine();
                if (word.equalsIgnoreCase("exit")) {
                    break;
                }
                if (spellCheck.isCorrectWord(word)) {
                    System.out.println("Correct word");
                } else {
                    System.out.println("Misspelled?");
                    System.out.print("Suggestions: ");
                    Iterable<String> suggestions = spellCheck.suggestAlternatives(word);
                    for (String suggestion : suggestions) {
                        System.out.print(suggestion + " ");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
