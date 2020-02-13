import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*Document target = WikiRacer.getDocument("https://en.wikipedia.org/wiki/Hollywood_Boulevard");
		Elements links = WikiRacer.getLinks(target);
		for (Element el : links) {
			System.out.println(el.toString());
		}*/
		Ladder l = WikiRacer.generateLadder("https://en.wikipedia.org/wiki/Magnesium","https://en.wikipedia.org/wiki/Jerry_Brown");
		System.out.println(l);
		/*Document doc = WikiRacer.getDocument("https://en.wikipedia.org/wiki/Hollywood_Boulevard");
		Document testDoc = WikiRacer.getDocument("https://en.wikipedia.org/wiki/Charles_E._Toberman");
		Elements e = doc.select("[href*=/wiki/]");
		Elements e2 = testDoc.select("[href*=/wiki/]");
		e.sort(new WikiRacer.alphaComp());
		e2.sort(new WikiRacer.alphaComp());
		/*for (Element el : e) {
			System.out.println(el.absUrl("href"));
		}
		System.out.println(WikiRacer.commonLinks(e, e));*/
		
	}

}
