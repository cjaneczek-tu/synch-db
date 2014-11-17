package janemair;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Eine Klasse die es ermoeglicht Dynamisch den Typ der Datenbank zu aendern und eine Datasource zu entwickeln
 * 
 * @author Wolfgang Mair
 * @version 12-12-2013
 */
public class FactorySQL {
	
	//Datasource Objekt von mysql
	/**
	 * Ein Konstruktor um ein Datasource Objekt von einem vorgeschriebenen typ zu benutzen
	 * 
	 * @param type Der angegebene Typ der Datenbank
	 * @param server Der Server
	 * @param database Die Datenbank
	 * @return   DataSource Objekt
	 */
	public DataSource createDataSource(String type,String server, String database){
		DataSource ds = null;
		if(type.toLowerCase().equals("mysql")){
			MysqlDataSource mds = new MysqlDataSource();
			mds.setServerName(server);
			mds.setDatabaseName(database);
			
			ds = mds;
		}
		//Datasource Objekt von postgres
		else if(type.toLowerCase().equals("post")){
			PGSimpleDataSource pds = new PGSimpleDataSource();
			pds.setServerName(server);
			pds.setDatabaseName(database);
			
			ds = pds;
		}
		//Datasource Ojekt von sqlite
		else if(type.toLowerCase().equals("sqlite")){
			SQLiteJDBCLoader.initialize();
			SQLiteDataSource sqll = new SQLiteDataSource();
			sqll.setUrl("jdbc:sqlite:/" + server +"/" + database);
			ds = sqll;
		}
			
		return ds;
	}
	
}
