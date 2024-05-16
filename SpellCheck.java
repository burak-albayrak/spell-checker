import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//------------------------------------------
// Summary: Class for spell checking functionality
//------------------------------------------
public class SpellCheck {
    private TST<Boolean> dictionary;

    //------------------------------------------
    // Summary: Constructor that initializes the dictionary
    //------------------------------------------
    public SpellCheck(String dictionaryFile) {
        dictionary = new TST<>();
        loadDictionary(dictionaryFile);
    }

    //------------------------------------------
    // Summary: Loads words from the dictionary file into the TST
    //------------------------------------------
    private void loadDictionary(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.put(line.toLowerCase(), true);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    //------------------------------------------
    // Summary: Checks if a word is correctly spelled
    //------------------------------------------
    public boolean isCorrectWord(String word) {
        return dictionary.contains(word.toLowerCase());
    }

    //------------------------------------------
    // Summary: Suggests alternative words based on the given prefix
    //------------------------------------------
    public Iterable<String> suggestAlternatives(String prefix) {
        return dictionary.keysWithPrefix(prefix.toLowerCase());
    }
}
