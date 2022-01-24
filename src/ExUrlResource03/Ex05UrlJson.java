package ExUrlResource03;
//JSON 파일을 읽어 필요한 필요한 정보만 추출 후 swing으로 보여주기
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
	public JLabel statusLbl = new JLabel("Status : 준비중입니다.");

	public Ex05UrlJson() throws IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		// 1. 미리 12개의 JLabel과 ImageIcon을 CenterPane에 준비한다.
		// 2. 이미지가 로드되는 순서대로 보여지게 한다.
		contentPane = this.getContentPane();
		contentPane.add(new JScrollPane(centerPane));
		for (int i = 0; i < SIZE; i++) {
			ImageIcon imgIco = new ImageIcon();
			lblList.add(new JLabel(imgIco));
			centerPane.add(lblList.get(i));
			imgIcoList.add(imgIco);
		}
		setVisible(true);
		// 이미지 불러오기
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

			// for문이 1회전 할때 마다 이미지 하나가 저장 됨.
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
				System.out.println("새 디렉토리 생성 완료!");
			}
		}
		
		File file = new File(dir, imgName);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		int data = 0;
		
		while ((data = dis.read()) != -1) {
			// 파일로 저장 하기
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