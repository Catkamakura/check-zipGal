import java.io.File;
import java.util.Objects;

public class Md5Checker {


  public static boolean md5Check(File dir)  {
    File[] rarList;
    MD5[] md5List;
    rarList = InfoReader.getRarList(dir);
    md5List = InfoReader.getMD5List(dir);
    if (rarList.length != md5List.length) {
      System.out.println("Missing RAR file");
      return false;
    }
    for (int i = 0;i < md5List.length;i++)  {
      String rarMD5 = Md5CaculateUtil.getMD5(rarList[i]);
      String expectedMD5 = md5List[i].getMd5();
      if (!rarMD5.equalsIgnoreCase(expectedMD5)) {
        return false;
      }
    }
    return true;
  }

  // test
  public static void main(String[] args) {
    if (Md5Checker.md5Check(new File("E:/galgame/A048@Little Busters！EX"))) {
      System.out.println("test passed");
    }
  }
}
