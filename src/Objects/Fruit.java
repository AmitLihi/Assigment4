package Objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Geom.Point3D;
/**
 * This class represents a Fruit which is the Pacmans target
 * @author Amit & Lihi
 */
public class Fruit {

	private Point3D p;
	private double weight;
	private int ID;
	private ArrayList <Corner> visible = new ArrayList();// a list of all the corners this fruit can see
	
	/**
	 * Constructor that gets 3Dpoint and creats a new Fruit with this point
	 * @param p is the point
	 */
	public Fruit(Point3D p) {
		this.p = new Point3D(p);
		this.weight = 1;
		this.ID = 0;
	}
	/**
	 * Constructor that gets 3Dpoint, id and creats a new Fruit with this point
	 * @param p is a 3Dpoint
	 * @param id is the is of the fruit
	 */
	public Fruit(Point3D p, int id) {
		this.p = new Point3D(p);
		this.weight = 1;
		this.ID = id;
	}
	/**
	 * Construstor that gets coords and weight of the Fruit and creates a new Fruit from it	
	 * @param lon is longitude is x
	 * @param lat is lattitude is y
	 * @param alt is altitude is z
	 * @param weight is the weight of the fruit
	 */
	public Fruit (double lon, double lat, double alt, double weight, int id) {
		this.p = new Point3D (lon,lat,alt);
		this.weight = 1;
		this.ID = id;
	}
	
	///*** Getters & Setters ***///
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Fruit [p=" + p + ", weight=" + weight + "]";
	}
	
	public ArrayList<Corner> getVisible() {
		return visible;
	}

	public void setVisible(ArrayList<Corner> visible) {
		this.visible = visible;
	}

}
