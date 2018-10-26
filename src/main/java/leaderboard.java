import spark.ModelAndView;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;


public class leaderboard {
        public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
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
                    System.out.println("Email does not exist in moringa DB.java. Check with your school administration");
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
                String df = request.session().attribute("username");
                dp.setUname(df);
                model.put("mylnk",DBQuery.fetch_link(dp));
                model.put("username",df);
                model.put("template","/templates/home.vtl");
                return new ModelAndView(model,layout);
            },new VelocityTemplateEngine());


// insert kata data
            post("/kata",(request,response)->{
                Map<String, Object> model = new HashMap<String, Object>();
                    String uname = request.queryParams("uname");
                    dp.setUname(uname);
                    String selectLanguage = request.queryParams("selectLanguage");
                    dp.setLanguage(selectLanguage);
                    String link = request.queryParams("link");
                    dp.setLink(link);
                    //fetch title from the link below code
                    Document doc = Jsoup.connect(dp.getLink()).get();
                    String title = doc.title();
                    dp.setTitle(title);
                    String solution = request.queryParams("solution");
                    dp.setSolution(solution);
                    String time = request.queryParams("time");
                    dp.setTime(time);

                    Integer addnum = 1;
                    dp.setMykatas(addnum);

                    if(title.equals(DBQuery.valtitle(dp))){
                        System.out.println("same");
                        dbQuery.updatekata(dp);
                    }else{
                        System.out.println("not same");
                        dbQuery.save_to_kata(dp);
                    }

                    model.put("username",request.session().attribute("username"));
                    response.redirect("/getsession");
                return new ModelAndView(model, layout);
            },new VelocityTemplateEngine());

            get("/fetchlink",(request,response)->{
                Map<String,Object> model = new HashMap<String,Object>();

                return new ModelAndView(model,layout);
            },new VelocityTemplateEngine());

        }

}