import spark.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
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
            staticFileLocation("/public");
            enableDebugScreen();


            get("/", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("template", "/templates/login.vtl");
                return new ModelAndView(model, layout);
            }, new VelocityTemplateEngine());


//            post("/login",((request, response) ->{
//                Map<String,Object> model = new HashMap<String,Object>();
//
//                String adminuname = request.queryParams("userid");
//                hairdp.setUname(adminuname);
//
//                String stylistid = request.queryParams("userid");
//                hairdp.setStylistid(stylistid);
//
//                String password = request.queryParams("passw");
//                byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//                hairdp.setPassword(Arrays.toString(pass));
////            hb.save(hairdp);
//
//                String confirmadmin = HairSalonDB.adminval(hairdp);
//
//                String confirmstylist = HairSalonDB.stylistval(hairdp);
//                if (confirmadmin != null){
//                    model.put("template","/templates/stylistregform.vtl");
//                }else if(confirmstylist != null){
//                    model.put("stylstcust",HairSalonDB.stylistcustomers(hairdp));
//                    model.put("template","/templates/stylistpage.vtl");
//                }else{
//                    model.put("template","/templates/caution.vtl");
//                }
//                model.put("stylists",HairSalonDB.allstylist());
//                return new ModelAndView(model,layout);
//            }),new VelocityTemplateEngine());
        }

}