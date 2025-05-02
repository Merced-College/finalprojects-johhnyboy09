import java.util.*;

public class GuessMasterPro {
    // Main class for the game
    private String hiddenWord;
    private Set<Character> guessedLetters = new HashSet<>();
    private int remainingGuesses = 6;
    private Stack<Character> guessHistory = new Stack<>();

    public GuessMasterPro(String word) {
        this.hiddenWord = word.toLowerCase();
    }

    // Displays the current word with guessed letters shown and others as '_'
    public String getCurrentGuessState() {
        StringBuilder display = new StringBuilder();
        for (char c : hiddenWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(c).append(' ');
            } else {
                display.append('_').append(' ');
            }
        }
        return display.toString().trim();
    }

    // Process a letter guess from the user
    public boolean makeGuess(char letter) {
        letter = Character.toLowerCase(letter);
        if (!Character.isLetter(letter) || guessedLetters.contains(letter)) {
            return false; // invalid or repeated guess
        }
        guessedLetters.add(letter);
        guessHistory.push(letter);
        if (!hiddenWord.contains(String.valueOf(letter))) {
            remainingGuesses--;
        }
        return true;
    }

    // Check if the player has won
    public boolean hasWon() {
        for (char c : hiddenWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Comment 1: Constructor initializes game with a given hidden word
    // Comment 2: getCurrentGuessState shows the guessed word progress with underscores
    // Comment 3: makeGuess handles letter checking and updates guess history
    // Comment 4: hasWon checks if all letters in the word have been guessed
    // Comment 5: guessHistory stack allows implementing an undo feature later
}
