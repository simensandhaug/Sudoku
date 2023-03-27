package sudoku;

import java.util.Date;

public class Sudoku {
    private String name;
    private Board board;
    private String author;
    private String description;
    private String comment;
    private Date date;
    private String sourceName;
    private Difficulty difficulty;
    private String sourceUrl;

    public Sudoku(String name, Board board, String author, String description, String comment, Date date,
            String sourceName,
            Difficulty difficulty, String sourceUrl) {
        this.name = name;
        this.board = board;
        this.author = author;
        this.description = description;
        this.comment = comment;
        this.date = date;
        this.sourceName = sourceName;
        this.difficulty = difficulty;
        this.sourceUrl = sourceUrl;
    }

    public void update() {
        board.validate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        String o = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                o += board.getCell(i, j).getValue();
                o += " ";
            }
            o += "\n";
        }
        return o;
    }
}
