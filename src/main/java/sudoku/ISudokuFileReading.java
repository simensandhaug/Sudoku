package sudoku;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ISudokuFileReading {
    /**
     * Read a sudoku from a given InputStream.
     * 
     * @param ios The input stream to read from.
     * @return The sudoku from the InputStream.
     */
    Sudoku readSudoku(InputStream is);

    /**
     * Read a sudoku with a given name, from a default (implementation-specific)
     * location.
     * 
     * @param name The name of the sudoku
     * @return The sudoku with the given name from the default location
     * @throws IOException if the sudoku can't be found.
     */
    Sudoku readSudoku(String name) throws IOException;

    /**
     * Write a sudoku to a given OutputStream
     * 
     * @param sudoku The list to write
     * @param os     The stream to write to
     */
    void writeSudoku(Sudoku sudoku, OutputStream os);

    /**
     * Write a sudoku to a file named after the list in a default (implementation
     * specific) location.
     * 
     * @param sudoku The list to write
     * @throws IOException If a file at the proper location can't be written to
     */
    void writeSudoku(Sudoku sudoku) throws IOException;
}
