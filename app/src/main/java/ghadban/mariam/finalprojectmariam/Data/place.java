package ghadban.mariam.finalprojectmariam.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class place
{
        private String name;//asm almkan
        private String location;//mo93
        private String category;//mjal almkan
        private String Evaluation;// ta9im almkan


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

        public String getEvaluation() { return Evaluation; }
        public void setEvaluation(String evaluation) { this.Evaluation = evaluation; }


    @Override
    public String toString() {
        return "place{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", Evaluation=" + Evaluation +'}';
    }
}
