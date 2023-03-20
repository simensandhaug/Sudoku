package sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuFactory {
    private static final int BOARD_SIZE = 9;
    private static final int NUMBER_OF_REGIONS = 27;

    public static Board createBoard() {
        Board board = new Board();
        Collection<ValidationRegion> validationRegions = new ArrayList<ValidationRegion>(NUMBER_OF_REGIONS);

        // Add empty cells
        IntStream.range(0, BOARD_SIZE).forEach(i -> {
            IntStream.range(0, BOARD_SIZE).forEach(j -> {
                Cell cell = new Cell(0, false);
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

    public static Sudoku createSudokuFromSDKFile(String sdkFile) {
        try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(new File(sdkFile))))) {
            List<String> data = stream
                    .map(s -> s.strip())
                    .toList();
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
