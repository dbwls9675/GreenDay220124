package ExUrlResource01;

//URL접속 후 이미지 복사 후 저장
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Ex02Url {

	public Ex02Url() throws IOException {
		String imgUrlPath = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA3MjJfMTI3%2FMDAxNjI2OTEyMzEwNDE2.xhG1D3Py9QL36ZiLWZOQ4wRHnPl2SpZclSyQjFbLL2Mg.cRUeLXswuSkUgVWUmLh1k4woSL6HWcw7su97AbR7MyUg.JPEG.sauooo%2F20210619%25A3%25DF195020.jpg&type=sc960_832";
		URL url = new URL(imgUrlPath);

		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(is);

		File dir = new File("./images");
		if (!dir.exists()) {
			if (dir.mkdir()) {
				System.out.println("새 디렉토리 생성 완료!");
			}
		}

		String saveFilePath = "photo.jpg";
		File file = new File(dir, saveFilePath);
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
		new Ex02Url();
	}
}
