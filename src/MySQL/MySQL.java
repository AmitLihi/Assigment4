package MySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MySQL {


	public MySQL(String s, int mapID) {
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		String [] sArray = s.split(",");
		double gamePoint=Double.parseDouble(sArray[2].substring(6, sArray[2].length()));
		ArrayList <Double>arr=new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

			Statement statement = connection.createStatement();

			//select data
			String allCustomersQuery = "SELECT * FROM logs;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			
			while(resultSet.next())
			{
				if(resultSet.getInt("SomeDouble") == mapID) arr.add(resultSet.getDouble("Point"));
			}
			resultSet.close();		
			statement.close();		
			connection.close();		
			Collections.sort(arr);
			arr.toString();
			
			System.out.println("*****************************************************");
			
			int max = 0, i = 0;
			for(i = 0 ; i < arr.size() ; i++) {
				if(arr.get(i) <= gamePoint) max = i;
			}
			System.out.println("MyPlace: " + max + ", from: " + i);
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}