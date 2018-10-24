import org.sql2o.Connection;

import java.util.List;

public class DP  {
    private String fname,sname,uname,password,email,language,link,solution,time;
    private Integer upvote,downvote;

    public DP(String email) {
        this.email = email;
    }

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

    //select all email from moringa database;

    public static List<DP> all() {
        String sql = "SELECT email FROM email";
        try(Connection con = DB.moringa.open()) {
            return con.createQuery(sql).executeAndFetch(DP.class);
        }
    }


//public static DP find(String email) {
//    try(Connection con = DB.moringa.open()) {
//        String sql = "SELECT email FROM email where email=:email";
//        DP dp = con.createQuery(sql)
//                .addParameter("email", email)
//                .executeAndFetchFirst(DP.class);
//        return dp;
//    }
//}

    public void save_to_moringa() {
        try (Connection connection = DB.moringa.open()) {
            String newdata = "INSERT INTO email(email)VALUES(:email)";
            connection.createQuery(newdata)
                    .addParameter("email", this.email)
                    .executeUpdate();
        }
    }

    @Override
    public boolean equals(Object otherTask) {
        if (!(otherTask instanceof DP)) {
            return false;
        } else {
            DP newTask = (DP) otherTask;
            return this.getEmail().equals(newTask.getEmail())
                    ;
        }
    }

//    @Override
//    public boolean equals(Object otherTask) {
//        if (!(otherTask instanceof DP)) {
//            return false;
//        } else {
//            DP newTask = (DP) otherTask;
//            return this.getFname().equals(newTask.getFname())&&
//                    this.getSname().equals( newTask.getSname())&&
//                    this.getUname().equals(newTask.getUname())&&
//                    this.getPassword().equals(newTask.getPassword())&&
//                    this.getEmail().equals(newTask.getEmail())&&
//                    this.getLanguage().equals(newTask.getLanguage())&&
//                    this.getLink().equals(newTask.getLink())&&
//                    this.getSolution().equals(newTask.getSolution())&&
//                    this.getUpvote().equals( newTask.getUpvote())&&
//                    this.getDownvote().equals(newTask.getDownvote())&&
//                    this.getTime().equals( newTask.getTime());
//
//        }
//    }
}
