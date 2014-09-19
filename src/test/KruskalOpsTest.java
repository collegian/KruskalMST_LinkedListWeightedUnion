package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import kruskal.KruskalAlgorithm;

public final class KruskalOpsTest 
{
  KruskalAlgorithm ka = new KruskalAlgorithm();
  Graph graph;
  Set<Edge> expectedEdges;
  
  @Before
  public void setUp()
  {
	  Vertex a = Vertex.createVertex('a');
	  Vertex b = Vertex.createVertex('b');
	  Vertex c = Vertex.createVertex('c');
	  Vertex d = Vertex.createVertex('d');
	  Vertex e = Vertex.createVertex('e');
	  Vertex f = Vertex.createVertex('f');
	  Vertex g = Vertex.createVertex('g');
	  Vertex h = Vertex.createVertex('h');
	  Vertex i = Vertex.createVertex('i');
	  
	  Set<Vertex> vertices = new HashSet<>();
	  vertices.add(a);
	  vertices.add(b);
	  vertices.add(c);
	  vertices.add(d);
	  vertices.add(e);
	  vertices.add(f);
	  vertices.add(g);
	  vertices.add(h);
	  vertices.add(i);
	  
	  Set<Edge> edges = new HashSet<>();
	  
	  Edge ab = Edge.createEdge(a, b, 4);
	  Edge ah = Edge.createEdge(a, h, 8);
	  Edge bh = Edge.createEdge(b, h, 11);
	  Edge bc = Edge.createEdge(b, c, 8);
	  Edge hi = Edge.createEdge(h, i, 7);
	  Edge hg = Edge.createEdge(h, g, 1);
	  Edge gi = Edge.createEdge(g, i, 6);
	  Edge gf = Edge.createEdge(g, f, 2);
	  Edge ci = Edge.createEdge(c, i, 2);
	  Edge cf = Edge.createEdge(c, f, 4);
	  Edge cd = Edge.createEdge(c, d, 7);
	  Edge de = Edge.createEdge(d, e, 9);
	  Edge ef = Edge.createEdge(e, f, 10);
	  Edge df = Edge.createEdge(d, f, 14);
	  
	  
	  edges.add(ab);
	  edges.add(ah);
	  edges.add(bh);
	  edges.add(bc);
	  edges.add(hi);
	  edges.add(hg);
	  edges.add(gi);
	  edges.add(gf);
	  edges.add(ci);
	  edges.add(cf);
	  edges.add(cd);
	  edges.add(de);
	  edges.add(ef);
	  edges.add(df);
	  
	  expectedEdges = new HashSet<>();
	  expectedEdges.add(hg);
	  expectedEdges.add(gf);
	  expectedEdges.add(ci);
	  expectedEdges.add(ab);
	  expectedEdges.add(cf);
	  expectedEdges.add(cd);
	  expectedEdges.add(ah);
	  expectedEdges.add(bc);
	  expectedEdges.add(hi);
	  expectedEdges.add(de);
	  
      graph = Graph.createGraph(vertices, edges);
  }
  
  @Test
  public void testKruskalAlgorithm()
  {
	Set<Edge> mst = ka.getMinimumSpanningTree(graph);
	for(Edge edge:mst)
	{
		System.out.println(edge.getStartVertex().getName() + "->" + edge.getEndVertex().getName());
	}
	assertEquals("mst:expected size!=actual size", 8, mst.size());
	assertTrue(expectedEdges.containsAll(mst));
  }
}
