import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {
  // @After
  // public void tearDown() {
  //   Definition.clear();
  // }

  @Test
  public void definition_instantiatesCorrectly_true() {
    Definition testDefinition = new Definition("An area designated for public recreation");
    assertEquals(true, testDefinition instanceof Definition);
  }

  @Test
  public void definition_instantiatesCorrectlyReturningDefinition_String() {
    Definition testDefinition = new Definition("An area designated for public recreation");
    assertEquals("An area designated for public recreation", testDefinition.getDefinition());
  }

  @Test
  public void all_returnsAllInstancesOfDefinitions_true() {
    Definition testDefinitionOne = new Definition("An area designated for public recreation");
    Definition testDefinitionTwo = new Definition("The gear used in automatic transmissions when the vehicle is not being used");
    assertTrue(Definition.all().contains(testDefinitionOne));
    assertTrue(Definition.all().contains(testDefinitionTwo));
  }
}
