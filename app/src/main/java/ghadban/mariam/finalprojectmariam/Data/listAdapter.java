package ghadban.mariam.finalprojectmariam.Data;

import java.util.ArrayList;

public class listAdapter
{
    private ArrayList<Place>placeslist = new ArrayList<Place>();

    public listAdapter(){
    }

    public ArrayList<Place> getPlaceslist() { return placeslist; }
    public void setPlaceslist(ArrayList<Place> placeslist) { this.placeslist = placeslist; }

    public void addplaces(Place p) { placeslist.add(p);}
}
