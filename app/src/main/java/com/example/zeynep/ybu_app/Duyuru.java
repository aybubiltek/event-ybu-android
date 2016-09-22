package com.example.zeynep.ybu_app;

/**
 * Created by User on 4.09.2016.
 */
public class Duyuru {

    String baslik;
    String ayrinti;

    public Duyuru(String baslik, String ayrinti){
        this.baslik=baslik;
        this.ayrinti=ayrinti;
    }

    public void setAyrinti(String ayrinti) {
        this.ayrinti = ayrinti;
    }

    public String getAyrinti() {
        return ayrinti;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getBaslik() {
        return baslik;
    }
}
