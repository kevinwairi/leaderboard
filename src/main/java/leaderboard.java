import spark.ModelAndView;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;


public class leaderboard {
        public static void main(String[] args) throws NoSuchAlgorithmException{
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
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            DP dp = new DP();
            DBQuery dbQuery = new DBQuery();


            get("/", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("template", "/templates/login.vtl");
                return new ModelAndView(model, layout);
            }, new VelocityTemplateEngine());

            post("/validatemail",(request,response)->{
                Map<String, Object> model = new HashMap<String, Object>();
                String emailval = request.queryParams("emailval");
                dp.setEmail(emailval);
                String myemail = DBQuery.valemail(dp);
                if(myemail != null){
                    model.put("template", "/templates/signUp.vtl");
                    //                dbQuery.save_to_moringa(dp);
                }else{
                    //enter code for caution if email does not exist
                    System.out.println("Email does not exist in moringa DB. Check with your school administration");
                }
                return new ModelAndView(model, layout);
            },new VelocityTemplateEngine());

                post("/profile",(request,response)->{
                Map<String, Object> model = new HashMap<String, Object>();
               try{
                   String fname = request.queryParams("fname");
                   dp.setFname(fname);
                   String sname = request.queryParams("sname");
                   dp.setSname(sname);
                   String uname = request.queryParams("uname");
                   dp.setUname(uname);
                   String password = request.queryParams("password");
                   byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                   dp.setPassword(Arrays.toString(pass));
                   String email = request.queryParams("email");
                   dp.setEmail(email);

                   String myemail = DBQuery.valemail(dp);

                   if(myemail != null){
                       String valdata = DBQuery.profileval(dp);
                       System.out.println(valdata);
                       if(valdata == null){
                           dbQuery.save_to_leaderboard(dp);
                           response.redirect("/");
//                           System.out.println("The email or username does not exist");
                       }else{

//                           System.out.println("The email or username exist");
                       }
                   }else{
                       //enter code for caution if email does not exist
                       System.out.println("You have enterd the wrong email");
                   }
               }
               catch(Exception e){
                   System.out.println();
               }
                return new ModelAndView(model, layout);
            },new VelocityTemplateEngine());


            post("/login",(request,response)->{
                Map<String, Object> model = new HashMap<String, Object>();

                String uname = request.queryParams("uname");
                dp.setUname(uname);
                request.session().attribute("username",dp.getUname());
                String password = request.queryParams("passw");
                dp.setPassword(password);
                byte[] pass = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                dp.setPassword(Arrays.toString(pass));
                String checklogin = DBQuery.valunameandpass(dp);
                System.out.println(checklogin);
                //validate username and password matches
                if(checklogin != null){
                 response.redirect("/getsession");
//                    System.out.println("Username and password exist");
                }else{
                    response.redirect("/");
//                    System.out.println("username or password does not exist");
                }
                return new ModelAndView(model, layout);
            },new VelocityTemplateEngine());

            get("/getsession",(request,respond)->{
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("username",request.session().attribute("username"));
                model.put("template","/templates/home.vtl");
                return new ModelAndView(model,layout);
            },new VelocityTemplateEngine());

        }

}