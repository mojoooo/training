package gui.mvp.training;

import java.io.IOException;

import javafx.fxml.FXML;

public class Presenter
{
    private View view;
    private Model data;

    public Presenter(View view, Model data)
    {
        this.view = view;
        this.data = data;
        
        this.setAllActions();
    }
    
    private void setAllActions()
    {
        this.view.getOverViewList().setItems(this.data.getTrainingUnitList());
        this.view.getOverViewList().setOnMouseClicked(e -> showValues());
        this.view.getAddTrainingUnitButton().setOnAction(e -> this.createDialog());
        this.view.getDeleteTrainingUnitButton().setOnAction(e -> this.removeTrainingUnit());
    }
    
    @FXML public void showValues()
    {
        this.view.getMarkerLabel().setText(this.view.getOverViewList().getSelectionModel().getSelectedItem().getMarker());
        this.view.getDistanceLabel().setText(Float.toString(this.view.getOverViewList().getSelectionModel().getSelectedItem().getDistance()));
        this.view.getTimeLabel().setText(Float.toString(this.view.getOverViewList().getSelectionModel().getSelectedItem().getTime()));
        this.view.getMeanSpeedLabel().setText(Float.toString(this.view.getOverViewList().getSelectionModel().getSelectedItem().getMeanSpeed()));
    }
    
    public void createDialog()
    {
        try
        {
            EditorDialog modal = new EditorDialog();
            modal.getAdd().setOnAction(e -> handleDialogActions(modal));
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    public void handleDialogActions(EditorDialog modal)
    {
        String marker = modal.getMarkerTF().getText();
        float distance = Float.parseFloat(modal.getDistanceTF().getText());
        float time = Float.parseFloat(modal.getTimeTF().getText());
        
        TrainingUnit newUnit = new TrainingUnit(marker, distance, time);
        
        this.data.addTrainingUnit(newUnit);
        
        this.view.getOverViewList().getSelectionModel().select(newUnit);
    }
    
    public void removeTrainingUnit()
    {
        this.data.removeTrainingUnit(this.view.getOverViewList().getSelectionModel().getSelectedItem().toString());
        if (!this.data.getTrainingUnitList().isEmpty())
        {
            this.view.getOverViewList().getSelectionModel().select(0);
            this.view.getOverViewList().getFocusModel().focus(0);
        }
    }
}
