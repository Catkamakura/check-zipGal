import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MD5 {
  private Integer part;
  private String md5;
  private static Pattern nonFigure = Pattern.compile("[^0-9]");

  public MD5(String partName, String md5) {
    if (partName.contains("文件"))  {
      part = 0;
    } else {
      this.part = Integer.parseInt(nonFigure.matcher(partName).replaceAll("").trim());
    }
    this.md5 = md5;
  }

  public Integer getPart() {
    return part;
  }

  public String getMd5() {
    return md5;
  }
}
