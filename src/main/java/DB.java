import org.sql2o.Sql2o;

public class DB {
    public static Sql2o moringa = new Sql2o("jdbc:postgresql://localhost:5432/moringa","arnold","arnold1234");
    public static Sql2o leaderboard = new Sql2o("jdbc:postgresql://localhost:5432/leaderboard","arnold","arnold1234");
}
