import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Your Dictionary");
  }

  @Test
  public void definitionIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new definition"));
    fill("#addDefinition").with("An area designated for public recreation");
    submit(".btn");
    assertThat(pageSource()).contains("Your definition has been added to the dictionary.");
  }

  @Test
  public void definitionIsDisplayedTest() {
    goTo("http://localhost:4567/definitions/new");
    fill("#addDefinition").with("An area designated for public recreation");
    submit(".btn");
    click("a", withText("View definitions"));
    assertThat(pageSource()).contains("An area designated for public recreation");
  }

  // @Test
  // public void multipleDefinitionsAreDisplayedTest() {
  //   goTo("http://localhost:4567/definitions/new");
  //   fill("#addDefinition").with("An area designated for public recreation");
  //   submit(".btn");
  //   goTo("http://localhost:4567/definitions/new");
  //   fill("#addDefinition").with("The gear used in automatic transmissions when the vehicle is not being used");
  //   submit(".btn");
  //   click("a", withText("View definitions"));
  //   assertThat(pageSource()).contains("An area designated for public recreation");
  //   assertThat(pageSource()).contains("The gear used in automatic transmissions when the vehicle is not being used");
  // }
  //
  // @Test
  // public void definitionShowPageDisplaysDescription() {
  //   goTo("http://localhost:4567/definitions/new");
  //   fill("#addDefinition").with("An area designated for public recreation");
  //   submit(".btn");
  //   click("a", withText("View definitions"));
  //   click("a", withText("An area designated for public recreation"));
  //   assertThat(pageSource()).contains("An area designated for public recreation");
  // }
  //
  // @Test
  // public void definitionNotFoundMessageShown() {
  //   goTo("http://localhost:4567/definitions/9999");
  //   assertThat(pageSource()).contains("Definition not found");
  // }
}
