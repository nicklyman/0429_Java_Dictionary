import java.util.ArrayList;

public class Definition {
  private String mDefinition;
  private static ArrayList<Definition> allDefinitions = new ArrayList<Definition>();
  private int mId;

  public Definition(String userDefinition) {
    mDefinition = userDefinition;
    allDefinitions.add(this);
  }

  public String getDefinition() {
    return mDefinition;
  }

  public static ArrayList<Definition> all() {
    return allDefinitions;
  }

  public static void clear() {
    allDefinitions.clear();
  }
}
