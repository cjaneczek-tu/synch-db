package janemair;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * 
 * @author Christian Janeczek, Wolfgang Mair
 * @version 2014-11-19
 */
public class StartIsoThread {
	public static void main(String[] args){

		System.out.println("OBVIOUS!");
		
		FactorySQL fs = new FactorySQL();
		DataSource ds1, ds2;
		Connection con1 = null, con2 = null;
		Statement st1 = null, st2 = null;
		Thread th;

		CLI cli = new CLI(args);

		System.out.println("TRICKY START!");
		ds1 = fs.createDataSource(cli.getType1(),cli.getServer1(), cli.getDatenbank1());
		System.out.println("TRICKY MIDDLE!");
		ds2 = fs.createDataSource(cli.getType2(),cli.getServer2(), cli.getDatenbank2());
		System.out.println("TRICKY END!");
		try {
			System.out.println("CONNECTION PREPARED!");
			
			con1 = ds1.getConnection(cli.getUser(), cli.getPass());
			System.out.println("CONNECTION MIDDLE!");
			con2 = ds2.getConnection(cli.getUser2(), cli.getPass2());
			
			System.out.println("CONNECTION STARTED!");

			st1 = con1.createStatement();
			st2 = con2.createStatement();
			
			System.out.println("STATEMENT CREATED!");
			
			th = new Thread(new Thready(con1,con2,st1,st2));
			th.start();
			
			System.out.println("OH SNAP IT WORKS!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
