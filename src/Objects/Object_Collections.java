package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Algorithm.Convert;
import GUI.MyFrame;
import Geom.Point3D;
/**
 * This class represents the colletion of all the players in the game field
 * @author Amit & Lihi
 */
public class Object_Collections {

	private Me me = null;
	private ArrayList <Pacman> pacmans = new ArrayList();
	private ArrayList <Fruit> fruits = new ArrayList();
	private ArrayList <Ghost> ghosts = new ArrayList();
	private ArrayList <Black_Rectangle> black_Rectangles = new ArrayList();
	private ArrayList <Corner> corners = new ArrayList();

/**
 * Default constructor 
 */
	public Object_Collections() {

	}
/**
 * Copy constructor
 * @param oc - is the object collections object that we copy from
 */
	public Object_Collections(Object_Collections oc) {
		this.me = oc.getMe();
		this.pacmans = new ArrayList(oc.getPacmans());
		this.fruits = new ArrayList(oc.getFruits());
		this.ghosts = new ArrayList(oc.getGhosts());
		this.black_Rectangles = new ArrayList(oc.getBlack_Rectangles());
	}


	///***Getters & Setters***///

	public Me getMe() {
		return me;
	}

	public void setMe(Me me) {
		this.me = me;
	}

	public ArrayList<Pacman> getPacmans() {
		return pacmans;
	}

	public void setPacmans(ArrayList<Pacman> pacmans) {
		this.pacmans = pacmans;
	}

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}

	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public ArrayList<Black_Rectangle> getBlack_Rectangles() {
		return black_Rectangles;
	}

	public void setBlack_Rectangles(ArrayList<Black_Rectangle> black_Rectangles) {
		this.black_Rectangles = black_Rectangles;
	}

	///***Pacman***///

	public boolean addPacman (Pacman p)
	{
		return this.pacmans.add(p);
	}

	public int sizePacman() {
		return this.pacmans.size();
	}

	public Pacman getPacman(int i) {
		return this.pacmans.get(i);
	}

	/**
	 * This function checks if the specific pacman contains in the game
	 * @param id - is the id of the pacman
	 * @return true if the pacman which ID is id is found
	 */
	public boolean containPacman(int id) {
		for (int i = 0; i < this.getPacmans().size(); i++) {
			if (this.getPacman(i).getID()==id)return true;
		}
		return false;
	}
	///***fruits***///

	public boolean addFruit (Fruit f)
	{
		return fruits.add(f);
	}

	public int sizeFruit() {
		return fruits.size();
	}

	public Fruit getFruit(int i) {
		return this.fruits.get(i);
	}

	/**
	 * This function checks if the specific fruit contains in the game
	 * @param id - is the id of the fruit
	 * @return true if the fruit which ID is id is found
	 */
	public boolean containFruit(int id) {
		for (int i = 0; i < this.getFruits().size(); i++) {
			if (this.getFruit(i).getID()==id)return true;
		}
		return false;
	}

	///***Ghost***///

	public boolean addGhost (Ghost g)
	{
		return ghosts.add(g);
	}

	public int sizeGhost() {
		return ghosts.size();
	}

	public Ghost getGhost(int i) {
		return this.ghosts.get(i);
	}

	///***Black_Rectangle***///

	public boolean addBlack_Rectangle (Black_Rectangle br)
	{
		return black_Rectangles.add(br);
	}		

	public int sizeBlack_Rectangle() {
		return black_Rectangles.size();
	}

	public Black_Rectangle getBlack_Rectangle(int i) {
		return this.black_Rectangles.get(i);
	}

	///***Corners***///

	public ArrayList<Corner> getCorners() {
		return corners;
	}

	public void setCorners(ArrayList<Corner> corners) {
		this.corners = corners;
	}

	public boolean addCorner (Corner corner)
	{
		return corners.add(corner);
	}		

	public int sizeCorners() {
		return corners.size();
	}

	public Corner getCorners(int i) {
		return this.corners.get(i);
	}

	///***File Reader Writer***///
	
	/**
	 * This class reads the game (csv file)  
	 * @param csvFile is the path 
	 */
	public void readFile(String csvFile) {
		int rows=0,cols=1,counter=0;
		String line="";
		try (BufferedReader check = new BufferedReader(new FileReader(csvFile))) //build a reader for the file
		{
			while ((line = check.readLine()) != null) // run the file and check how many rows and cols it has
			{
				rows++;
				if(rows==1) {
					for (int i=0; i<line.length();i++) {
						if(line.charAt(i)==',') {
							cols++;
						}
					}
				}
			}
			BufferedReader br = new BufferedReader(new FileReader(csvFile));//run the file again this time to put it in a metrix string
			String str [][] = new String [rows][cols];
			int i=0;
			while ((line = br.readLine()) != null) 
			{
				str[i] = line.split(",");
				i++;
			}

			for (int j = 1; j < str.length; j++) {
				if(str[j][0].equals("M")) {
					this.setMe(new Me(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
				}

				else if(str[j][0].equals("P")) {
					this.addPacman(new Pacman(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
				}

				else if(str[j][0].equals("F")) {
					this.addFruit(new Fruit(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Integer.parseInt(str[j][1])));
				}

				else if(str[j][0].equals("G")) {
					this.addGhost(new Ghost(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
				}

				else{
					this.addBlack_Rectangle(new Black_Rectangle(Double.parseDouble(str[j][6]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][7]),Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Integer.parseInt(str[j][1])));
					this.addCorner(new Corner (Double.parseDouble(str[j][3]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][4]),counter));counter++;
					this.addCorner(new Corner (Double.parseDouble(str[j][6]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][7]),counter));counter++;
					this.addCorner(new Corner (Double.parseDouble(str[j][6]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][7]),counter));counter++;
					this.addCorner(new Corner (Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),counter));counter++;
				}
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * This function writes with a csv file
	 * @param csvFile is the path
	 */
	public void writeFile(String csvFile) {
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(csvFile));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		Point3D p = new Point3D(0,0,0);
		Point3D p2 = new Point3D(0,0,0);
		StringBuilder sb = new StringBuilder();
		sb.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + this.sizePacman() + "," + this.sizeFruit() + "\n");
		if(this.getMe() != null) {
			p = new Point3D (convert(this.getMe().getP().x(),this.getMe().getP().y())); // convert to coords back from pix
			sb.append("M," + this.getMe().getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + this.getMe().getSpeed()+ "," + this.getMe().getRadius() + "\n");
		}
		for (int j = 0; j < this.sizePacman(); j++) {
			p = new Point3D (convert(this.getPacman(j).getP().x(),this.getPacman(j).getP().y())); // convert to coords back from pix
			sb.append("P," + this.getPacman(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + this.getPacman(j).getSpeed()+ "," + this.getPacman(j).getRadius() + "\n");
		}
		for (int j = 0; j < this.sizeFruit(); j++) {
			p = new Point3D (convert(this.getFruit(j).getP().x(),this.getFruit(j).getP().y())); // convert to coords back from pix
			sb.append("F," + this.getFruit(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + this.getFruit(j).getWeight() + "\n");
		}
		for (int j = 0; j < this.sizeGhost(); j++) {
			p = new Point3D (convert(this.getGhost(j).getP().x(),this.getGhost(j).getP().y())); // convert to coords back from pix
			sb.append("G," + this.getGhost(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + ","+ this.getGhost(j).getSpeed()+ "," + this.getGhost(j).getRadius() + "\n");
		}
		for (int j = 0; j < this.sizeBlack_Rectangle(); j++) {
			p = new Point3D (convert(this.getBlack_Rectangle(j).getpStart().x(),this.getBlack_Rectangle(j).getpStart().y())); // convert to coords back from pix
			p2 = new Point3D (convert(this.getBlack_Rectangle(j).getpEnd().x(),this.getBlack_Rectangle(j).getpEnd().y())); // convert to coords back from pix
			sb.append("B," + this.getBlack_Rectangle(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + p2.y()+ "," + p2.x()+ "," + p2.z() + "\n");
		}
		pw.write(sb.toString());
		pw.close();
	}

	/**
	 * This class reads the game from a string
	 * @param s is the string
	 */
	public void read(ArrayList <String> s) {
		int counter=0;
		String [][] str = new String [s.size()][8];
		for (int i = 0; i < s.size(); i++) {
			str[i] = s.get(i).split(",");
		}
		for (int j = 0; j < str.length; j++) {
			if(str[j][0].equals("M")) {
				this.setMe(new Me(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
			}

			else if(str[j][0].equals("P")) {
				this.addPacman(new Pacman(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
			}

			else if(str[j][0].equals("F")) {
				this.addFruit(new Fruit(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Integer.parseInt(str[j][1])));
			}

			else if(str[j][0].equals("G")) {
				this.addGhost(new Ghost(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1])));
			}

			else{
				this.addBlack_Rectangle(new Black_Rectangle(Double.parseDouble(str[j][6]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][7]),Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Integer.parseInt(str[j][1])));
				this.addCorner(new Corner (Double.parseDouble(str[j][3]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][4]),counter));counter++;
				this.addCorner(new Corner (Double.parseDouble(str[j][6]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][7]),counter));counter++;
				this.addCorner(new Corner (Double.parseDouble(str[j][6]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][7]),counter));counter++;
				this.addCorner(new Corner (Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),counter));counter++;
			}
		}
	}

	/**
	 * This class is responsible for sending to convert object for converting from or to pixels or courdinates
	 * @param x
	 * @param y
	 * @return a new point with x,y after convertion
	 */
	private Point3D convert(double x, double y) {
		MyFrame m = new MyFrame();
		Convert p = new Convert();
		return p.convert2Coords(m.getMyImage().getHeight(), m.getMyImage().getWidth() , x, y);
	}
}