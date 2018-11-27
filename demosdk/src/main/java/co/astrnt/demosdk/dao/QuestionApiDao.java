package co.astrnt.demosdk.dao;

import com.google.gson.annotations.SerializedName;

public class QuestionApiDao {

    @SerializedName("title")
    private String title;
    @SerializedName("takesCount")
    private int takesCount;
    @SerializedName("maxTime")
    private String maxTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTakesCount() {
        return takesCount;
    }

    public void setTakesCount(int takesCount) {
        this.takesCount = takesCount;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }
}
