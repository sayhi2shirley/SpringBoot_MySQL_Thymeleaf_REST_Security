package shangrila.council.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Options")
public class Options {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="optionid")
	private int optionID;

	@ManyToOne
	@JoinColumn(name="question_id")
	@JsonBackReference
	private Questions question;

	@Column(name="optiontext", nullable=false)
	private String optionText;

	public Options(int optionID, Questions question, String optionText) {
		super();
		this.optionID = optionID;
		this.question = question;
		this.optionText = optionText;
	}


	public Options() { }

	public int getOptionID() {
		return optionID;
	}

	public void setOptionID(int optionID) {
		this.optionID = optionID;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}	

}
