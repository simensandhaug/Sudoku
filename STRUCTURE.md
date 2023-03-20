Classes:
Sudoku
-> Board board;
-> String author;
-> String description;
-> String comment;
-> Date datePublished;
-> String sourceName;
-> Difficulty level/difficulty;
-> String sourceURL;

ValidationRegion (Box, Row, Column)
-> Integer row;
-> Integer column;
-> Collection<Cell> = new Collection<Cell>(9);
-> boolean validate();

Cell
-> Integer value;
-> Set<Integer> remainingPossibleValues;
-> boolean isFixed;
-> Collection<ValidationRegion> regions = new Collection<ValidationRegion>(3);

Board
-> Cell[][] cells;
-> Collection<ValidationRegion> validationRegions;
-> Collection<Pattern> solvingPatterns;
-> void solve(); // Uses patterns to solve, and lastly bruteforce if necessary.

abstract Pattern
-> apply(Board); //Removes possibilities from cells and solves if only one left

OneChoicePattern:Pattern
SinglePossibilityPattern:Pattern
OnlySquareRulePattern:Pattern
https://sudokudragon.com/sudokustrategy.html


