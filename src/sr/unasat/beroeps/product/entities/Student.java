package sr.unasat.beroeps.product.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Student{

	private int id; 
	private String naam;
	private String adres;
	private String studierichting;
	private int leeftijd;
	private double cijfergemiddelde;

	public Student(){}
	public Student(int id, String naam, String adres, String studierichting, int leeftijd, double cijfergemiddelde) {
		this.id = id;
		this.naam = naam;
		this.adres = adres;
		this.studierichting = studierichting;
		this.leeftijd = leeftijd;
		this.cijfergemiddelde = cijfergemiddelde;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getStudierichting() {
		return studierichting;
	}
	public void setStudierichting(String studierichting) {
		this.studierichting = studierichting;
	}
	public int getLeeftijd() {
		return leeftijd;
	}
	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}
	public double getCijfergemiddelde() {
		return cijfergemiddelde;
	}
	public void setCijfergemiddelde(double cijfergemiddelde) {
		this.cijfergemiddelde = cijfergemiddelde;
	}
	
	@Override
	public String toString()
	{
	    return ToStringBuilder.reflectionToString(this);
	}
	
}
