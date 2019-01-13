package Algorithm;

import java.util.ArrayList;

import Geom.Point3D;
import Objects.Fruit;
import Objects.Me;
import Objects.Object_Collections;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

public class GameGraph {

	private Object_Collections oc;
	private Me me;
	private Fruit fruit;
	private Graph graph;
	private Node save;
	private ArrayList<String> shortestPath;

	public GameGraph(Object_Collections oc, Fruit fruit, Graph graph) {
		this.oc = oc;
		this.fruit = fruit;
		this.graph = graph;
		funtion();	}

	private ArrayList funtion() {
		graph.add(new Node("source")); // Node "a"
		for(int i=0;i<oc.getCorners().size();i++) {
			graph.add(new Node(""+oc.getCorners(i).getName()));
		}
		graph.add(new Node("target")); // Node "b"

		for (int i = 0; i < oc.getMe().getVisible().size(); i++) { // me
			graph.addEdge("source", "" + oc.getMe().getVisible().get(i).getName(), distance(oc.getMe().getP(), oc.getMe().getVisible().get(i).getPoint()));
		}

		for (int i = 0; i < oc.getCorners().size(); i++) { // corner
			for (int j = 0; j < oc.getCorners().get(i).getVisible().size(); j++) {
				if( oc.getCorners().get(i).getName() != oc.getCorners().get(j).getName())
					graph.addEdge("" + oc.getCorners().get(i).getName(), "" + oc.getCorners().get(j).getName(), distance(oc.getCorners().get(i).getPoint(), oc.getCorners().get(j).getPoint()));
			}
		}

		for (int i = 0; i < fruit.getVisible().size(); i++) {
			for (int j = 0; j < oc.getCorners().size(); j++) { // fruit
				if(fruit.getVisible().get(i).getPoint().equals(oc.getCorners(j).getPoint())){
					graph.addEdge("target", "" + oc.getCorners(j).getName(), distance(fruit.getVisible().get(i).getPoint(), oc.getCorners(j).getPoint()));
				}
			}
		}
		
		Graph_Algo.dijkstra(graph, "source");
		save = graph.getNodeByName("target");
		shortestPath = save.getPath();
		return shortestPath;
	}

	
	public ArrayList<String> getShortestPath() {
		return shortestPath;
	}
	public void setShortestPath(ArrayList<String> shortestPath) {
		this.shortestPath = shortestPath;
	}
	public Node getSave() {
		return save;
	}
	public void setSave(Node save) {
		this.save = save;
	}
	private double distance (Point3D me, Point3D target) {
		return Math.sqrt(Math.pow(me.x()-target.x(),2)+(Math.pow(me.y()-target.y(),2)));
	}
}
