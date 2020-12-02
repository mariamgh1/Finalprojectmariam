package ghadban.mariam.finalprojectmariam.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class place
{
        private String name;//asm almkan
        private String location;//mo93
        private String category;//mjal almkan
        private float Evaluation;// ta9im almkan
        private HashMap<String, String> comments = new HashMap<String, String>();// masfofat comments
        private int numcom;//5laya almasfofa


    public place(){

        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }

        public String getLocation(){
            return location;
        }
        public void setLocation(String location){
            this.location = location;
        }

        public String getCategory(){
            return category;
        }
        public void setCategory(String category){
            this.category = category;
        }

        public float getEvaluation() { return Evaluation; }
        public void setEvaluation(float evaluation) { this.Evaluation = evaluation; }

        public HashMap<String, String> getComments() { return comments; }
        public void setComments(HashMap<String, String> comments) { this.comments = comments; }

        public int getNumcom() { return numcom; }
        public void setNumcom(int numcom) { this.numcom = numcom; }


    public void addcomment(String id, String s)
    {
     comments.put(id, s);
    }


}
