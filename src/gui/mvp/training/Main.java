package gui.mvp.training;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    private Model data;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        createTestData();
        View view = new View(primaryStage, "traininglist.fxml", "Trainingseinheiten");
        Presenter presenter = new Presenter(view, this.data);
    }
    
    public void createTestData()
    {
        Model model = new Model();
        TrainingUnit unit1 = new TrainingUnit("Montag", 13, 5);
        TrainingUnit unit2 = new TrainingUnit("Dienstag", 12, 7);
        TrainingUnit unit3 = new TrainingUnit("Mittwoch", 11, 9);
        
        model.addTrainingUnit(unit1);
        model.addTrainingUnit(unit2);
        model.addTrainingUnit(unit3);
        
        this.data = model;
    }
}
