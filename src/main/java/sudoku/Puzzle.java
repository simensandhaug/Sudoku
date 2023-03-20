package sudoku;

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

    public void setPuzzle(Cell[][] puzzle) {
        this.puzzle = puzzle;
    }

    public String getSdkFilePath() {
        return this.sdkFilePath;
    }

    public void setSdkFilePath(String sdkFilePath) {
        this.sdkFilePath = sdkFilePath;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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

    public String getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceURL() {
        return this.sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

}
