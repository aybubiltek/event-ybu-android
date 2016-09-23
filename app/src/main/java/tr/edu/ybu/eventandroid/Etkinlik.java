package tr.edu.ybu.eventandroid;

/**
 * Created by User on 4.09.2016.
 */
public class Etkinlik {

    //String resim;
    String etkinlikbaslik;
    String etkinlikayrinti;

    public Etkinlik(/*String resim, */String etkinlikbaslik, String etkinlikayrinti){
        //this.resim=resim;
        this.etkinlikayrinti=etkinlikayrinti;
        this.etkinlikbaslik=etkinlikbaslik;
    }

    //public void setResim(String resim) {
       // this.resim = resim;
    //}

   // public String getResim() {
       // return resim;
    //}

    public void setEtkinlikayrinti(String etkinlikayrinti) {
        this.etkinlikayrinti = etkinlikayrinti;
    }

    public String getEtkinlikayrinti() {
        return etkinlikayrinti;
    }

    public void setEtkinlikbaslik(String etkinlikbaslik) {
        this.etkinlikbaslik = etkinlikbaslik;
    }

    public String getEtkinlikbaslik() {
        return etkinlikbaslik;
    }
}

