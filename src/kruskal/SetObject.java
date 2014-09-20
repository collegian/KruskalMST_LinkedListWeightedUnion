package kruskal;

/**
 * The SetObject of each Set. This contains the length of the set and pointers to the head and tail of the list.
 * @author Rahul
 */
public final class SetObject 
{
    private Node head;
    private Node tail;
    private int length;
    
    public static SetObject createSetObject(Node head,Node tail,int length)
    {
    	return new SetObject(head,tail,length);
    }
    
    private SetObject(Node head,Node tail,int length)
    {
    	this.setHead(head);
    	this.setTail(tail);
    	this.setLength(length);
    }

	public Node getHead() 
	{
		return head;
	}

	public void setHead(Node head) 
	{
		this.head = head;
	}

	public Node getTail() 
	{
		return tail;
	}

	public void setTail(Node tail) 
	{
		this.tail = tail;
	}

	public int getLength() 
	{
		return length;
	}

	public void setLength(int length) 
	{
		this.length = length;
	}
 }
