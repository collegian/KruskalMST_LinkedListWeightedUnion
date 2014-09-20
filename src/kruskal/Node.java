package kruskal;

/**
 * The Node representation. Each node contains a pointer to the SetObject, a pointer to the next node and a character to store the vertex.
 * @author Rahul
 */
//TODO: I need a better representation than a char for each vertex since char is too small.
public final class Node 
{
	private SetObject head;
	private Node tail;
	private char vertex;

	public static Node createNode(SetObject head, Node tail, char vertex) 
	{
		return new Node(head, tail, vertex);
	}

	private Node(SetObject head, Node tail, char vertex) 
	{
		this.head = head;
		this.tail = tail;
		this.vertex = vertex;
	}

	public char getVertexChar() 
	{
		return vertex;
	}

	public SetObject getHead() 
	{
		return head;
	}

	public Node getTail() 
	{
		return tail;
	}

	public void setHead(SetObject newHead) 
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
