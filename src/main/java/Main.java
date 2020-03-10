import java.io.File;

public class Main {

  // 一键check MD5 + 压缩
  public static void main(String[] args) {
    File rootDir = new File("E:/galgame");
    for (File gal : InfoReader.getDir(rootDir)) {
      System.out.println("we are at" + gal.getName());
      if (Md5Checker.md5Check(gal)) {
        System.out.println("MD5 checked");
        new Zipper(new File(rootDir, gal.getName() + ".zip")).zipFiles(gal);
      }
    }
  }

  // 仅check MD5，后自行使用 “批量压缩.bat” 进行压缩
  public static void onlyCheckMD5(String[] args) {
    File rootDir = new File("E:/galgame");
    for (File gal : InfoReader.getDir(rootDir)) {
      System.out.println("we are at" + gal.getName());
      if (Md5Checker.md5Check(gal)) {
        System.out.println("MD5 checked");
      } else {
        System.out.println("MD5 check at" + gal.getName() + " failed");
      }
    }
  }

}
