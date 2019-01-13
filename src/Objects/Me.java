package Objects;
import java.util.ArrayList;

import Geom.Point3D;
/**
 * This class represents the player class which aim is eating all the fruits as fast as possible
 * @author Amit & Lihi
 */
public class Me {

	private Point3D p;
	private double radius;
	private double speed;
	private int ID;
	private ArrayList <Corner> visible; // a list of all the corners that "me" can see

	/**
	 * Default constuctor
	 */
	public Me() {
		
	}
	/**
	 * Copy constructor 
	 * @param me is the player we will copy
	 */
	public Me(Me me) {
		this.p = new Point3D (me.getP());
		this.radius = me.radius;
		this.speed = me.speed;
		this.ID = me.ID;
		this.visible = new ArrayList();
	}
	/**
	 * This constructor gets one point and creates a player
	 * @param p is the point
	 */
	public Me(Point3D p) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = 0;
		this.visible = new ArrayList();
	}
/**
 * This constructor gets point and id and creates a new "me"
 * @param p is the point
 * @param id is the id of the player
 */
	public Me(Point3D p, int id) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = id;
		this.visible = new ArrayList();
	}
/**
 * This constructor gets x,y,z , speed,radius,id and creates "me" from it
	 * @param lon is longitude is x
	 * @param lat is lattitude is y
	 * @param alt is altitude is z
	 * @param speed is the speed of the me
	 * @param radius is the radius of the me
	 * @param id is the id of me
 */
	public Me(double lon, double lat, double alt, double speed, double radius, int id)
	{
		this.p = new Point3D (lon,lat,alt);
		this.radius = radius;
		this.speed = speed;
		this.ID = id;
		this.visible = new ArrayList();
	}
	
	///***Getters & Setters***///

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Corner> getVisible() {
		return visible;
	}

	public void setVisible(ArrayList<Corner> visible) {
		this.visible = visible;
	}
}