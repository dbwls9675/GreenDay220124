package ExUrlResource02;
//Jsoup을 사용하여 필요한 정보만 얻기
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//JSoup 다운 → eclipse-workspace 넣기 → BuildPath → 라이브러리에 Add External.jar에서 추가 
public class Ex03JSoup {
	
	public Ex03JSoup() throws IOException {
		String urlPath ="https://www.w3schools.com/css/css_selectors.asp";
		Document doc = Jsoup.connect(urlPath).get();
		System.out.println(doc.title());//창 타이틀 가져오기
		
		
		Elements lis = doc.select("#main ul li");
		for(Element el : lis) {
			System.out.printf("%s, %s\n",el.select("a[href]").attr(""),el.text());
		}
		
	}

	public static void main(String[] args) throws IOException {
		new Ex03JSoup();
		
	}

}
