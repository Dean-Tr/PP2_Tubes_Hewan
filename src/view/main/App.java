package view.main;

public class App {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainController.showMainFrame();
            }
        });
    }
}
