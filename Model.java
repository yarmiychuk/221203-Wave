import java.util.ArrayList;
import java.util.Random;

public class Model {

    private final int MIN_SIZE = 10;
    private final int SIZE_DIFFERENCE = 10;
    private final int MAX_PERCENT = 100;
    private final int WALL_PERCENT = 9;
    private final int WALL = -1;
    private final int MAX_FINISH = 2;

    private int[][] table;
    private int[] start;
    private int[][] finish;
    private Cell selectedFinish;

    public Model() {}

    public void createArea() {
        int x = MIN_SIZE + getRandom(SIZE_DIFFERENCE);
        int y = MIN_SIZE + getRandom(SIZE_DIFFERENCE);
        table = new int[x][y];
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j++) {
                if (getRandom(MAX_PERCENT + 1) <= WALL_PERCENT) {
                    table[i][j] = WALL;
                }
            }
        }
    }

    private int getRandom(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    public void setStartPoint() {
        start = new int[2];
        do {
            start[0] = getRandom(table.length);
            start[1] = getRandom(table[0].length);
        } while (table[start[0]][start[1]] < 0);
    }

    public void setFinishPoints() {
        int numOfFinish = getRandom(MAX_FINISH) + 1;
        finish = new int[numOfFinish][2];
        for (int i = 0; i < numOfFinish; i++) {
            do {
                finish[i][0] = getRandom(table.length);
                finish[i][1] = getRandom(table[0].length);
            } while (!isCorrectFinish(finish[i]));
        }
    }

    private boolean isCorrectFinish(int[] finish) {
        return (finish[0] != start[0] || finish[1] != start[1])
            && table[finish[0]][finish[1]] == 0; 
    }

    public int[][] getTable() {
        return table;
    }

    public int[] getStart() {
        return start;
    }

    public int[][] getFinish() {
        return finish;
    }

    public Cell getSelectedFinish() {
        return selectedFinish;
    }

    public void runWave(ArrayList<Cell> cells) {
        if (cells == null) {
            cells = new ArrayList<>();
            cells.add(new Cell(start[0], start[1]));
            table[start[0]][start[1]] = 1;
        }
        ArrayList<Cell> nextCells = new ArrayList<>();
        for (Cell cell: cells) {
            int nextVal = table[cell.x][cell.y] + 1;
            int y = cell.y - 1;
            if (y >= 0 && table[cell.x][y] == 0) {
                nextCells.add(new Cell(cell.x, y));
                table[cell.x][y] = nextVal;
            }
            int x = cell.x + 1;
            if (x < table.length && table[x][cell.y] == 0) {
                nextCells.add(new Cell(x, cell.y));
                table[x][cell.y] = nextVal;
            }
            y = cell.y + 1;
            if (y < table[0].length && table[cell.x][y] == 0) {
                nextCells.add(new Cell(cell.x, y));
                table[cell.x][y] = nextVal;
            }
            x = cell.x - 1;
            if (x >= 0 && table[x][cell.y] == 0) {
                nextCells.add(new Cell(x, cell.y));
                table[x][cell.y] = nextVal;
            }
        }
        if (nextCells.size() > 0) {
            runWave(nextCells);
        }
    }

    public void selectFinishPoint() {
        selectedFinish = new Cell(-1, -1);
        if (finish.length == 1) {
            if (table[finish[0][0]][finish[0][1]] > 0) {
                selectedFinish = new Cell(finish[0][0], finish[0][1]);
            }
        } else {
            if (table[finish[0][0]][finish[0][1]] > 0) {
                if (table[finish[1][0]][finish[1][1]] == 0 ||
                    table[finish[0][0]][finish[0][1]] < table[finish[1][0]][finish[1][1]]) {
                        selectedFinish = new Cell(finish[0][0], finish[0][1]);
                } else if (table[finish[1][0]][finish[1][1]] > 0) {
                    selectedFinish = new Cell(finish[1][0], finish[1][1]);
                }
            } else if (table[finish[1][0]][finish[1][1]] > 0) {
                selectedFinish = new Cell(finish[1][0], finish[1][1]);
            }
        }
    }

    public ArrayList<Cell> getWay() {
        ArrayList<Cell> way = new ArrayList<>();
        if (selectedFinish.x >= 0) {
            int x = selectedFinish.x;
            int y = selectedFinish.y;
            way.add(new Cell(x, y));
            for (int i = table[selectedFinish.x][selectedFinish.y]; i > 1; i--) {
                if (x > 0 && table[x - 1][y] == i - 1) {
                    x = x - 1;
                } else if (y > 0 && table[x][y - 1] == i - 1) {
                    y = y - 1;
                } else if (x < table.length - 1 && table[x + 1][y] == i - 1) {
                    x = x + 1;
                } else {
                    y = y + 1;
                }
                way.add(0, new Cell(x, y));
            }
        }
        return way;
    }
}
