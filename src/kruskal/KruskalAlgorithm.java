package kruskal;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import kruskal.Node;
/**
 * This is a greedy algorithm and selects the least weight edge at each stage. This makes use of a linked list representation
 * of the set with weighted union heuristic.
 * @author Rahul
 */
public final class KruskalAlgorithm 
{
	private Graph graph;
	
	//TODO: See if there is a better way of representing a nodes.
	private Set<Node> nodes = new HashSet<>();
	
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
			SetObject startVertexSetObject=findSet(edge.getStartVertex());
			SetObject endVertexSetObject  = findSet(edge.getEndVertex());
			
			if(startVertexSetObject!=endVertexSetObject)
			{
				mstEdges.add(edge);
				union(startVertexSetObject,endVertexSetObject);
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
		SetObject setObject = SetObject.createSetObject(null, null, 0);
         
		Node vertexNode = Node.createNode(setObject, null, vertex.getName());
		
		setObject.setHead(vertexNode);
		setObject.setTail(vertexNode);
		setObject.setLength(1);
		
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
	
	private SetObject findSet(Vertex vertex)
	{
		Set<SetObject> setObject= nodes.stream().filter(n->n.getVertexChar()==vertex.getName()).map(n->n.getHead()).collect(Collectors.toSet());
		if(setObject==null || setObject.isEmpty())
		{
			throw new IllegalStateException("Couldn't find the set representative object corresponding to the vertex:" + vertex.getName());
		}
		
		if(setObject.size()>1)
		{
			throw new IllegalStateException("There are more than 1 set objects corresponding to the vertex:" + vertex.getName());
		}
		return setObject.iterator().next();
	}
	
	private void union(SetObject start,SetObject end)
	{
		int startLength = start.getLength();
		int endLength = end.getLength(); 
		
		// The linked list length for the first vertex is > than that for the second vertex
		// Therefore attach the second list to the first.
		if(startLength>endLength)
		{
			Node startTail = start.getTail();
			startTail.setTail(end.getHead());
			
			Node endHead = end.getHead();
			while(endHead!=null)
			{
				endHead.setHead(start);
				endHead = endHead.getTail();
			}
			
			start.setTail(end.getTail());
			start.setLength(++startLength);
			
			end=null;
		}
		else
		{
			Node endTail = end.getTail();
			endTail.setTail(start.getHead());
			
			// Setting each node in the list being added to point to the new set object.
			Node startHead = start.getHead();
			while(startHead!=null)
			{
				startHead.setHead(end);
				startHead = startHead.getTail();
			}
			end.setTail(start.getTail());
			
			end.setLength(++endLength);
			
			start = null;
		}
	}
}
