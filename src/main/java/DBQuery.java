import org.sql2o.Connection;

import java.util.List;

public class DBQuery {
    public static List<DP> all() {
        String sql = "SELECT email FROM email";
        try(Connection con = DB.moringa.open()) {
            return con.createQuery(sql).executeAndFetch(DP.class);
        }
    }


    public static String valemail(DP email) {
        try(Connection con = DB.moringa.open()) {
            String sql = "SELECT email FROM email where email=:email";
            String dp = con.createQuery(sql)
                    .addParameter("email", email.getEmail())
                    .executeScalar(String.class);
            return dp;
        }
    }

    public void save_to_moringa(DP myemail) {
        try (Connection connection = DB.moringa.open()) {
            String newdata = "INSERT INTO email(email)VALUES(:email)";
            connection.createQuery(newdata)
                    .addParameter("email", myemail.getEmail())
                    .executeUpdate();
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
                    dp.getUpvote().equals(otheruname);
        }
    }

}

