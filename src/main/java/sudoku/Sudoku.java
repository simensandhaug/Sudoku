package sudoku;

import java.io.IOException;
import java.util.Timer;

public class Sudoku {
    private Puzzle puzzle;
    private Difficulty difficulty;
    private int[][] solution;
    private Timer timer;

    public Sudoku(String sdkFilePath) throws IOException {
        puzzle = PuzzleFactory.generateFromSDK(sdkFilePath);
        difficulty = Difficulty.EASY;
        timer = new Timer();
    }

    public Sudoku(Difficulty difficulty) throws IOException {
        puzzle = PuzzleFactory.generateFromDifficulty(difficulty);
        this.difficulty = difficulty;
        timer = new Timer();
    }

    public Cell[][] getPuzzle() {
        return puzzle.getPuzzle();
    }
}
