import java.io.File;
import java.io.IOException;

import jodd.io.FileUtil;
import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;
import jodd.lagarto.dom.Node;

public class SpringChmParse {
  static final String CF = "\r\n";
  static final StringBuilder sb = new StringBuilder(1 * 1024 * 1024);
  static int _level = 0;
  static int _pos = 0;

  public static void main(String[] args) throws IOException {
    File file = new File("Spring3.2Catalog.html");

    long startTime = System.currentTimeMillis();
    Jerry doc = Jerry.jerry(FileUtil.readString(file, "gb2312"));

    // parse
    doc.$("div>dl>dt").each(new JerryFunction() {
      public boolean onNode(Jerry $this, int index) {
        String title = $this.$("a").text().replaceAll("(\r\n|\r|\n|\n\r)", "");
        String href = $this.$("a").attr("href");
        sb.append("TitleList.Title." + _pos + "=" + title).append(CF);
        sb.append("TitleList.Level." + _pos + "=" + _level).append(CF);
        sb.append("TitleList.Url." + _pos + "=" + href).append(CF);
        sb.append("TitleList.Icon." + _pos + "=" + "0").append(CF);
        sb.append("TitleList.Status." + _pos + "=" + "0").append(CF);
        sb.append("TitleList.Keywords." + _pos + "=" + "").append(CF);
        sb.append("TitleList.ContextNumber." + _pos + "=" + "999" + _pos).append(CF);
        sb.append("TitleList.ApplyTemp." + _pos + "=" + "0").append(CF);
        sb.append("TitleList.Expanded." + _pos + "=" + "0").append(CF);
        sb.append("TitleList.Kind." + _pos + "=" + "0").append(CF);

        Jerry jerryDD = $this.next();
        if (jerryDD.get(0) != null && jerryDD.get(0).getNodeName().equals("dd")) {
          _pos = enumDD(jerryDD.get(0), _level + 1, _pos);
        }
        return true;
      }
    });

    sb.insert(0, "TitleList=" + (_pos + 1) + CF);
    sb.append(CF);
    FileUtil.writeString("xxx.txt", sb.toString());

    System.out.println("use time:" + (System.currentTimeMillis() - startTime) + "∫¡√Î!");
  }

  static int enumDD(Node nodeDD, int level, int pos) {
    Node[] dldts = nodeDD.getChildElement(0).getChildElements();
    for (Node dldt : dldts) {
      if (!dldt.getNodeName().equals("dt")) {
        continue;
      }

      pos++;

      String title = dldt.getTextContent().replaceAll("(\r\n|\r|\n|\n\r)", "");
      String href = dldt.getFirstChild().getFirstChild().getAttribute("href");
      sb.append("TitleList.Title." + pos + "=" + title).append(CF);
      sb.append("TitleList.Level." + pos + "=" + level).append(CF);
      sb.append("TitleList.Url." + pos + "=" + href).append(CF);
      sb.append("TitleList.Icon." + pos + "=" + "0").append(CF);
      sb.append("TitleList.Status." + pos + "=" + "0").append(CF);
      sb.append("TitleList.Keywords." + pos + "=" + "").append(CF);
      sb.append("TitleList.ContextNumber." + pos + "=" + "999" + pos).append(CF);
      sb.append("TitleList.ApplyTemp." + pos + "=" + "0").append(CF);
      sb.append("TitleList.Expanded." + pos + "=" + "0").append(CF);
      sb.append("TitleList.Kind." + pos + "=" + "0").append(CF);

      Node nextSibling = dldt.getNextSibling();
      if (nextSibling != null && nextSibling.getNodeName().equals("dd")) {
        pos = enumDD(nextSibling, level + 1, pos);
      }
    }

    return pos;
  }
}