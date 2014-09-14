package graph.components;

import com.google.common.base.Objects;

// Using a comparator instead of comparable since the natural ordering is inconsistent with equals.
public final class Edge 
{
   private int weight;
   private Vertex startVertex,endVertex;
   
   public Edge createEdge(Vertex start, Vertex end, int weight)
   {
	   return new Edge(start,end,weight);
   }
   
   private Edge(Vertex start, Vertex end, int weight)
   {
	   this.startVertex=start;
	   this.endVertex=end;
	   this.weight=weight;
   }

   public int getWeight() 
   {
	return weight;
   }
   
   public Vertex getStartVertex() 
   {
	return startVertex;
   }
   
   public Vertex getEndVertex() 
   {
	return endVertex;
   }
   
   @Override
   public boolean equals(Object obj) 
    {
 		if (obj instanceof Edge) 
 		{
 			Edge other = (Edge) obj;
 			
 			// The edges (a,b,10) and (b,a,10) are same. This will only work for undirected edges.
 			// Also, removing the weight since (a,b,2) and (a,b,4) isn't permitted.
 			return (Objects.equal(startVertex, other.startVertex) || Objects.equal(startVertex, other.endVertex)) && (Objects.equal(endVertex, other.startVertex) || Objects.equal(endVertex, other.endVertex));
 		} 
 		else 
 		{
 			return false;
 		}
 	}
   
     @Override
 	public int hashCode() 
     {
 		return Objects.hashCode(startVertex,endVertex);
 	}


//	@Override
//	public int compareTo(Edge other) 
//	{
//		if(weight>other.weight)
//		{
//			return 1;
//		}
//		
//		else if(weight==other.weight)
//		{
//			return 0;
//		}
//		
//		return -1;
//	}

}