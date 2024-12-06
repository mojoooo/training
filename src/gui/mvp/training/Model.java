package gui.mvp.training;

import java.util.ArrayList;

public class Model
{
    private ArrayList<TrainingUnit> trainingUnitList;
    
    public ArrayList<TrainingUnit> getTrainingUnitList()
    {
        return this.trainingUnitList;
    }
    
    public Model()
    {
        this.trainingUnitList = new ArrayList<TrainingUnit>();
    }
    
    public void addTrainingUnit(TrainingUnit unit)
    {
        for (TrainingUnit tUnit : this.trainingUnitList)
        {
            if (tUnit.getMarker() == unit.getMarker())
            {
                throw new IllegalArgumentException("Bezeichnung muss eindeutig sein!");
            }
        }
        
        this.trainingUnitList.add(unit);
    }
    
    public void removeTrainingUnit(String marker)
    {
        this.trainingUnitList.remove(this.getTrainingUnit(marker));
    }
    
    public TrainingUnit getTrainingUnit(String marker)
    {
        for (TrainingUnit unit : this.trainingUnitList)
        {
            if (unit.getMarker().equals(marker))
            {
                return unit;
            }
        }
        
        return null;
    }
    
    public String[] getAllMarkers()
    {
        String[] allMarkers = new String[this.trainingUnitList.size()];
        
        for (int i = 0; i < this.trainingUnitList.size(); i++)
        {
            allMarkers[i] = this.trainingUnitList.get(i).getMarker();
        }
                
        return allMarkers;
    }
}
