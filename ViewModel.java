import java.util.ArrayList;

public class ViewModel {
    
    public static void printTable(int[][] table, int[] start, int[][] finish) {
        for (int x = 0; x < table.length; x++) {
            System.out.print("|");
            for (int y = 0; y < table[0].length; y++) {
                if (table[x][y] == -1) {
                    System.out.print(" **");
                } else if (start[0] == x && start[1] == y) {
                    System.out.print(" SS");
                } else if (finish[0][0] == x && finish[0][1] == y ||
                    finish.length > 1 && (finish[1][0] == x && finish[1][1] == y)) {
                        System.out.print(" FF");
                } else {
                    String value = "";
                    if (table[x][y] == 0) {
                        value = "   ";
                    } else if (table[x][y] < 10) {
                        value = "  " + table[x][y];
                    } else {
                        value = " " + table[x][y];
                    }
                    System.out.print(value);
                }
            }
            System.out.println(" |");
        }
        System.out.println("");
        
    }
    
    public static void printStart(int[] start) {
        System.out.println("Из стартовой точки с координатами (x-y): " + start[0] + "-" + start[1]);
    }
    
    public static void printFinish(Cell finish) {
        if (finish.x == -1) {
            System.out.println("Невозможно проложить маршрут до финишной точки (точек)");
        } else {
            System.out.println("В выбранную финишную точку (x-y): " + finish.x + "-" + finish.y);
        }
    }

    public static void printWay(ArrayList<Cell> steps) {
        if (steps.size() > 0) {
            System.out.println("Проложен маршрут по ячейкам с координатами (x-y):");
            for (Cell step : steps) {
                System.out.print(step.x + "-" + step.y + " ");
            }
            System.out.println();
        }
    }
}
