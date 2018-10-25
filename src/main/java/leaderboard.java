import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
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

            get("/", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("template", "/templates/home.vtl");
                return new ModelAndView(model, layout);
            }, new VelocityTemplateEngine());
    }

}