package tr.edu.ybu.eventandroid;

/**
 * Created by zeynep on 22.09.2016.
 */
public class Club {
    String name;
    String explanation;
    private int idImg;

    Club(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }

    public String getExp() {

        return explanation;
    }

    public int getIdImg() {
        return idImg;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDist(String explanation) {

        this.explanation = explanation;
    }

}
