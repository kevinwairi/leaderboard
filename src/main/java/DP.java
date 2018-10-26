import org.sql2o.Connection;

import java.util.List;

public class DP {
    private String fname;
    private String sname;
    private String uname;
    private String password;
    private String email;
    private String language;
    private String link;
    private String solution;

    public void setSearchlang(String searchlang) {
        this.searchlang = searchlang;
    }

    public String getSearchlang() {
        return searchlang;
    }

    private String searchlang;

    public void setMykatas(Integer mykatas) {
        this.mykatas = mykatas;
    }

    public Integer getMykatas() {
        return mykatas;
    }

    private Integer mykatas;

    public void setTitle(String title) {
        this.title = title;
    }

    private String time;

    public String getTitle() {
        return title;
    }

    private String title;
    private Integer upvote, downvote;

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLanguage() {
        return language;
    }

    public String getLink() {
        return link;
    }

    public String getSolution() {
        return solution;
    }

    public String getTime() {
        return time;
    }

    public Integer getUpvote() {
        return upvote;
    }

    public Integer getDownvote() {
        return downvote;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUpvote(Integer upvote) {
        this.upvote = upvote;
    }

    public void setDownvote(Integer downvote) {
        this.downvote = downvote;
    }



}
