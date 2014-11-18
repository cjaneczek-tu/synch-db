package janemair;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;

public class Thready implements Runnable{

	private Connection con1 = null, con2 = null;
	private Statement st1 = null, st2 = null;
	private ResultSet rs = null;
	private ArrayList<ArrayList<Integer>> idList = new ArrayList<ArrayList<Integer>>();
	private int changeId = -1;
	private String[] rowEntry;

	public Thready (Connection con1, Connection con2, Statement st1, Statement st2){

		this.con1 = con1;
		this.con2 = con2;
		this.st1 = st1;
		this.st2 = st2;
		this.rs = befehlAbfrage(st1, "SELECT * FROM Person;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st1, "SELECT * FROM Mitarbeiter;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st2, "SELECT * FROM Abteilung;");
		idList.add(this.getIds(rs));
		this.rs = befehlAbfrage(st2, "SELECT * FROM Angestellter;");
		idList.add(this.getIds(rs));
	}

	@Override
	public void run() {

		this.rs = befehlAbfrage(st1, "SELECT * FROM Person;");

		try {
			changeId = this.searchChange(this.rs);
			if(changeId != -1){
				//Change other table and update changed back
				this.rs = befehlAbfrage(st1, "SELECT * FROM Person where id = "+changeId+";");
				this.rowEntry = getRow(rs);
				befehlAbfrage(st2, "UPDATE Angestellter SET name = '"+rowEntry[2]+"' '"+rowEntry[3]+"' and wohnort = '"+rowEntry[4]+"' where id = "+changeId+";");
				befehlAbfrage(st1, "UPDATE Person SET version = -1 where id = "+changeId+";");
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
}
