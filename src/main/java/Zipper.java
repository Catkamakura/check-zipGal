import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {


  File targetFile;

  public Zipper() {
  }

  public Zipper(File target) {
    targetFile = target;
    if (targetFile.exists()) {
      targetFile.delete();
    }
  }

  //测试
  public static void main(String[] args) {
    File f = new File("E:/workspace");
    new Zipper(new File("D:/", f.getName() + ".zip")).zipFiles(f);
  }


  public void zipFiles(File srcfile) {

    ZipOutputStream out = null;
    try {
      out = new ZipOutputStream(new FileOutputStream(targetFile));

      if (srcfile.isFile()) {
        zipFile(srcfile, out, "");
      } else {
        File[] list = srcfile.listFiles();
        for (int i = 0; i < list.length; i++) {
          compress(list[i], out, "");
        }
      }

      System.out.println("压缩完毕");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void compress(File file, ZipOutputStream out, String basedir) {
    /* 判断是目录还是文件 */
    if (file.isDirectory()) {
      this.zipDirectory(file, out, basedir);
    } else {
      this.zipFile(file, out, basedir);
    }
  }

  public void zipFile(File srcfile, ZipOutputStream out, String basedir) {
    if (!srcfile.exists()) {
      return;
    }

    byte[] buf = new byte[1024];
    FileInputStream in = null;

    try {
      int len;
      in = new FileInputStream(srcfile);
      out.putNextEntry(new ZipEntry(basedir + srcfile.getName()));

      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.closeEntry();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void zipDirectory(File dir, ZipOutputStream out, String basedir) {
    if (!dir.exists()) {
      return;
    }
    File[] files = dir.listFiles();
    assert files != null;
    for (int i = 0; i < files.length; i++) {
      /* 递归 */
      compress(files[i], out, basedir + dir.getName() + "/");
    }
  }

}
