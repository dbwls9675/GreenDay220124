package ExUrlResource01;
//URL에 접속하고 html정보 얻기
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Ex01Url {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://news.naver.com/");

		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(is);
		int data = 0;
		while ((data = dis.read()) != -1) {
			System.out.print((char) data);
		}

		if (dis != null)
			dis.close();
		if (is != null)
			is.close();
	}
}
