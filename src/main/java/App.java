import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("words/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/word-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/definitions", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("definitions", Definition.all());
    //   model.put("template", "templates/definitions.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    post("/words", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String userWord = request.queryParams("addWord");
      Word newWord = new Word(userWord);
      model.put("template", "templates/wordAdded.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/definitions/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Definition definition = Definition.find(Integer.parseInt(request.params(":id")));
    //   model.put("definition", definition);
    //   model.put("template", "templates/definition.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  }
}
