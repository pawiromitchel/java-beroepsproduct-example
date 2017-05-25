package sr.unasat.beroeps.product.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Jonathan on 5/17/2017.
 */
public class Vak {
    private int id;
    private String vakNaam;
    private boolean tentamenGemaakt;
    private String docent;
    private int studiePunten;

    public Vak(){}

    public Vak(int id, String vakNaam, boolean tentamenGemaakt, String docent, int studiePunten) {
        this.id = id;
        this.vakNaam = vakNaam;
        this.tentamenGemaakt = tentamenGemaakt;
        this.docent = docent;
        this.studiePunten = studiePunten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVakNaam() {
        return vakNaam;
    }

    public void setVakNaam(String vakNaam) {
        this.vakNaam = vakNaam;
    }

    public boolean isTentamenGemaakt() {
        return tentamenGemaakt;
    }

    public void setTentamenGemaakt(boolean tentamenGemaakt) {
        this.tentamenGemaakt = tentamenGemaakt;
    }

    public String getDocent() {
        return docent;
    }

    public void setDocent(String docent) {
        this.docent = docent;
    }

    public int getStudiePunten() {
        return studiePunten;
    }

    public void setStudiePunten(int studiePunten) {
        this.studiePunten = studiePunten;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
