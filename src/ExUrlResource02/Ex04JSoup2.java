package ExUrlResource02;
//Jsoup�� ����Ͽ� �̹��� ���ϸ� ���� �� ����
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//JSoup �ٿ� �� eclipse-workspace �ֱ� �� BuildPath �� ���̺귯���� Add External.jar���� �߰� 
public class Ex04JSoup2 {

	public void imgLoad(String imgName, String imgUrlPath) throws IOException {
		URL url = new URL(imgUrlPath);

		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(is);

		File dir = new File("./images");
		if (!dir.exists()) {
			if (dir.mkdir()) {
				System.out.println("�� ���丮 ���� �Ϸ�!");
			}
		}
		File file = new File(dir, imgName);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		int data = 0;
		while ((data = dis.read()) != -1) {
			// ���Ϸ� ���� �ϱ�
			dos.write(data);
		}

		if (dis != null)
			dis.close();
		if (is != null)
			is.close();
		if (dos != null)
			dos.close();
		if (fos != null)
			fos.close();
	}

	public Ex04JSoup2() throws IOException {
		String urlPath = "https://ko.wikipedia.org/wiki/%EA%B3%A0%EC%96%91%EC%9D%B4";
		Document doc = Jsoup.connect(urlPath).get();

		Elements lis = doc.select("#content img");
		for (Element el : lis) {
			String imgUrl = String.format("https:%s", el.attr("src"));
			String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
			if (imgName.lastIndexOf(".") != -1) {
				imgLoad(imgName, imgUrl);
				System.out.println(imgUrl);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Ex04JSoup2();
	}

}
