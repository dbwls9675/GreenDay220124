package ExUrlResource03;
//JSON ������ �о� �ʿ��� �ʿ��� ������ ���� �� swing���� �����ֱ�
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import org.json.*;

public class Ex05UrlJson extends JFrame {
	public static final int SIZE = 12;
	public static final ArrayList<String> thumbnailUrlList = new ArrayList<String>();
	public static final ArrayList<String> thumbnailNameList = new ArrayList<String>();
	public static final ArrayList<ImageIcon> imgIcoList = new ArrayList<ImageIcon>();
	public static final ArrayList<JLabel> lblList = new ArrayList<JLabel>();

	public JPanel centerPane = new JPanel(new GridLayout(4, 3));
	public Container contentPane;
	public JLabel statusLbl = new JLabel("Status : �غ����Դϴ�.");

	public Ex05UrlJson() throws IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		// 1. �̸� 12���� JLabel�� ImageIcon�� CenterPane�� �غ��Ѵ�.
		// 2. �̹����� �ε�Ǵ� ������� �������� �Ѵ�.
		contentPane = this.getContentPane();
		contentPane.add(new JScrollPane(centerPane));
		for (int i = 0; i < SIZE; i++) {
			ImageIcon imgIco = new ImageIcon();
			lblList.add(new JLabel(imgIco));
			centerPane.add(lblList.get(i));
			imgIcoList.add(imgIco);
		}
		setVisible(true);
		// �̹��� �ҷ�����
		init();
	}

	public void init() throws IOException {
		URL jsonUrl = new URL("https://jsonplaceholder.typicode.com/photos");

		JSONTokener jsonTk = new JSONTokener(jsonUrl.openStream());
		JSONArray jsonArr = new JSONArray(jsonTk);
		// System.out.println("json arr size : " + jsonArr.length());

		for (int i = 0; i < SIZE; i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			// System.out.println(i+". thumbnailUrl : "+jsonObj.get("thumbnailUrl"));
			String thumburl = (String) jsonObj.get("thumbnailUrl");
			String thumbName = (String) thumburl.substring(thumburl.lastIndexOf("/") + 1);
			thumbnailUrlList.add(thumburl);
			thumbnailNameList.add(thumbName);

			// for���� 1ȸ�� �Ҷ� ���� �̹��� �ϳ��� ���� ��.
			String thumbDir = "./photo";
			File thumbFile = new File(thumbDir, thumbName);
			if (!thumbFile.exists()) {
				saveImage(thumbName, thumburl, thumbDir);
			}
			//imgIcoList.get(i).setImage(new ImageIcon(thumbFile.getPath()).getImage());
			ImageIcon ico = new ImageIcon(thumbFile.getPath());
			lblList.get(i).setIcon(ico);
			imgIcoList.add(ico);
		}
	}

	private void saveImage(String imgName, String imgUrlPath, String dirPath) throws IOException {
		URL url = new URL(imgUrlPath);
		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(is);
		File dir = new File(dirPath);
		
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

	public static void main(String[] args) throws IOException {
		new Ex05UrlJson();
	}
}