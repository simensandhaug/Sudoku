package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class SudokuFileSupport implements ISudokuFileReading {
    public final static String FILE_EXTENSION = "";

    private Path getSudokuUserFolderPath() {
        return Path.of(System.getProperty("user.home"), "tdt4100", "sudoku");
    }

    private boolean ensureSudokuUserFolder() {
        try {
            Files.createDirectories(getSudokuUserFolderPath());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private Path getSudokuPath(String name) {
        return getSudokuUserFolderPath().resolve(name + FILE_EXTENSION);
    }

    @Override
    public Sudoku readSudoku(InputStream is) {
        try {
            return SudokuFactory.createSudokuFromInputStream(is);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sudoku readSudoku(String name) throws IOException {
        Path path = getSudokuPath(name);
        try (InputStream is = new FileInputStream(path.toFile())) {
            return readSudoku(is);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void writeSudoku(Sudoku sudoku, OutputStream os) {
        try (var writer = new PrintWriter(os)) {

            String A = sudoku.getAuthor();
            String D = sudoku.getDescription();
            String C = sudoku.getComment();
            Date B = sudoku.getDate();
            String S = sudoku.getSourceName();
            Difficulty L = sudoku.getDifficulty();
            String difficulty = L.toString();
            String U = sudoku.getSourceUrl();
            writer.println("#A" + A);
            writer.println("#D" + D);
            writer.println("#C" + C);
            writer.println("#B" + B.toString());
            writer.println("#S" + S);
            writer.println("#L" + difficulty);
            writer.println("#U" + U);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Integer value = sudoku.getBoard().getCell(i, j).getValue();
                    if (value == 0) {
                        writer.print(".");
                    } else {
                        writer.print(value);
                    }
                }
                writer.println();
            }
        }

    }

    @Override
    public void writeSudoku(Sudoku sudoku) throws IOException {
        var todoListPath = getSudokuPath(sudoku.getName());
        ensureSudokuUserFolder();
        try (var output = new FileOutputStream(todoListPath.toFile())) {
            writeSudoku(sudoku, output);
        }

    }
}
