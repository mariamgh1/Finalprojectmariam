package ghadban.mariam.finalprojectmariam.Data;

public class place
{
        private String name;
        private String location;
        private String category;
        private String[]content;
        private float Evaluation;
        private String[]comments;
        private int numcom;


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

        public String[] getContent() { return content; }

        public void setContent(String[] content) { this.content = content; }

        public float getEvaluation() { return Evaluation; }

        public void setEvaluation(float evaluation) { this.Evaluation = evaluation; }

        public String[] getComments() { return comments; }

        public void setComments(String[] comments) { this.comments = comments; }

        public int getNumcom() { return numcom; }

        public void setNumcom(int numcom) { this.numcom = numcom; }

    public void addcomment(String s){
        comments[numcom]=s;
        numcom++;
        }
}
