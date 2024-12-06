package gui.mvp.training;

import java.io.IOException;
import java.util.ArrayList;

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
        }
        catch (IOException e)
        {
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
        
        //this.view.getOverViewList().getSelectionModel().select(newUnit);
        
        modal.close();
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
    /*
    public ArrayList<String> setMarkers()
    {
        ArrayList<String> list = new ArrayList<String>();
        
        for (String marker : this.data.getAllMarkers())
        {
            list.add(marker);
        }
        return list;
    }
    */
}
