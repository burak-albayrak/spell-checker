import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//------------------------------------------
// Summary: Main class to run the spell checker program
//------------------------------------------
public class Main {
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
                    boolean foundSuggestions = suggestions.iterator().hasNext();

                    // If can't find any suggestion, check the prev character.
                    while (!foundSuggestions && word.length() > 0) {
                        word = word.substring(0, word.length() - 1);
                        suggestions = spellCheck.suggestAlternatives(word);
                        foundSuggestions = suggestions.iterator().hasNext();
                    }

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
