package ghadban.mariam.finalprojectmariam.Data;

import java.io.Serializable;

public class Place implements Serializable
{
        private  String name;//asm almkan
        private String location;//mo93
        private double lat;
        private double lng;
        private String category;//mjal almkan
        private double Evaluation;// ta9im almkan
        private String Image;
        private String owner;
        private String key;


    public Place(){
        }

        public String getName(){ return name; }
        public void setName(String name){ this.name = name; }

        public String getLocation(){
            return location;
        }
        public void setLocation(String location){
            this.location = location;
        }

        public double getLat() { return lat; }
        public void setLat(double lat) { this.lat = lat; }

        public double getLng() { return lng; }
        public void setLng(double lng) { this.lng = lng; }

        public String getCategory(){
            return category;
        }
        public void setCategory(String category){
            this.category = category;
        }

        public double getEvaluation() { return Evaluation; }
        public void setEvaluation(double evaluation) { this.Evaluation = evaluation; }

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
        return "Place{" +
                "location='" + location + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", category='" + category + '\'' +
                ", Evaluation=" + Evaluation +
                ", Image='" + Image + '\'' +
                ", owner='" + owner + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}


