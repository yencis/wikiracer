import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Article{
		Document doc;
		Elements links;
		int commonLinks;
		Article(Document d, Elements el, Comparator<Element> alphaComp){
			el.sort(alphaComp);
			doc = d;
			links = el;
		}
		
		public boolean contains(String url) {
			return linksSearch(links,url);
		}
		
		public void getCommonLinks(Article targetArticle) {
			try {
				commonLinks = WikiRacer.commonLinks(links,targetArticle.links);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				commonLinks = Integer.MIN_VALUE;
				e.printStackTrace();
			}
		}
		
		public boolean linksSearch(Elements links, String url) {
			int lower = 0;
			int upper = links.size()-1;
			int mid;
			if (links.get(upper).absUrl("href").compareTo(url)<0)return false;
			if (links.get(0).absUrl("href").compareTo(url)>0)return false;
			while(true) {
				//System.out.println(lower);
				mid = (int)Math.floor(((double)upper+(double)lower)/2);
				if(lower>=upper){
					return false;
				}else if (links.get(mid).absUrl("href").compareTo(url)==0) {
					return true;
				}else if (url.compareTo(links.get(mid).absUrl("href"))>0) {
					lower = mid+1;
				}else if (url.compareTo(links.get(mid).absUrl("href"))<0) {
					upper = mid;
				}
			}
		}
		
		public String toString() {
			return doc.location();
		}
		
		public String showLinks() {
			return links.toString();
		}
		
	}