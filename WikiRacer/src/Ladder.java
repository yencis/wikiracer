import java.util.*;
import org.jsoup.nodes.*;
public class Ladder{
	//private Queue<Article> q;
	private Stack<Article> q;
	private Article temp;
	
	public Ladder(Article a) {
		//q = new LinkedList<Article>();
		q = new Stack<Article>();
		q.add(a);
		//s.add(a);
		temp = a;
	}
	
	@SuppressWarnings("unchecked")
	public Ladder copyLadder() {
		Ladder newL = new Ladder(temp);
		//newL.q = new LinkedList<Article>(q);
		newL.q = (Stack<Article>) q.clone();
		return newL;
	}
	
	public void add(Article d) {
		q.add(d);
		//s.add(d);
	}
	
	public Article poll() {
		return q.pop();
		//return q.poll();
		
	}
	
	public Article peek() {
		return q.peek();
		//return q.peek();
	}

	public String toString() {
		return q.toString();
	}

	
}
