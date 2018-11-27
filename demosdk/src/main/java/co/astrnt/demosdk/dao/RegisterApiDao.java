package co.astrnt.demosdk.dao;

import com.google.gson.annotations.SerializedName;

public class RegisterApiDao extends BaseApiDao {

    @SerializedName("candidate_identifier")
    private String candidateIdentifier;

    @SerializedName("interview_code")
    private String interviewCode;

    @SerializedName("questions")
    private QuestionApiDao questions;

    public String getCandidateIdentifier() {
        return candidateIdentifier;
    }

    public void setCandidateIdentifier(String candidateIdentifier) {
        this.candidateIdentifier = candidateIdentifier;
    }

    public String getInterviewCode() {
        return interviewCode;
    }

    public void setInterviewCode(String interviewCode) {
        this.interviewCode = interviewCode;
    }

    public QuestionApiDao getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionApiDao questions) {
        this.questions = questions;
    }
}
