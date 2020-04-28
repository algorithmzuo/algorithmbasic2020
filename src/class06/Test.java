package class06;

public class Test {

	public static class Node{
		public int value;
		public Node next;
		public Node(int v) {
			value = v;
		}
	}
	
	public static void main(String[] args) {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		
		a.next = b;
		b.next = c;
		
		c = null;
		
	}
	
	
}
