public class Controller {

    public static void run() {
        Model data = new Model();
        
        data.createArea();
        data.setStartPoint();
        data.setFinishPoints();
        ViewModel.printTable(data.getTable(), data.getStart(), data.getFinish());
        data.runWave(null);
        ViewModel.printTable(data.getTable(), data.getStart(), data.getFinish());
        ViewModel.printStart(data.getStart());
        data.selectFinishPoint();
        ViewModel.printFinish(data.getSelectedFinish());
        ViewModel.printWay(data.getWay());
    }
    
}
