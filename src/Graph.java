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
	
	public void addEdge(String headVertexName, String tailVertexName, double weight){
		Vertex headVertex = this.getVertex(headVertexName);
		Vertex tailVertex = this.getVertex(tailVertexName);
		
		Edge E = new Edge(headVertex, tailVertex, weight);
		
		int index = headVertex.adjacent.indexOf(E);
		if(index == -1){
			headVertex.adjacent.add(E);
		} else{
			headVertex.adjacent.get(index).weight = E.weight;
		}
	}
	
	public void deleteEdge(String headVertexName, String tailVertexName){
		Vertex headVertex = this.getVertex(headVertexName);
		Vertex tailVertex = this.getVertex(tailVertexName);
		
		Edge E = new Edge(headVertex, tailVertex, 0.0);
		
		headVertex.adjacent.remove(E);
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
				
				Vertex startVertex = G.getVertex(lineData[0]);
				Vertex endVertex = G.getVertex(lineData[1]);
				
				Double weight = Double.parseDouble(lineData[2]);
				
				Edge E = new Edge(startVertex, endVertex, weight);
				
				startVertex.adjacent.add(E);
			}
			
			reader.close();
		} catch(IOException e){
			System.err.println(e);
		}
		
		System.out.println(G);
		
	}
}
