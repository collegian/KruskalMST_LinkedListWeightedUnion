package graph.components;


import com.google.common.base.Objects;

public final class Vertex 
{
  // TODO: Think of a better way to represent the name. String will consume a lot of memory!
 // But this is too small.
  private char name;
  
  public static Vertex createVertex(char name)
  {
	Vertex v = new Vertex(name);
	return v;
  }
  
  private Vertex(char name)
  {
	  this.name=name; 
  }

  public char getName() 
  {
	return name;
  }
  
  @Override
  public boolean equals(Object obj) 
   {
		if (obj instanceof Vertex) 
		{
			Vertex other = (Vertex) obj;
			
			return Objects.equal(name, other.name);
		} 
		else 
		{
			return false;
		}
	}
  
    @Override
	public int hashCode() 
    {
		return Objects.hashCode(name);
	}
}
