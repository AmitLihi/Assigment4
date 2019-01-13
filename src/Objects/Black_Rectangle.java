package Objects;
import java.util.ArrayList;

import Geom.Point3D;
/**
 * This class represents a Black rectangle obstacle
 * @author Amit & Lihi
 */
public class Black_Rectangle {

	//two points given in csv file, we create the rectangle from it
	private Point3D pStart; 
	private Point3D pEnd;
	private int ID; 
	private Point3D upLeft; 
	private Point3D downLeft;
	private Point3D upRight;
	private Point3D downRight;
	private ArrayList <Point3D> cornersCollector  = new ArrayList();//list of 4 corners in this rectangle

	/**
	 * This Constructor gets a start point and end point and creates the Black rectangle from it
	 * @param pStart is the start point
	 * @param pEnd is the end point
	 */
	public Black_Rectangle(Point3D pStart, Point3D pEnd) {
		this.pStart = new Point3D(pStart);
		this.pEnd = new Point3D(pEnd);
		this.ID = 0;

		this.upLeft = new Point3D(pEnd.x(), pStart.y()); //first point 
		this.upRight = new Point3D(pStart);// second point
		this.downRight = new Point3D(pStart.x(), pEnd.y());//third point
		this.downLeft = new Point3D(pEnd);//fourt point

		this.cornersCollector.add(upLeft);
		this.cornersCollector.add(upRight);
		this.cornersCollector.add(downRight);
		this.cornersCollector.add(downLeft);
	}

	/**
	 *This Constructor gets a start point, end point and Id. It creates the Black rectangle from those elements
	 * @param pStart is the start point
	 * @param pEnd is the end point
	 * @param ID is the id of the rectangle
	 */
	public Black_Rectangle(Point3D pStart, Point3D pEnd, int ID) {
		this.pStart = new Point3D(pStart);
		this.pEnd = new Point3D(pEnd);
		this.ID = ID;

		this.upLeft = new Point3D(pEnd.x(), pStart.y());//first point
		this.upRight = new Point3D(pStart);//second point
		this.downRight = new Point3D(pStart.x(), pEnd.y());//third point
		this.downLeft = new Point3D(pEnd);//fourth point

		this.cornersCollector.add(upLeft);
		this.cornersCollector.add(upRight);
		this.cornersCollector.add(downRight);
		this.cornersCollector.add(downLeft);
	}

	/**
	 * This constructor gets x,y,z doubles of two points and id, it builds the rectangle from these elements
	 * @param lon is the x of the first point	
	 * @param lat is the y of the first point
	 * @param alt is the z of the first point
	 * @param lon2 is the x of the second point
	 * @param lat2 is the y of the second point
	 * @param alt2 is the x of the second point
	 * @param ID is the id of the rectangle
	 */
	public Black_Rectangle (double lon, double lat, double alt,double lon2, double lat2, double alt2,int ID) {
		this.pStart = new Point3D(lon,lat,alt);
		this.pEnd = new Point3D(lon2,lat2,alt2);
		this.ID = ID;

		this.upLeft = new Point3D(lon2,lat,alt);
		this.upRight = new Point3D(lon,lat,alt);
		this.downRight = new Point3D(lon,lat2,alt2);
		this.downLeft = new Point3D(lon2,lat2,alt2);

		this.cornersCollector.add(upLeft);
		this.cornersCollector.add(upRight);
		this.cornersCollector.add(downRight);
		this.cornersCollector.add(downLeft);
	}

	///**Getters & Setters**///


	public Point3D getpStart() {
		return pStart;
	}

	public void setpStart(Point3D pStart) {
		this.pStart = pStart;
	}

	public Point3D getpEnd() {
		return pEnd;
	}

	public void setpEnd(Point3D pEnd) {
		this.pEnd = pEnd;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Point3D> getCornersCollector() {
		return cornersCollector;
	}

	public void setCornersCollector(ArrayList<Point3D> cornersCollector) {
		this.cornersCollector = cornersCollector;
	}

}