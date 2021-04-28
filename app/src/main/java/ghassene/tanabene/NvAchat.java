package ghassene.tanabene;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NvAchat {

private String Achat;
String date;
private String prix;
private int id;
public static float total ;

    public NvAchat(String achat, Float prix){}

    public NvAchat(String achat, String prix)
    {   Achat = achat;
        this.prix = prix;
        String pattern = "MM/dd/yyyy HH:mm:ss";

        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();

        date = df.format(today);
    }

    public NvAchat(String achat) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAchat() {
        return Achat;
    }

    public void setAchat(String achat) {
        Achat = achat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}