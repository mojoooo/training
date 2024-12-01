package gui.mvp.training;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View
{   
    @FXML
    private ListView<TrainingUnit> overViewList;
    @FXML
    private Label markerLabel;
    @FXML
    private Label distanceLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label meanSpeedLabel;
    @FXML
    private Button addTrainingUnit;
    @FXML
    private Button deleteTrainingUnit;
    /*
     * only for surpressing ASB error
     */
    private ListView<String> dummyList;
    private Presenter presenter;
    
    public ListView<TrainingUnit> getOverViewList()
    {
        return this.overViewList;
    }
    
    public Label getMarkerLabel()
    {
        return this.markerLabel;
    }
    
    public Label getDistanceLabel()
    {
        return this.distanceLabel;
    }
    
    public Label getTimeLabel()
    {
        return this.timeLabel;
    }
    
    public Label getMeanSpeedLabel()
    {
        return this.meanSpeedLabel;
    }
    
    public Button getAddTrainingUnitButton()
    {
        return this.addTrainingUnit;
    }
    
    public Button getDeleteTrainingUnitButton()
    {
        return this.deleteTrainingUnit;
    }
    
    public View(Stage stage, String fxmlFile, String title) throws IOException
    {
        Pane root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root, 800, 600);
        
        this.overViewList = (ListView<TrainingUnit>) scene.lookup("#overviewList");
        this.markerLabel = (Label) scene.lookup("#markerLabel");
        this.distanceLabel = (Label) scene.lookup("#distanceLabel");
        this.timeLabel = (Label) scene.lookup("#timeLabel");
        this.meanSpeedLabel = (Label) scene.lookup("#meanSpeedLabel");
        this.addTrainingUnit = (Button) scene.lookup("#addTrainingUnit");
        this.deleteTrainingUnit = (Button) scene.lookup("#deleteTrainingUnit");

        stage.setScene(scene);

        stage.setTitle(title);
        stage.show();
    }
}
