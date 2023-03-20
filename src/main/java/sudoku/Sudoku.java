package sudoku;

import java.util.Date;

public class Sudoku {
    private Board board;
    private String author;
    private String description;
    private String comment;
    private Date date;
    private String sourceName;
    private Difficulty difficulty;
    private String sourceUrl;

    public Sudoku(Board board, String author, String description, String comment, Date date, String sourceName,
            Difficulty difficulty, String sourceUrl) {
        this.board = board;
        this.author = author;
        this.description = description;
        this.comment = comment;
        this.date = date;
        this.sourceName = sourceName;
        this.difficulty = difficulty;
        this.sourceUrl = sourceUrl;
    }

    public Board getBoard() {
        return board;
    }
}
