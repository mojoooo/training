package gui.mvp.training;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditorDialog extends Stage
{
    @FXML
    private TextField markerTF;
    @FXML
    private TextField distanceTF;
    @FXML
    private TextField timeTF;
    @FXML
    private Label errMsgLabel;
    @FXML
    private Button add;
    @FXML
    private Button cancel;
    
    public TextField getMarkerTF()
    {
        return this.markerTF;
    }
    
    public TextField getDistanceTF()
    {
        return this.distanceTF;
    }
    
    public TextField getTimeTF()
    {
        return this.timeTF;
    }
    
    public Label getErrMsgLabel()
    {
        return this.errMsgLabel;
    }
    
    public Button getAdd()
    {
        return this.add;
    }
    
    public Button getCancel()
    {
        return this.cancel;
    }

    public EditorDialog() throws IOException
    {
        this.initModality(Modality.APPLICATION_MODAL);
        
        Pane dialog = FXMLLoader.load(getClass().getResource("trainingmodal.fxml"));
        Scene scene = new Scene(dialog, 640, 480);
        
        this.markerTF = (TextField) scene.lookup("#markerTF");
        this.distanceTF = (TextField) scene.lookup("#distanceTF");
        this.timeTF = (TextField) scene.lookup("#timeTF");
        this.errMsgLabel = (Label) scene.lookup("#errMsgLabel");
        this.add = (Button) scene.lookup("#add");
        this.cancel = (Button) scene.lookup("#cancel");

        this.setScene(scene);

        this.setTitle("Dialog");
        this.show();
    }
}
