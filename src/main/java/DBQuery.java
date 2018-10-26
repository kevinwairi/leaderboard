import org.sql2o.Connection;

import java.util.List;

public class DBQuery {
    public static String profileval(DP email) {
        try(Connection con = DB.leaderboard.open()) {
            String sql = "SELECT uname,email FROM profile where email=:email or uname=:uname";
            String dp = con.createQuery(sql)
                    .addParameter("email", email.getEmail())
                    .addParameter("uname", email.getUname())
                    .executeScalar(String.class);
            return dp;
        }
    }

//validate email exist in moringa DB.java

    public static String valemail(DP email) {
        try(Connection con = DB.moringa.open()) {
            String sql = "SELECT email FROM email where email=:email";
            String dp = con.createQuery(sql)
                    .addParameter("email", email.getEmail())
                    .executeScalar(String.class);
            return dp;
        }
    }
    //validate username and password

    public static String valunameandpass(DP email) {
        try(Connection con = DB.leaderboard.open()) {
            String sql = "SELECT uname,password FROM profile where uname=:uname and password=:password";
            String dp = con.createQuery(sql)
                    .addParameter("uname", email.getUname())
                    .addParameter("password", email.getPassword())
                    .executeScalar(String.class);
            return dp;
        }
    }

    //uncomment to save to moringa database
//    public void save_to_moringa(DP myemail) {
//        try (Connection connection = DB.java.moringa.open()) {
//            String newdata = "INSERT INTO email(email)VALUES(:email)";
//            connection.createQuery(newdata)
//                    .addParameter("email", myemail.getEmail())
//                    .executeUpdate();
//        }
//    }

    //save data to profile
public void save_to_leaderboard(DP myprofile) {
    try (Connection connection = DB.leaderboard.open()) {
        String newdata = "INSERT INTO profile(fname,sname,uname,password,email)VALUES(:fname,:sname,:uname,:password,:email)";
        connection.createQuery(newdata)
                .addParameter("fname", myprofile.getFname())
                .addParameter("sname", myprofile.getSname())
                .addParameter("uname", myprofile.getUname())
                .addParameter("password", myprofile.getPassword())
                .addParameter("email", myprofile.getEmail())
                .executeUpdate();
    }
}
    public void save_to_kata(DP katadata) {
        try(Connection con = DB.leaderboard.open()) {
            String sql = "INSERT INTO kata (uname, language, link, solution,time,title,mykatas) VALUES (:uname, :language, :link, :solution,:time,:title,:mykatas);";
            con.createQuery(sql)
                    .addParameter("uname", katadata.getUname())
                    .addParameter("language", katadata.getLanguage())
                    .addParameter("link", katadata.getLink())
                    .addParameter("solution", katadata.getSolution())
                    .addParameter("time", katadata.getTime())
                    .addParameter("title", katadata.getTitle())
                    .addParameter("mykatas", katadata.getMykatas())
                    .executeUpdate();
        }
    }
    public void updatekata(DP upkata) {
        try(Connection con = DB.leaderboard.open()) {
            String sql = "UPDATE kata SET solution =:solution,time=:time WHERE title =:title";
            con.createQuery(sql)
                    .addParameter("solution", upkata.getSolution())
                    .addParameter("time", upkata.getTime())
                    .addParameter("title", upkata.getTitle())
                    .executeUpdate();
        }
    }
    public static List<DP> fetch_link(DP title){
        String sql = "SELECT uname,language,link,solution,title FROM kata where uname=:uname";
        try(Connection con = DB.leaderboard.open()) {
            return con.createQuery(sql)
                    .addParameter("uname", title.getUname())
                    .executeAndFetch(DP.class);
        }
    }
    public static String valtitle(DP valtitle) {
        try(Connection con = DB.leaderboard.open()) {
            String sql = "SELECT title FROM kata where uname=:uname";
            String dp = con.createQuery(sql)
                    .addParameter("uname", valtitle.getUname())
                    .executeScalar(String.class);
            return dp;
        }
    }
    @Override
    public boolean equals(Object otheruname){
        if (!(otheruname instanceof DP)){
            return false;
        }else{
            DP dp = (DP) otheruname;
            return  dp.getFname().equals(otheruname)&&
                    dp.getSname().equals(otheruname)&&
                    dp.getEmail().equals(otheruname)&&
                    dp.getDownvote().equals(otheruname)&&
                    dp.getPassword().equals(otheruname)&&
                    dp.getUname().equals(otheruname)&&
                    dp.getLink().equals(otheruname)&&
                    dp.getUpvote().equals(otheruname);
        }
    }

}

