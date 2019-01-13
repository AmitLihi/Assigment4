package Objects;

import java.util.ArrayList;
import Geom.Point3D;
/**
 * This class represents a corner in a rectangle
 * @author Amit & Lihi
 */
public class Corner {

	private int name; // the id of the corner
	private Point3D point; // the point of the corner
	private ArrayList<Corner> visible; // list of the corners this orner can see

	/**
	 * This constructor gets a point and counter and creates the corner
	 * @param point - the point of the corner
	 * @param counter - the id of the corner
	 */
	public Corner (Point3D point, int counter) {
		this.point = point;
		this.name = counter;
		this.visible = new ArrayList();
	}
	
	/**
	 * This constructor gets x,y,z and counter and creates the corner
	 * @param lon - longitute is x
	 * @param lat - latitude is y
	 * @param alt - altitude is z
	 * @param counter - is the id of the corner
	 */
	public Corner (double lon, double lat, double alt, int counter) {
		this.point = new Point3D (lon, lat, alt);
		this.name = counter;
		this.visible = new ArrayList();
	}

	///***Getters & setters***///

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Point3D getPoint() {
		return point;
	}

	public Corner getCorner() {
		return this;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}

	public ArrayList<Corner> getVisible() {
		return visible;
	}

	public void setVisible(ArrayList<Corner> visible) {
		this.visible = visible;
	}		
}