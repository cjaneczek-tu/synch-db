package janemair;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;

/**
 * 
 * @author Christian Janeczek, Wolfgang Mair
 * @version 2014-11-19
 */
public class Thready implements Runnable{

	private Connection con1 = null, con2 = null;
	private Statement st1 = null, st2 = null;
	private ResultSet rs = null;
	private ArrayList<ArrayList<Integer>> idList = new ArrayList<ArrayList<Integer>>();
	private int changeId = -1;
	private String[] rowEntry;

	public Thready (Connection con1, Connection con2, Statement st1, Statement st2){

		this.setCon1(con1);
		this.setCon2(con2);
		this.st1 = st1;
		this.st2 = st2;
		this.rs = befehlAbfrage(st1, "SELECT * FROM person;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st1, "SELECT * FROM mitarbeiter;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st2, "SELECT * FROM Abteilung;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st2, "SELECT * FROM Angestellter;");
		idList.add(this.getIds(rs));
	}

	@Override
	public void run() {

		this.rs = befehlAbfrage(st1, "SELECT * FROM person;");

		try {
			changeId = this.searchChange(this.rs);
			if(changeId != -1){
				//Change other table and update changed back
				this.rs = befehlAbfrage(st1, "SELECT * FROM person where id = "+changeId+";");
				this.rowEntry = getRow(rs);
				System.out.println(rowEntry[0]);
				System.out.println(rowEntry[1]);
				System.out.println(rowEntry[2]);
				System.out.println(rowEntry[3]);
				System.out.println(rowEntry[4]);
				st2.executeUpdate("UPDATE Angestellter SET name = '"+rowEntry[1]+" "+rowEntry[2]+"', wohnort = '"+rowEntry[3]+"' where id = "+changeId+";");
				st1.executeUpdate("UPDATE person SET version = -1 where id = "+changeId+";");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// the same for the other tables
		// Check row count for inserts and deletes

	}

	/**
	 * Gibt eine ArrayListe mit den IDs der abgefragten tabelle zurueck
	 * @param rs Das Resultset der abgefragten tabelle
	 * @return   Die ArrayList mit den Ids
	 */
	private ArrayList<Integer> getIds(ResultSet rs){

		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			while(rs.next()){
				list.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * generiert die neue Tabelle
	 * 
	 * @param rs das ResultSet
	 * @throws SQLException Eine SQL Exception
	 */
	private String[] getRow(ResultSet rs) throws SQLException {
		if(rs != null){
			//Deklarieren von Attributen
			int colCount;
			String[] row;

			//ResultSetMetaData Objekt erstellen um deren Methoden zu benutzen
			ResultSetMetaData md = rs.getMetaData();
			//Spalten anzahl
			colCount = md.getColumnCount();

			//Den Inhalt der Tabellenmodel speichern
			row = new String[colCount];
			rs.next();
			for (int i = 1; i <= colCount; i++) {
				row[i - 1] = rs.getString(i);
			}
			return row;
		}
		return null;
	}	

	/**
	 * generiert die neue Tabelle
	 * 
	 * @param rs das ResultSet
	 * @throws SQLException Eine SQL Exception
	 */
	private int searchChange(ResultSet rs) throws SQLException {
		if(rs != null){
			//Deklarieren von Attributen
			int colCount;

			//ResultSetMetaData Objekt erstellen um deren Methoden zu benutzen
			ResultSetMetaData md = rs.getMetaData();
			//Spalten anzahl
			colCount = md.getColumnCount();

			//Den Inhalt der Tabellenmodel speichern

			while(rs.next()){
				if(rs.getInt(colCount) == 1){
					return rs.getInt(1);
				}
			}
		}
		return -1;
	}	

	/**
	 * Eine Methode die eine SQL abfrage bearbeitet und ein passendes Resultset zurueckgibt
	 * 
	 * @param befehl Die SQL abfrage
	 * @return   Das dadurch entstandene Resultset
	 */
	private ResultSet befehlAbfrage(Statement st, String befehl){
		//Abarbeiten des Befehles
		try{
			return rs = st.executeQuery(befehl);
		}
		catch(SQLException sqle){
			System.out.println("Error: "+sqle.getMessage());
			return null;
		}
	}

	public Connection getCon1() {
		return con1;
	}

	public void setCon1(Connection con1) {
		this.con1 = con1;
	}

	public Connection getCon2() {
		return con2;
	}

	public void setCon2(Connection con2) {
		this.con2 = con2;
	}
}
