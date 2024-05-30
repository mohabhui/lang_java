package wlu.mmb.beans;

public class ScoreBean {
private int id;
private String score;

public ScoreBean() {}

public ScoreBean(int id, String score) {
	super();
	this.id = id;
	this.score = score;

}

//constructor without id
public ScoreBean(String score) {
	super();
	this.score = score;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}


}
