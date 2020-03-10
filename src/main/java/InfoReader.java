import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoReader {

  // gal目录可以没有rar文件，但一定要有README文件

  public static MD5[] getMD5List(File directory) {
    ArrayList<MD5> md5List = new ArrayList<>();
    try {
      if (!directory.exists()) {
        throw new FileNotFoundException("File Path Error");
      }
      if (!directory.isDirectory()) {
        throw new FileNotFoundException("The input path is not a directory");
      }
      File[] list = directory.listFiles(new MDFilenameFilter());
      if (list == null) {
        throw new FileNotFoundException("can't find README.md");
      }
      File readme = list[0];
      if (readme.isDirectory()) {
        throw new FileNotFoundException("There should not be a directory named README.*\\.md");
      }
      Scanner scr = new Scanner(readme);
      while (scr.hasNextLine()) {
        String content = scr.nextLine().trim();
        Matcher matcher = Pattern.compile("(.+)MD5[:：]([\\sa-zA-Z0-9]+)").matcher(content);
        if (matcher.find()) {
          md5List.add(new MD5(matcher.group(1).trim(),matcher.group(2).trim()));
        }
      }
      if (md5List.isEmpty()) {
        throw new NoSuchElementException("Can't find md5 in the README.md");
      }
    } catch (FileNotFoundException | NoSuchElementException e) {
      e.getMessage();
    }
    return md5List.toArray(new MD5[0]);
  }

  public static File[] getRarList(File directory) {
    File[] rarList = new File[0];
    try {
      if (!directory.exists()) {
        throw new FileNotFoundException("File Path Error");
      }
      if (!directory.isDirectory()) {
        throw new FileNotFoundException("The input path is not a directory");
      }
      rarList = directory.listFiles(new RarFilenameFilter());
      if (rarList == null)  {
        rarList = new File[0];
      }
      Arrays.sort(rarList,new FileComparator<>());
    } catch (FileNotFoundException e) {
      e.getMessage();
    }
    return rarList;
  }

  public static File[] getDir(File rootDir) {
    File[] dirList = null;
    try {
      if (!rootDir.exists()) {
        throw new FileNotFoundException("File Path Error");
      }
      if (!rootDir.isDirectory()) {
        throw new FileNotFoundException("The input path is not a directory");
      }
      dirList = rootDir.listFiles(new  DirFileFilter());
      if (dirList == null)  {
        throw new NoSuchElementException("no subdirectory in " + rootDir.getName());
      }
    } catch (FileNotFoundException e) {
      e.getMessage();
    }
    return dirList;
  }

  private static class MDFilenameFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
      return Pattern.compile("README.*\\.md").matcher(name).find();
    }
  }

  private static class RarFilenameFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
      return name.endsWith(".rar");
    }
  }

  private static class DirFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
      return pathname.isDirectory();
    }
  }

  private static class FileComparator<T> implements Comparator<T> {

    @Override
    public int compare(Object o1, Object o2) {
      File f1 = (File)o1;
      File f2 = (File)o2;
      return getNumber(f1) - getNumber(f2);
    }
  }

  public static Integer getNumber(File file) {
    String fileName = file.getName();
    Matcher matcher = Pattern.compile("\\.part([0-9]+)\\.rar").matcher(fileName);
    if (!matcher.find()) {
      return 0;
    } else {
      return Integer.parseInt(matcher.group(1).trim());
    }
  }

  // test
  public static void main(String[] args) {

    for (File f : getDir(new File("E:/galgame"))) {
      System.out.println(f.getPath());
      for (File r : getRarList(f)) {
        System.out.println(r.getPath());
      }
      for (MD5 m : getMD5List(f)) {
        System.out.println(m.getPart());
      }
    }
  }
}
