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
    fill("#addWord").with("lake");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("lake"));
    assertThat(pageSource()).contains("lake");
  }

  @Test
  public void wordDefinitionsFormIsDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#addWord").with("park");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("park"));
    click("a", withText("Add a new definition"));
    assertThat(pageSource()).contains("Add a definition to the word park");
  }

  @Test
  public void definitionsIsAddedAndDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#addWord").with("park");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("park"));
    click("a", withText("Add a new definition"));
    fill("#addDefinition").with("An area designated for public recreation");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("park"));
    assertThat(pageSource()).contains("An area designated for public recreation");
  }

  // @Test
  // public void wordNotFoundMessageShown() {
  //   goTo("http://localhost:4567/words/9999");
  //   assertThat(pageSource()).contains("Word not found");
  // }
}
