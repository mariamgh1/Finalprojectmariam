package ghadban.mariam.finalprojectmariam.Data;

import java.util.ArrayList;

public class listAdapter
{
    private ArrayList<place>placeslist = new ArrayList<place>();

    public listAdapter(){
    }

    public ArrayList<place> getPlaceslist() { return placeslist; }

    public void setPlaceslist(ArrayList<place> placeslist) { this.placeslist = placeslist; }
}
