package ghadban.mariam.finalprojectmariam.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Place
{
        private static String name;//asm almkan
        private String location;//mo93
        private String category;//mjal almkan
        private String Evaluation;// ta9im almkan
        private String Image;
        private String owner;
        private String key;


    public Place(){
        }

        public static String getName(){
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

        public String getImage() { return Image; }
        public void setImage(String Image) { this.Image = Image; }

        public String getOwner() {
        return owner;
    }
        public void setOwner(String owner) {
        this.owner = owner;
    }

        public String getKey() {
        return key;
    }
        public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return "place{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", Evaluation=" + Evaluation +'}';
    }

}
