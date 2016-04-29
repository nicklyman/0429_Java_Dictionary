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

    get("definitions/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/definition-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/definitions", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("definitions", Definition.all());
    //   model.put("template", "templates/definitions.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    post("/definitions", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String userDefinition = request.queryParams("userDefinition");
      Definition newDefinition = new Definition(userDefinition);
      model.put("template", "templates/definitionAdded.vtl");
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
