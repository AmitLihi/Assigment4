package Algorithm;
import java.util.ArrayList;

import GUI.MyFrame;
import Geom.Point3D;
import Objects.Corner;
import Objects.Object_Collections;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
/**
 * This class represents the main Algorithm of the movement's game
 * @author Amit & Lihi
 */
public class Algorithm {

	private double azimuth = -1;
	private Convert convert = new Convert();
	private int indexFruit = -1, idF = -1;
	private boolean firstIteration = true, visibleFruit = true, finish = false;
	private Graph graph = new Graph();
	private ArrayList<String> shortestPath;
	private Point3D lastPoint = new Point3D (0,0,0);
	private MyFrame mf = new MyFrame();

	/**
	 * This function routes these functions:cornersRoute,fruitRoute,meRoute,algorithm 
	 * @param oc is object collections object
	 * @return the azimuth the player should go
	 */
	public double mainAlgorithm(Object_Collections oc) {
		if(firstIteration) {
			cornersRoute(oc);
			fruitRoute(oc);
			firstIteration = false;
		}
		meRoute(oc);
		algorithm(oc);
		return azimuth;
	}

	private double algorithm(Object_Collections oc) {
		if((!oc.containFruit(idF))&&(oc.getFruits().size() != 0)) { // when searching for the next fruit && fruit is not empty
			double temp = 0, distanceFruit = Integer.MAX_VALUE; finish = false;
			for (int i = 0; i < oc.getFruits().size(); i++) {
				//visible fruit
				if(!checkBlockedMe(oc, oc.getMe().getP(),oc.getFruit(i).getP())) {
					temp = distance(oc.getMe().getP(),oc.getFruit(i).getP());
					if (temp<distanceFruit) {
						distanceFruit = temp;
						indexFruit = i;
						visibleFruit = true;
					}
				}
				else {
					GameGraph gameGraph = new GameGraph(oc, oc.getFruit(i), graph);
					shortestPath = gameGraph.getShortestPath();
					if (gameGraph.getSave().getDist()<distanceFruit) {
						distanceFruit = gameGraph.getSave().getDist();
						indexFruit = i;
						visibleFruit = false;
					}
				}
			}
			if(visibleFruit) {//when the fruit is visible, send the pacman to the azimuth
				Point3D mePoint = new Point3D (oc.getMe().getP().y(),oc.getMe().getP().x());
				Point3D fruitPoint = new Point3D (oc.getFruit(indexFruit).getP().y(), oc.getFruit(indexFruit).getP().x());
				azimuth = convert.azimuth(mePoint,fruitPoint);
				idF = oc.getFruit(indexFruit).getID();
				return azimuth;
			}
			else {//when the fruit is not visible, send the pacman to the azimuth
				if (shortestPath.size() != 0 && shortestPath.get(0).equals("source"))
					shortestPath.remove(0);
				Point3D mePoint = new Point3D (oc.getMe().getP().y(),oc.getMe().getP().x());
				//Point3D point = new Point3D(oc.getCorners(Integer.parseInt(shortestPath.get(0))).getPoint().y()+0.00005,oc.getCorners(Integer.parseInt(shortestPath.get(0))).getPoint().x()+0.00005);
				Point3D point = new Point3D(changePoint(oc.getCorners(Integer.parseInt(shortestPath.get(0)))));
				lastPoint = new Point3D (point);
				idF = oc.getFruit(indexFruit).getID();
				azimuth = convert.azimuth(mePoint,point);
				return azimuth;
			}
		}
		else {//when the pacman hasnt reached the fruit yet
			if(visibleFruit) return azimuth; //for visible fruit
			else {//for not visible fruit
				Point3D checkPoint = new Point3D(lastPoint.y(), lastPoint.x());
				double d = distance(oc.getMe().getP(), checkPoint);
				if(d<0.000045 && !finish) {//check if the pacman got the point
					if(shortestPath.size() != 0) shortestPath.remove(0);
					if(shortestPath.size() != 0) { //if the list is not empty
						Point3D mePoint = new Point3D (oc.getMe().getP().y(),oc.getMe().getP().x());
						//						Point3D point = new Point3D(oc.getCorners(Integer.parseInt(shortestPath.get(0))).getPoint().y()+0.00005,oc.getCorners(Integer.parseInt(shortestPath.get(0))).getPoint().x()+0.00005);
						Point3D point = new Point3D(changePoint(oc.getCorners(Integer.parseInt(shortestPath.get(0)))));
						idF = oc.getFruit(indexFruit).getID();
						lastPoint = new Point3D (point);
						azimuth = convert.azimuth(mePoint,point);
						return azimuth;
					}
					else {
						Point3D mePoint = new Point3D (oc.getMe().getP().y(),oc.getMe().getP().x());
						Point3D fruitPoint = new Point3D (oc.getFruit(indexFruit).getP().y(), oc.getFruit(indexFruit).getP().x());
						azimuth = convert.azimuth(mePoint,fruitPoint);
						idF = oc.getFruit(indexFruit).getID();
						finish = true;
						return azimuth;
					}
				}
			}
		}
		return azimuth;
	}

	private Point3D changePoint(Corner c) {
		if(c.getName()==0 || c.getName()==4 || c.getName()==8 || c.getName()==12 || c.getName()==16 || c.getName()==20 || c.getName()==24) return new Point3D(c.getPoint().y()+0.00005, c.getPoint().x()-0.00005);
		else if(c.getName()==1 || c.getName()==5 || c.getName()==9 || c.getName()==13 || c.getName()==17 || c.getName()==21 || c.getName()==25) return new Point3D(c.getPoint().y()+0.00005, c.getPoint().x()+0.00005);
		else if(c.getName()==2 || c.getName()==6 || c.getName()==10 || c.getName()==14 || c.getName()==18 || c.getName()==22 || c.getName()==26) return new Point3D(c.getPoint().y()-0.00005, c.getPoint().x()+0.00005);
		else return new Point3D(c.getPoint().y()-0.00005, c.getPoint().x()-0.00005);
	}

	private void meRoute (Object_Collections oc) {
		for (int i = 0; i < oc.getCorners().size(); i++) {
			boolean blocked = checkBlockedMe(oc, oc.getMe().getP(),oc.getCorners().get(i).getPoint());
			if(!blocked) oc.getMe().getVisible().add(oc.getCorners().get(i).getCorner());
		}
	}

	private void cornersRoute (Object_Collections oc) {
		/*int extra = -4; boolean exist = false;
		for (int i = 0; i < oc.getCorners().size(); i++) {
			if(i%4==0) extra=extra+4;
			if(i%2==0) {
				oc.getCorners(i).getVisible().add(oc.getCorners().get(1+extra));
				oc.getCorners(i).getVisible().add(oc.getCorners().get(3+extra));
			}
			else {
				oc.getCorners(i).getVisible().add(oc.getCorners().get(0+extra));
				oc.getCorners(i).getVisible().add(oc.getCorners().get(2+extra));
			}
		}
		extra = -1;
		for (int i = 0; i < oc.getCorners().size(); i++) {
			if(i%4==0) {
				extra++;
			}
			for (int j = 0; j < oc.getCorners().size(); j++) {
				exist = false;
				for (int j2 = 0; j2 < 4 && !exist; j2++) {
					if(oc.getBlack_Rectangle(extra).getCornersCollector().get(j2).equals(oc.getCorners(j).getPoint())) {
						exist = true;
					}
				}
				if(exist == false) {
					boolean blocked = checkBlocked(oc, oc.getCorners().get(i).getPoint() ,oc.getCorners().get(j).getPoint());
					if(!blocked) {
						oc.getCorners(i).getVisible().add(oc.getCorners().get(j));
					}		
				}	
			}
		}*/
		for (int i = 0; i < oc.getCorners().size(); i++) {
			for (int j = 0; j < oc.getCorners().size(); j++) {
				if(oc.getCorners().get(i).getName() != oc.getCorners().get(j).getName()) {
					boolean blocked = checkBlocked(oc, oc.getCorners().get(i).getPoint() ,oc.getCorners().get(j).getPoint());
					if(!blocked) {
						oc.getCorners(i).getVisible().add(oc.getCorners().get(j));
					}
				}
			}
		}
	}

	private void fruitRoute (Object_Collections oc) {
		for (int i = 0; i < oc.getFruits().size(); i++) {
			for (int j = 0; j < oc.getCorners().size() ; j++) {
				boolean blocked = checkBlocked(oc, oc.getFruits().get(i).getP(), oc.getCorners().get(j).getPoint());
				if(!blocked) oc.getFruits().get(i).getVisible().add(oc.getCorners().get(j));
			}
		}
	}

	private boolean checkBlocked(Object_Collections oc, Point3D p1, Point3D p2) {
		for (int i = 0; i < oc.getBlack_Rectangles().size(); i++) {
			for (int j = 0; j < oc.getBlack_Rectangles().get(i).getCornersCollector().size(); j++) {
				if (isBlocked(p1, p2, oc.getBlack_Rectangles().get(i).getCornersCollector().get(j),  oc.getBlack_Rectangles().get(i).getCornersCollector().get((j+1)%4))) return true;
			}	
		}
		return false;
	}

	
	private boolean checkBlockedMe(Object_Collections oc, Point3D p1, Point3D p2) {
		for (int i = 0; i < oc.getBlack_Rectangles().size(); i++) {
			for (int j = 0; j < oc.getBlack_Rectangles().get(i).getCornersCollector().size(); j++) {
				if (isBlockedMe(p1, p2, oc.getBlack_Rectangles().get(i).getCornersCollector().get(j),  oc.getBlack_Rectangles().get(i).getCornersCollector().get((j+1)%4))) return true;
			}	
		}
		return false;
	}

	private boolean vertical(double x3, double x4){
		if (x3 == x4) // meaning the slope is 0
			return true;
		return false;
	}

	private boolean isBlocked(Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
		//***************check what to do when m is zero
		//the line between point1 and point2  
		p1 = new Point3D (convert.convert2Pix(642,1433, p1.x(), p1.y()));
		p2 = new Point3D (convert.convert2Pix(642,1433, p2.x(), p2.y()));
		p3 = new Point3D (convert.convert2Pix(642,1433, p3.x(), p3.y()));
		p4 = new Point3D (convert.convert2Pix(642,1433, p4.x(), p4.y()));		
		double x1=p1.x();
		double y1=p1.y();
		double x2=p2.x();
		double y2=p2.y();
		//the Edge of a rectangle
		double x3=p3.x();
		double y3=p3.y();
		double x4=p4.x();
		double y4=p4.y();
		boolean ver = vertical(x3,x4);
		//if the edge is vertical we will consider x range
		if(p1==p3 && p2==p4) {
			return false;
		}
		if (ver)
		{

			double xEdge = x3; 
			double mLine = (y1-y2)/(x1-x2);
			double yLine = y1 + mLine*(xEdge - x1);
			//check if the Intersection point is in the range of both line and edge
			if (((y2<=yLine && yLine<=y1) || (y2>=yLine && yLine>=y1)) && ((y4<=yLine && yLine<=y3) || (y4>=yLine && yLine>=y3)))
				return true; // the line is blocked by the edge
			return false;
		}
		//if the edge is not vertical we will consider y range
		else{
			double yEdge = y3; 
			double mLine = (y1-y2)/(x1-x2);
			double xLine = (mLine*x1 + yEdge - y1)/mLine;
			//check if the Intersection point is in the range of both line and edge
			if (((x1<=xLine && xLine<=x2) || (x1>=xLine && xLine>=x2)) && ((x3<=xLine && xLine<=x4) || (x3>=xLine && xLine>=x4)))
				return true; // the line is blocked by the edge
			return false;
		}
	}
	
	private boolean isBlockedMe(Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
		//***************check what to do when m is zero
		//the line between point1 and point2  
		p1 = new Point3D (convert.convert2Pix(642,1433, p1.x(), p1.y()));
		p2 = new Point3D (convert.convert2Pix(642,1433, p2.x(), p2.y()));
		p3 = new Point3D (convert.convert2Pix(642,1433, p3.x(), p3.y()));
		p4 = new Point3D (convert.convert2Pix(642,1433, p4.x(), p4.y()));		
		double x1=p1.x();
		double y1=p1.y();
		double x2=p2.x();
		double y2=p2.y();
		//the Edge of a rectangle
		double x3=p3.x();
		double y3=p3.y();
		double x4=p4.x();
		double y4=p4.y();
		boolean ver = vertical(x3,x4);
		//if the edge is vertical we will consider x range
		if(p1==p3 && p2==p4) {
			return false;
		}
		if (ver)
		{

			double xEdge = x3; 
			double mLine = (y1-y2)/(x1-x2);
			double yLine = y1 + mLine*(xEdge - x1);
			//check if the Intersection point is in the range of both line and edge
			if (((y2<=yLine && yLine<=y1) || (y2>=yLine && yLine>=y1)) && ((y4<=yLine && yLine<=y3) || (y4>=yLine && yLine>=y3)) && (!p2.equals(p3)) && (!p2.equals(p4)) && (!p1.equals(p3)) && (!p1.equals(p4)))
				return true; // the line is blocked by the edge
			return false;
		}
		//if the edge is not vertical we will consider y range
		else{
			double yEdge = y3; 
			double mLine = (y1-y2)/(x1-x2);
			double xLine = (mLine*x1 + yEdge - y1)/mLine;
			//check if the Intersection point is in the range of both line and edge
			if (((x1<=xLine && xLine<=x2) || (x1>=xLine && xLine>=x2)) && ((x3<=xLine && xLine<=x4) || (x3>=xLine && xLine>=x4)) && (!p2.equals(p3)) && (!p2.equals(p4)) && (!p1.equals(p3)) && (!p1.equals(p4)))
				return true; // the line is blocked by the edge
			return false;
		}
	}

	private double distance (Point3D me, Point3D target) {
		return Math.sqrt(Math.pow(me.x()-target.x(),2)+(Math.pow(me.y()-target.y(),2)));
	}
}
