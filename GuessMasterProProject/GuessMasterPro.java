import java.util.*;

public class GuessMasterPro {
    private String hiddenWord;
    private Set<Character> guessedLetters = new HashSet<>();
    private int remainingGuesses = 6;
    private Stack<Character> guessHistory = new Stack<>();
    private int score = 0;

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
            return false;
        }
        guessedLetters.add(letter);
        guessHistory.push(letter);
        if (hiddenWord.contains(String.valueOf(letter))) {
            score += 10;
        } else {
            remainingGuesses--;
            score -= 5;
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
        return score;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }
}
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GuessMasterPro game = new GuessMasterPro("elephant");

        System.out.println("Welcome to GuessMasterPro!");
        while (!game.hasWon() && game.getRemainingGuesses() > 0) {
            System.out.println("\nWord: " + game.getCurrentGuessState());
            System.out.println("Score: " + game.getScore());
            System.out.println("Remaining guesses: " + game.getRemainingGuesses());
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Please enter only one letter.");
                continue;
            }

            char guess = input.charAt(0);
            boolean success = game.makeGuess(guess);
            if (!success) {
                System.out.println("Invalid or repeated guess.");
            }
        }

        if (game.hasWon()) {
            System.out.println("Congratulations! You guessed the word: " + game.getCurrentGuessState());
        } else {
            System.out.println("Game over! The word was: elephant");
        }
        System.out.println("Final Score: " + game.getScore());
    }
}
