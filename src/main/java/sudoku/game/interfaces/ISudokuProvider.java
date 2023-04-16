package sudoku.game.interfaces;

import sudoku.game.models.Sudoku;

public interface ISudokuProvider {

    /**
     * 
     * Returns a new Sudoku board
     * 
     * @return a new Sudoku board
     */
    public Sudoku getNextSudoku();

    /**
     * Parses a Sudoku board from the given text form
     * 
     * @param textForm The text form of the sudoku board
     * 
     * @return the parsed sudoku board
     */
    public Sudoku parseSudoku(String textForm);
}
