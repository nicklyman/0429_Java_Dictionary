import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {
  @After
  public void tearDown() {
    Word.clear();
    Definition.clear();
  }

  @Test
  public void word_instantiatesCorrectly_true() {
    Word testWord = new Word("park");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  public void word_instantiatesCorrectlyReturningWord_String() {
    Word testWord = new Word("park");
    assertEquals("park", testWord.getWord());
  }

  @Test
  public void all_returnsAllInstancesOfWords_true() {
    Word testWordOne = new Word("park");
    Word testWordTwo = new Word("trail");
    assertTrue(Word.all().contains(testWordOne));
    assertTrue(Word.all().contains(testWordTwo));
  }

  @Test
  public void clear_emptiesArrayListOfWords_0() {
    Word testWord = new Word("park");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void getId_returnsUniqueIdForEachWord_1() {
    Word testWord = new Word("park");
    assertEquals(1, testWord.getId());
  }

  @Test
  public void find_returnsWordWithAccurateId_testWordTwo() {
    Word testWordOne = new Word("park");
    Word testWordTwo = new Word("trail");
    assertEquals(Word.find(testWordTwo.getId()), testWordTwo);
  }

  @Test
  public void find_returnsNullIfWordNotFound_null() {
    assertTrue(Word.find(9999) == null);
  }

  @Test
  public void getDefinitions_initiallyReturnsEmptyList_ArrayList() {
    Word testWord = new Word("play");
    assertEquals(0, testWord.getDefinitions().size());
  }
}
