import org.sql2o.Connection;

import java.util.List;

public class DP {
    private String fname, sname, uname, password, email, language, link, solution, time;
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
