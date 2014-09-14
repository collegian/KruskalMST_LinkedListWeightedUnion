package kruskal;


   
   public final class Node
   {
	   private Node head;
	   private Node tail;
	   private char vertex;
	   
	   Node(Node head, Node tail, char vertex)
	   {
		   this.head = head;
		   this.tail = tail;
		   this.vertex = vertex;
	   }
	   
	   public char getVertexChar()
	   {
		   return vertex;
	   }
	   
	   public Node getHead()
	   {
		   return head;
	   }
	   
	   public Node getTail()
	   {
		   return tail;
	   }
	   
	   public void setHead(Node newHead)
	   {
		   this.head = newHead;
	   }
	   
	   public void setTail(Node newTail)
	   {
		   this.tail = newTail;
	   }
	   
	   public void setVertex(char vertex)
	   {
		   this.vertex = vertex;
	   }
   }
