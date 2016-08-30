package com.example.ienning.ncuhome.adapter;

/**
 * Created by ienning on 16-8-30.
 */
public class ClassScores {
    private String lessonName;
    private String score;
    private String credit;

    public ClassScores(String lessonName, String score, String credit) {
        this.lessonName = lessonName;
        this.score = score;
        this.credit = credit;
    }
    public String getLessonName() {
        return lessonName;
    }

    public String getScore() {
        return score;
    }

    public String getCredit() {
        return credit;
    }

}
