package com.interviewManagementApplication.RMS.dto;

import java.util.Date;

public class FeedbackDTO {

    private int feedback_ID;
    private int interviewID;
    private int interviewer_ID;
    private String comment;
    private int overallRating;
    private boolean decisionOnHire;
    private Date feedbackDate;

    public FeedbackDTO(int feedback_ID, int interviewID, int interviewer_ID, String comment, int overallRating, boolean decisionOnHire, Date feedbackDate) {
        this.feedback_ID = feedback_ID;
        this.interviewID = interviewID;
        this.interviewer_ID = interviewer_ID;
        this.comment = comment;
        this.overallRating = overallRating;
        this.decisionOnHire = decisionOnHire;
        this.feedbackDate = feedbackDate;
    }

    public int getFeedback_ID() {
        return feedback_ID;
    }

    public void setFeedback_ID(int feedback_ID) {
        this.feedback_ID = feedback_ID;
    }

    public int getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(int interviewID) {
        this.interviewID = interviewID;
    }

    public int getInterviewer_ID() {
        return interviewer_ID;
    }

    public void setInterviewer_ID(int interviewer_ID) {
        this.interviewer_ID = interviewer_ID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public boolean isDecisionOnHire() {
        return decisionOnHire;
    }

    public void setDecisionOnHire(boolean decisionOnHire) {
        this.decisionOnHire = decisionOnHire;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
