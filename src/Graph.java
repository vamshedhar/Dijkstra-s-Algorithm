import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Graph {

	public HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	
	public Vertex getVertex(String name){
		Vertex V = this.vertices.get(name);
		
		if(V == null){
			V = new Vertex(name);
			this.vertices.put(name, V);
		}
		
		return V;
	}
	
	public void addEdge(String tailVertexName, String headVertexName, double weight){
		Vertex tailVertex = this.getVertex(tailVertexName);
		Vertex headVertex = this.getVertex(headVertexName);
		
		Edge E = new Edge(tailVertex, headVertex, weight);
		
		int index = tailVertex.adjacent.indexOf(E);
		if(index == -1){
			tailVertex.adjacent.add(E);
		} else{
			tailVertex.adjacent.get(index).weight = E.weight;
		}
	}
	
	public void deleteEdge(String tailVertexName, String headVertexName){
		Vertex tailVertex = this.getVertex(tailVertexName);
		Vertex headVertex = this.getVertex(headVertexName);
		
		Edge E = new Edge(tailVertex, headVertex, 0.0);
		
		tailVertex.adjacent.remove(E);
	}
	
	public void updateEdgeStatus(String tailVertexName, String headVertexName, boolean status){
		Vertex tailVertex = this.getVertex(tailVertexName);
		Vertex headVertex = this.getVertex(headVertexName);
		
		Edge E = new Edge(tailVertex, headVertex, 0.0);
		
		int index = tailVertex.adjacent.indexOf(E);
		if(index == -1){
			System.out.println("Edge not found!");
		} else{
			tailVertex.adjacent.get(index).active = status;
		}
	}
	
	public void edgeDown(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, false);
	}
	
	public void edgeUp(String tailVertexName, String headVertexName){
		this.updateEdgeStatus(tailVertexName, headVertexName, true);
	}
	
	public void updateVertexStatus(String vertexName, boolean status){
		Vertex V = this.getVertex(vertexName);
		
		V.active = status;
	}
	
	public void vertexDown(String vertexName){
		this.updateVertexStatus(vertexName, false);
	}
	
	public void vertexUp(String vertexName){
		this.updateVertexStatus(vertexName, true);
	}
	
	@Override
	public String toString() {
		
		ArrayList<String> vertexNames = new ArrayList<String>(this.vertices.keySet());
		
		Collections.sort(vertexNames);
		
		String output = "";
		
		for(String vertex : vertexNames){
			output += this.vertices.get(vertex);
		}
		
		return output;
	}
	
	public static void main(String[] args){
		// System arguments. Will change
		String filename = "network.txt";
		
		Graph G = new Graph();
		
		try{
			// Read data from file
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] lineData = line.split(" ");
				
				G.addEdge(lineData[0], lineData[1], Double.parseDouble(lineData[2]));
			}
			
			reader.close();
		} catch(IOException e){
			System.err.println(e);
		}
		
//		System.out.println(G);
		
		Dijkstra dj = new Dijkstra(G);
		dj.findShortestPath("Belk", "Grigg");
	}
}
