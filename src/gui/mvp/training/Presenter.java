package gui.mvp.training;

import java.io.IOException;

import javafx.application.Platform;

public class Presenter
{
    private Model data;
    private View view;

    public Presenter(Model data)
    {
        this.data = data;
    }
    
    public Model getData()
    {
        return this.data;
    }
    
    public void initView(View vw)
    {
        this.view = vw;
    }
    
    public void createDialog()
    {
        try
        {
            EditorDialog modal = new EditorDialog();
            modal.getAdd().setOnAction(e -> handleDialogActions(modal));
            modal.getCancel().setOnAction(e -> closeDialog(modal));
            this.setTextfieldListener(modal);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
    
    public void setTextfieldListener(EditorDialog modal)
    {
        modal.getMarkerTF().textProperty().addListener((observable, oldValue, newValue) -> 
        {
            this.clearErrMsg(modal);
            
            if (newValue.equals(" "))
            {

                Platform.runLater(() ->  modal.getErrMsgLabel().setText("Kennung: ungültige Eingabe"));
            }
            
            if (this.data.getTrainingUnit(newValue) != null)
            {

                Platform.runLater(() -> modal.getErrMsgLabel().setText("Kennung: existiert schon"));
            }
        });
        
        modal.getDistanceTF().textProperty().addListener((observable, oldValue, newValue) -> 
        {
            this.clearErrMsg(modal);

            Float distance = null;

            try
            {
                distance = Float.parseFloat(modal.getDistanceTF().getText());
            }
            catch (Exception e)
            {

            }
            
            if (distance == null)
            {
                Platform.runLater(() -> modal.getErrMsgLabel().setText("Entfernung: ungültige Eingabe"));
            }
            
        });
        
        modal.getTimeTF().textProperty().addListener((observable, oldValue, newValue) -> 
        {
            this.clearErrMsg(modal);
            
            Float time = null;

            try
            {
                time = Float.parseFloat(modal.getTimeTF().getText());
            }
            catch (Exception e)
            {

            }
            
            if (time == null)
            {
                Platform.runLater(() -> modal.getErrMsgLabel().setText("Zeit: ungültige Eingabe"));
            }
        });
    }
    
    public void clearErrMsg(EditorDialog modal)
    {
        Platform.runLater(() -> modal.getErrMsgLabel().setText(""));
    }
    
    public void handleDialogActions(EditorDialog modal)
    {
        String marker = modal.getMarkerTF().getText();
        
        if (this.data.getTrainingUnit(marker) != null)
        {            
            Platform.runLater(() -> modal.getErrMsgLabel().setText("Kennung: existiert schon"));
            return;
        }
        
        if (marker.equals(""))
        {
            Platform.runLater(() -> modal.getErrMsgLabel().setText("Kennung: ungültige Eingabe"));
            
            return;
        }
                       
        Float distance = null;
        Float time = null;

        try
        {
            distance = Float.parseFloat(modal.getDistanceTF().getText());
            time = Float.parseFloat(modal.getTimeTF().getText());
        }
        catch (Exception e)
        {

        }
        
        if (marker != null && distance != null && time != null)
        {
            TrainingUnit newUnit = new TrainingUnit(marker, distance, time);
            
            this.data.addTrainingUnit(newUnit);
            
            this.view.getOverViewList().getItems().add(newUnit.getMarker());
            this.view.getOverViewList().getSelectionModel().select(newUnit.getMarker());
            this.view.showValues();
            
            modal.close();   
        }
    }
    
    public void closeDialog(EditorDialog modal)
    {
        modal.close();
    }
    
    public void removeTrainingUnit()
    {
        String selectedItem = this.view.getOverViewList().getSelectionModel().getSelectedItem();
        if (selectedItem != null && !selectedItem.equals(""))
        {
            this.data.removeTrainingUnit(selectedItem); 
            this.view.getOverViewList().getItems().remove(selectedItem);
        }
        
        if (!this.data.getTrainingUnitList().isEmpty())
        {
            this.view.getOverViewList().getSelectionModel().select(0);
            this.view.getOverViewList().getFocusModel().focus(0);
        }
        
        this.view.showValues();
    }
}
