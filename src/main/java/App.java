import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

       get("/", (request,response)->{
           Map<String, Object> model = new HashMap<>();
           return new ModelAndView(model, "index.hbs");
       }, new HandlebarsTemplateEngine());

       post("/heroes", (request,response)-> {
           Map<String, ArrayList<Hero>> model = new HashMap<>();
           String name = request.queryParams("name");
           request.session().attribute("name",name);
           int age = Integer.parseInt(request.queryParams("age"));
           request.session().attribute("age",age);
           String weakness = request.queryParams("weakness");
           request.session().attribute("weakness",weakness);
           String strength = request.queryParams("strength");
           request.session().attribute("strength",strength);
           Hero newHero = new Hero(name,age,weakness,strength);

           //get all created heroes and display them and their stats
           ArrayList allHeroes = Hero.getAllHeroes();
           model.put("myHeroes", allHeroes);
           return new ModelAndView(model,"heroes.hbs");
       },new HandlebarsTemplateEngine());
    }
}
