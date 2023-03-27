package sudoku;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.text.SimpleDateFormat;

public class SudokuFactory {
    private static final int BOARD_SIZE = 9;
    private static final int NUMBER_OF_REGIONS = 27;

    public static Board createBoard() {
        Board board = new Board();
        Collection<ValidationRegion> validationRegions = new ArrayList<ValidationRegion>(NUMBER_OF_REGIONS);

        // Add empty cells
        IntStream.range(0, BOARD_SIZE).forEach(i -> {
            IntStream.range(0, BOARD_SIZE).forEach(j -> {
                Cell cell = new Cell();
                board.setCell(i, j, cell);
            });
        });

        // Add validation regions for rows and columns
        IntStream.range(0, BOARD_SIZE).forEach(i -> {
            ValidationRegion rowRegion = new ValidationRegion();
            ValidationRegion columnRegion = new ValidationRegion();
            IntStream.range(0, BOARD_SIZE).forEach(j -> {
                rowRegion.addCell(board.getCell(i, j));
                columnRegion.addCell(board.getCell(j, i));
            });
            board.addValidationRegion(rowRegion);
            board.addValidationRegion(columnRegion);
        });

        // Add validation regions for 3x3 squares
        IntStream.range(0, BOARD_SIZE).forEach(i -> {
            IntStream.range(0, BOARD_SIZE).forEach(j -> {
                if (i % 3 == 0 && j % 3 == 0) { // Top left cell of a 3x3 square
                    ValidationRegion squareRegion = new ValidationRegion();

                    // Find all cells in the 3x3 square by offsetting the row and column
                    IntStream.range(0, 3).forEach(k -> { // Iterate over 3 rows
                        IntStream.range(0, 3).forEach(l -> { // Iterate over 3 columns
                            squareRegion.addCell(board.getCell(i + k, j + l));
                        });
                    });
                    board.addValidationRegion(squareRegion);
                }
            });
        });

        return board;
    }

    public static Sudoku createSudokuFromInputStream(InputStream is) throws Exception {
        Board board = createBoard();
        Scanner s = new Scanner(is);

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
         */

        String name = "sudoku";
        String author = "UNKNOWN";
        String description = "UNKNOWN";
        String comment = "UNKNOWN";
        Date date = new Date();
        String sourceName = "UNKNOWN";
        Difficulty difficulty = Difficulty.UNKNOWN;
        String sourceUrl = "UNKNOWN";

        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.contains("#")) {
                line = line.substring(1, line.length());
                String id = line.substring(0, 1);
                switch (id) {
                    case "A":
                        author = line.substring(1, line.length());
                        break;
                    case "D":
                        description = line.substring(1, line.length());
                        break;
                    case "C":
                        comment = line.substring(1, line.length());
                        break;
                    case "B":
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        date = formatter.parse(line.substring(1, line.length()));
                        name = line.substring(1, line.length());
                        break;
                    case "S":
                        sourceName = line.substring(1, line.length());
                        break;
                    case "L":
                        difficulty = Difficulty.valueOf(line.substring(1, line.length()).toUpperCase());
                        break;
                    case "U":
                        sourceUrl = line.substring(1, line.length());
                        break;
                    default:
                        break;
                }
            }

            // Sudokuboard
            else {
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        char c = line.charAt(j);
                        if (c != '.') {
                            board.setCell(i, j, new Cell(Character.getNumericValue(c), true));
                        } else {
                            board.setCell(i, j, new Cell());
                        }
                    }
                    if (s.hasNextLine())
                        line = s.nextLine();
                }
            }
        }
        return new Sudoku(name, board, author, description, comment, date, sourceName, difficulty, sourceUrl);
    }
}
