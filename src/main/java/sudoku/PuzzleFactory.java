package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleFactory {
    public static Cell[][] generateEmptyPuzzle() {
        Cell[][] puzzle = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = new Cell(0, CellStatus.TO_GUESS);
            }
        }
        return puzzle;
    }

    public static Puzzle generateFromDifficulty(Difficulty difficulty) {
        Cell[][] puzzle = generateEmptyPuzzle();
        switch (difficulty) {
            case EASY:
                generateEasy(puzzle);
                break;
            case MEDIUM:
                generateMedium(puzzle);
                break;
            case HARD:
                generateHard(puzzle);
                break;
            case UNKNOWN:
                break;
        }
        return new Puzzle(puzzle, "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown",
                "Unknown");
    }

    private static Puzzle generateEasy(Cell[][] puzzle) {
        return new Puzzle(puzzle, "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown",
                "Unknown");
    }

    private static Puzzle generateMedium(Cell[][] puzzle) {
        return new Puzzle(puzzle, "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown",
                "Unknown");
    }

    private static Puzzle generateHard(Cell[][] puzzle) {
        return new Puzzle(puzzle, "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown",
                "Unknown");
    }

    public static Puzzle generateFromSDK(String sdkFilePath) throws IOException {
        Cell[][] puzzle = generateEmptyPuzzle();
        String author;
        String description;
        String comment;
        String datePublished;
        String source;
        String difficulty;
        String sourceURL;

        try (BufferedReader reader = new BufferedReader(new FileReader(sdkFilePath))) {
            String line;
            int row = 0;

            // Example file
            /*
             * #ARuud
             * #DA random puzzle created by SudoCue
             * #CJust start plugging in the numbers
             * #B03-08-2006
             * #SSudoCue
             * #LEasy
             * #Uhttp://www.sudocue.net/
             * 2..1.5..3
             * .54...71.
             * .1.2.3.8.
             * 6.28.73.4
             * .........
             * 1.53.98.6
             * .2.7.1.6.
             * .81...24.
             * 7..4.2..1
             * src: https://www.sudocue.net/fileformats.php#:~:text=sdm,a%20collection%20of%
             * 20sudoku%20puzzles.
             */

            // split line from index 2 to the last

            author = reader.readLine().substring(3);
            description = reader.readLine().substring(3);
            comment = reader.readLine().substring(3);
            datePublished = reader.readLine().substring(3);
            source = reader.readLine().substring(3);
            difficulty = reader.readLine().substring(3);
            sourceURL = reader.readLine().substring(3);

            if (difficulty.toUpperCase() != "EASY" || difficulty.toUpperCase() != "MEDIUM"
                    || difficulty.toUpperCase() != "HARD") {
                difficulty = "UNKNOWN";
            } else {
                difficulty = difficulty.toUpperCase();
            }

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    if (Character.isDigit(c)) {
                        puzzle[row][col].setValue(Character.getNumericValue(c));
                        puzzle[row][col].setStatus(CellStatus.GIVEN);
                    }
                }
                row++;
            }
        }

        return new Puzzle(puzzle, sdkFilePath, author, description, comment, datePublished, source, difficulty,
                sourceURL);
    }
}
