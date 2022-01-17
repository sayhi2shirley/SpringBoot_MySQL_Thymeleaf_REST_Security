package shangrila.council.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "response")
public class Response {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="responseid")
	private int ResponseID;

	@Column(name="userid", nullable=false)
	private String UserID;
	
	@Column(name="optionid", nullable=false)
    private int ChosenOptionID;
	
	@Column(name="questionid", nullable=false)
    private int QuestionID;

	@Column(name="count", nullable=false)
	private int count;

	public Response() {

	}
	
	public Response(int responseID, String userID, int chosenOptionID, int questionID, int count) {
		super();
		ResponseID = responseID;
		UserID = userID;
		ChosenOptionID = chosenOptionID;
		QuestionID = questionID;
		this.count = count;
	}
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getResponseID() {
		return ResponseID;
	}

	public void setResponseID(int responseID) {
		ResponseID = responseID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public int getChosenOptionID() {
		return ChosenOptionID;
	}

	public void setChosenOptionID(int chosenOptionID) {
		ChosenOptionID = chosenOptionID;
	}

	public int getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(int questionID) {
		QuestionID = questionID;
	}
	
	
	
}
