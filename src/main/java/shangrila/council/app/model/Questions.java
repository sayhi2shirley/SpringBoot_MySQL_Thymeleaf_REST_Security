package shangrila.council.app.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Questions")
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="questionid")
	private int questionID;

	@Column(name="questiontext", nullable=false, unique=true)
	private String QuestionText;

	@OneToMany(mappedBy = "question")
	@JsonManagedReference
	private List<Options> options = new ArrayList<>();


	public Questions(int questionID, String questionText, List<Options> options) {
		super();
		this.questionID = questionID;
		QuestionText = questionText;
		this.options = options;
	}

	public Questions() { }

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return QuestionText;
	}

	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}

}
