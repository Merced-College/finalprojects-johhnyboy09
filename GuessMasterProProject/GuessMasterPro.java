import java.util.*;

public class GuessMasterPro {
    private String hiddenWord;
    private Set<Character> guessedLetters = new HashSet<>();
    private int remainingGuesses = 6;
    private Stack<Character> guessHistory = new Stack<>();
    private int score = 0; // Score system added

    public GuessMasterPro(String word) {
        this.hiddenWord = word.toLowerCase();
    }

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

    public boolean makeGuess(char letter) {
        letter = Character.toLowerCase(letter);
        if (!Character.isLetter(letter) || guessedLetters.contains(letter)) {
            return false; // invalid or repeated guess
        }
        guessedLetters.add(letter);
        guessHistory.push(letter);

        if (hiddenWord.contains(String.valueOf(letter))) {
            score += 10; // Add points for a correct guess
        } else {
            remainingGuesses--;
            score -= 5; // Subtract points for a wrong guess
        }
        return true;
    }

    public boolean hasWon() {
        for (char c : hiddenWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public int getScore() {
        return score; // Returns current score
    }

    public int getRemainingGuesses() {
        return remainingGuesses; // Optional helper for UI
    }

    public Stack<Character> getGuessHistory() {
        return guessHistory;
    }

    // Comment 6: Score system rewards correct guesses (+10) and punishes incorrect ones (-5)
}
