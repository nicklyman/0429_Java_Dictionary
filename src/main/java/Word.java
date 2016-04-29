import java.util.ArrayList;

public class Word {
  private String mWord;
  private static ArrayList<Word> allWords = new ArrayList<Word>();
  private int mId;

  public Word(String userWord) {
    mWord = userWord;
    allWords.add(this);
    mId = allWords.size();
  }

  public String getWord() {
    return mWord;
  }

  public static ArrayList<Word> all() {
    return allWords;
  }

  public static void clear() {
    allWords.clear();
  }

  public int getId() {
    return mId;
  }
}
