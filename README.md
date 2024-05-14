# Spell Checker

This project is a simple spell checker implemented in Java using a Ternary Search Tree (TST) for efficient storage and retrieval of dictionary words. The program reads a dictionary file and allows the user to input words to check their spelling. If the word is misspelled, it suggests alternatives based on the given prefix.

## Features

- **Dictionary Loading**: Loads words from a specified dictionary file.
- **Spell Checking**: Checks if a given word is correctly spelled.
- **Suggestions**: Suggests alternative words based on the given prefix.

## Usage

To run the spell checker program, you need to provide a dictionary file as a command line argument.

### Running the Program

1. **Compile the program**:
   ```bash
   javac Main.java SpellCheck.java TST.java
   ```

2. **Run the program**:
   ```bash
   java Main <dictionary file>
   ```

3. **Usage example**:
   ```bash
   java Main dictionary.txt
   ```

### Interacting with the Program

Once the program is running, you can enter words to check their spelling. If a word is misspelled, the program will suggest alternative words based on the given prefix.

- **To check a word**: Enter the word and press Enter.
- **To exit the program**: Type `exit` and press Enter.

## Example

```
Enter a word (or 'exit' to quit): example
Correct word
Enter a word (or 'exit' to quit): exmple
Misspelled?
Suggestions: example 
Enter a word (or 'exit' to quit): exit
```

## Classes and Methods

### Main Class

- **main(String[] args)**: Initializes the spell checker and processes user input.

### SpellCheck Class

- **SpellCheck(String dictionaryFile)**: Constructor that initializes the dictionary.
- **void loadDictionary(String fileName)**: Loads words from the dictionary file into the TST.
- **boolean isCorrectWord(String word)**: Checks if a word is correctly spelled.
- **Iterable<String> suggestAlternatives(String prefix)**: Suggests alternative words based on the given prefix.

### TST Class

- **void put(String key, Value val)**: Inserts a key-value pair into the TST.
- **Iterable<String> keysWithPrefix(String prefix)**: Retrieves all keys with the given prefix.
- **boolean contains(String key)**: Checks if the TST contains the given key.

## Dictionary File

The dictionary file should be a plain text file with one word per line. For example:

```
apple
banana
orange
grape
```
