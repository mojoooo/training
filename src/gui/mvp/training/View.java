package gui.mvp.training;

import java.io.IOException;

import javafx.collections.FXCollections;
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
    private Presenter presenter;
    @FXML
    private ListView<String> overViewList;
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
    
    public ListView<String> getOverViewList()
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
    
    public View(Presenter presenter, Stage stage, String fxmlFile, String title) throws IOException
    {       
        Pane root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root, 800, 600);

        this.presenter = presenter;
        this.overViewList = (ListView<String>) scene.lookup("#overviewList");
        this.markerLabel = (Label) scene.lookup("#markerLabel");
        this.distanceLabel = (Label) scene.lookup("#distanceLabel");
        this.timeLabel = (Label) scene.lookup("#timeLabel");
        this.meanSpeedLabel = (Label) scene.lookup("#meanSpeedLabel");
        this.addTrainingUnit = (Button) scene.lookup("#addTrainingUnit");
        this.deleteTrainingUnit = (Button) scene.lookup("#deleteTrainingUnit");
        
        this.overViewList.setItems(FXCollections.observableArrayList(this.presenter.getData().getAllMarkers()));
        this.getAddTrainingUnitButton().setOnAction(e -> this.presenter.createDialog());
        this.getDeleteTrainingUnitButton().setOnAction(e -> this.presenter.removeTrainingUnit());
        
        this.setAllActions();

        stage.setScene(scene);

        stage.setTitle(title);
        stage.show();
    }
    
    public void setAllActions()
    {
        this.getOverViewList().setOnMouseClicked(e -> this.showValues());
    }
    
    public void showValues()
    {
        String selectedItem = this.getOverViewList().getSelectionModel().getSelectedItem();
        if (selectedItem != null && !selectedItem.equals(""))
        {
            this.getMarkerLabel().setText(this.presenter.getData().getTrainingUnit(selectedItem).getMarker());
            this.getDistanceLabel().setText(Float.toString(this.presenter.getData().getTrainingUnit(selectedItem).getDistance()));
            this.getTimeLabel().setText(Float.toString(this.presenter.getData().getTrainingUnit(selectedItem).getTime()));
            this.getMeanSpeedLabel().setText(Float.toString(this.presenter.getData().getTrainingUnit(selectedItem).getMeanSpeed()));  
        }
    }
}
