import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;

public class leaderboard {
        public static void main(String[] args) {
            ProcessBuilder process = new ProcessBuilder();
            Integer port;
            if (process.environment().get("PORT") != null) {
                port = Integer.parseInt(process.environment().get("PORT"));
            } else {
                port = 4567;
            }
            setPort(port);
            String layout = "templates/layout.vtl";
            staticFileLocation ("/public");
            enableDebugScreen();
    }

}