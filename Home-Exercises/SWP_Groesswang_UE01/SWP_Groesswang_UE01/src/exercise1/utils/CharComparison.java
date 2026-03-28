package exercise1.utils;

public class CharComparison {
    char character;
    int correctCount;
    int incorrectCount;

    public CharComparison(char character, int correct_count, int incorrect_count) {
        this.character = character;
        this.correctCount = correct_count;
        this.incorrectCount = incorrect_count;
    }

    public char getCharacter() {
        return character;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }
}
