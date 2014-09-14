package kruskal;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import kruskal.Node;

public final class KruskalAlgorithm 
{
	private Graph graph;
	
	//TODO: See if there is a better way of representing a node set corresponding to each vertex.
	private Set<Node> nodes;
	
	public Set<Edge> getMinimumSpanningTree(Graph graph)
	{
		this.graph = graph;
		verifyPreconditions();
		
		Set<Vertex> vertices = graph.getVertices();
		for(Vertex vertex : vertices)
		{
			makeSet(vertex);
		}
		
		//TODO: Look and see if there is a better way to sort this.
		// Explore both if a better data structure can be used as well as if java 8 can be used.
		Set<Edge> edges = graph.getEdges();
		List<Edge> edgesList = Lists.<Edge>newArrayList(edges);
		sortEdges(edgesList);
		
		Set<Edge> mstEdges = new HashSet<>();
		
		for(Edge edge:edgesList)
		{
			Node startVertexSetNode = findSet(edge.getStartVertex());
			Node endVertexSetNode = findSet(edge.getEndVertex());
			if(startVertexSetNode!=endVertexSetNode)
			{
				mstEdges.add(edge);
				union(startVertexSetNode,endVertexSetNode);
			}
		}
		
		return mstEdges;
	}
	
	private void verifyPreconditions()
	{
		if(graph==null)
		{
			throw new IllegalStateException("The graph passed in can't be null.");
		}
		
		if(graph.getVertices()==null || graph.getVertices().isEmpty())
		{
			throw new IllegalStateException("The graph passed in can't contain null or empty vertices.");
		}
		
		if(graph.getEdges()==null || graph.getEdges().isEmpty())
		{
			throw new IllegalStateException("The graph passed in can't contain null or empty edges.");
		}
	}
	
	private void makeSet(Vertex vertex)
	{
		// The set object char represents the length of the Set.
		Node setObject = new Node(null,null,'0');
		
		Node vertexNode = new Node(setObject,null,vertex.getName());
		setObject.setHead(vertexNode);
		setObject.setTail(vertexNode);
		setObject.setVertex('1');
		
		nodes.add(vertexNode);
	}
	
	private void sortEdges(List<Edge> edgesList)
	{
		Collections.sort(edgesList,new Comparator<Edge>() 
	       {
		      @Override
		      public int compare(Edge e1, Edge e2) 
		      {
		     	   if(e1.getWeight()>e2.getWeight())
		      		{
		       			return 1;
		       		}
			       		
		       		if(e1.getWeight()==e2.getWeight())
		       		{
		       			return 0;
		       		}
		     		
			       		return -1;
		            }
		      });
	}
	
	private Node findSet(Vertex vertex)
	{
	  Set<Node> node= nodes.stream().filter(n->n.getVertexChar()==vertex.getName()).map(n->n.getHead()).collect(Collectors.toSet());
	  
	  if(node.size()>1)
	  {
		  throw new IllegalStateException("The set has more than 1 representative set object.");
	  }
	  
	  return node.iterator().next();
	}
	
	private void union(Node start,Node end)
	{
		char startVertexSetLength = start.getVertexChar();
		char endVertexSetLength = end.getVertexChar();
		
		if(!Character.isDigit(startVertexSetLength) || !Character.isDigit(endVertexSetLength))
		{
			throw new IllegalStateException("The set representative object doesn't contain the length of the set");
		}
		
		int startLength = Character.getNumericValue(startVertexSetLength);
		int endLength =Character.getNumericValue(endVertexSetLength); 
		
		// The linked list length for the first vertex is > than that for the second vertex
		// Therefore attach the second list to the first.
		if(startLength>endLength)
		{
			Node startTail = start.getTail();
			startTail.setTail(end.getHead());
			end.getHead().setHead(start);
			
			start.setTail(end.getTail());
			
			// TODO : Is this needed or pointless?
			end=null;
		}
		else
		{
			Node endTail = end.getTail();
			endTail.setTail(start.getHead());
			start.getHead().setHead(end);
			end.setTail(start.getTail());
			
			start = null;
		}
	}
}
