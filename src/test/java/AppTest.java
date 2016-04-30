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
    assertThat(pageSource()).contains("Add a new word");
  }

  @Test
  public void wordIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new word"));
    fill("#addWord").with("park");
    submit(".btn");
    assertThat(pageSource()).contains("Your word has been added to the dictionary.");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words/new");
    fill("#addWord").with("park");
    submit(".btn");
    click("a", withText("View words"));
    assertThat(pageSource()).contains("park");
  }

  @Test
  public void wordShowPageDisplaysWord() {
    goTo("http://localhost:4567/words/new");
    fill("#addWord").with("trail");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("trail"));
    assertThat(pageSource()).contains("trail");
  }

  // @Test
  // public void definitionNotFoundMessageShown() {
  //   goTo("http://localhost:4567/definitions/9999");
  //   assertThat(pageSource()).contains("Definition not found");
  // }
}
