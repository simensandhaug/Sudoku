package sudoku;

import java.io.IOException;

public class Puzzle {
    private Cell[][] puzzle;
    private String sdkFilePath;
    private Difficulty difficulty;
    private String author;
    private String description;
    private String comment;
    private String datePublished;
    private String source;
    private String sourceURL;

    public Puzzle(Cell[][] puzzle, String sdkFilePath, String author, String description, String comment,
            String datePublished, String source, String difficulty, String sourceURL) {
        this.puzzle = puzzle;
        this.sdkFilePath = sdkFilePath;
        this.author = author;
        this.description = description;
        this.comment = comment;
        this.datePublished = datePublished;
        this.source = source;
        this.difficulty = Difficulty.valueOf(difficulty);
        this.sourceURL = sourceURL;

    }

    public Cell[][] getPuzzle() {
        return puzzle;
    }
}
