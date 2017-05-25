package sr.unasat.beroeps.product.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by mitchel on 5/18/17.
 */
public class Rooster {
    private int id;
    private Student student;
    private Vak vak;
    private String dag;
    private String startTijd;
    private String endTijd;

    public Rooster(){}

    public Rooster(int id, Student student, Vak vak, String dag, String startTijd, String endTijd) {
        this.id = id;
        this.student = student;
        this.vak = vak;
        this.dag = dag;
        this.startTijd = startTijd;
        this.endTijd = endTijd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public String getDag() {
        return dag;
    }

    public void setDag(String dag) {
        this.dag = dag;
    }

    public String getStartTijd() {
        return startTijd;
    }

    public void setStartTijd(String startTijd) {
        this.startTijd = startTijd;
    }

    public String getEndTijd() {
        return endTijd;
    }

    public void setEndTijd(String endTijd) {
        this.endTijd = endTijd;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
