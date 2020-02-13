import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiRacer {

	public static Article targetArticle;
	
	
	public static Document getDocument(String url) throws IOException {
		return Jsoup.connect(url).timeout(0).get();
	}
	
	public static Elements getLinks(Document doc) {
		return doc.select(".mw-parser-output").select("[href*=/wiki/]");
	}
	
	public static Ladder generateLadder(String start, String target) throws IOException {
		System.out.println("Generating Ladder...");
		Document targetDoc = getDocument(target);
		Elements links = getLinks(targetDoc);
		/*Document startDoc = getDocument(start);
		Elements startLinks = getLinks(startDoc);*/
		/*for (Element el: links) {
			System.out.println(el.absUrl("href"));
		}*/
		//links.sort(new alphaComp());
		//targetLinks = links;	
		
		//targetArticle = articlize(getDocument(target));
		
		targetArticle = new Article(targetDoc,links,new alphaComp());
		//Article startArticle = new Article(startDoc,startLinks, new alphaComp());
		//startArticle.getCommonLinks(targetArticle);
		Article startArticle = articlize(getDocument(start));
		PriorityQueue<Ladder> articles = new PriorityQueue<Ladder>(1,new commonComp());
		articles.add(new Ladder(startArticle));

		
		Ladder result = new Ladder(startArticle);
		
		int iteration = 0;
		while(!articles.isEmpty()) {
			Ladder ladder = articles.poll();
			Article curArt = ladder.peek();
			//System.out.println(curArt.doc.location());
			if (curArt.contains(targetArticle.doc.location())) {
				System.out.println("Found "+curArt);
				result = ladder.copyLadder();
				break;
			}
			Elements artLinks = curArt.links;
			for (Element el : artLinks) {
				String url = el.absUrl("href");
				System.out.println(url);
				Document d = getDocument(url);
				Article newArt = articlize(d);
				Ladder newL = ladder.copyLadder();
				newL.add(newArt);
				articles.add(newL);
			}
			iteration++;
			System.out.println("Finished "+iteration+" iteration");
		}
		
		System.out.println("Done");
		return result;
	}	
	
	public static Article articlize(Document doc) {
		Article article = new Article(doc,getLinks(doc),new alphaComp());
		article.getCommonLinks(targetArticle);
		return article;
	}
	
	public static int commonLinks(Elements a, Elements target) throws IOException{
		Set<String> seen = new HashSet<String>();
		int commonLinks = 0;
		Elements b = new Elements(a);
		for (Element el : b) {
			if (seen.contains(el.absUrl("href"))) {
				a.remove(el);
				continue;
			}else {
				seen.add(el.absUrl("href"));
			}
			int val = Collections.binarySearch(target, el, new alphaComp());
			if (val>0) {
				//System.out.println(el.absUrl("href"));
				commonLinks++;
			}
		}
		return commonLinks;
	}
	
	static class commonComp implements Comparator<Ladder>{

		public int compare(Ladder a, Ladder b) {
			// TODO Auto-generated method stub
			return -1*(a.peek().commonLinks-b.peek().commonLinks);
		}
		
	}
	
	static class alphaComp implements Comparator<Element>{

		public int compare(Element a, Element b) {
			return a.absUrl("href").compareTo(b.absUrl("href"));
		}
		
	}
	
	
	
}
