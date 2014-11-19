package janemair;

import org.apache.commons.cli2.*;
import org.apache.commons.cli2.builder.*;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.commandline.Parser;

/**
 * Eine Klasse die das öffnen des Programmes mittels Anfangsparameter ermoeglicht
 * 
 * @author Wolfgang Mair
 * @version 2014-11-12
 */
public class CLI {
	//Vorbereiten der Attribute
	private String server1 = "", server2 = "", user = "", user2="", pass = "", pass2= "",datenbank1 = "", datenbank2 = "", type1 = "" , type2 = "";

	/**
	 * Ein Konstruktor der Mittels eines String arrays Das Programm anfangswerte übermittlet
	 * 
	 * @param args Das String array mit den Informationen
	 * @throws OptionException Eine Exception die bei Optionen auftreten kann
	 */
	public CLI(String[] args){
		try{
			//Die Builder
			DefaultOptionBuilder dob = new DefaultOptionBuilder();
			ArgumentBuilder ab = new ArgumentBuilder();
			GroupBuilder gb = new GroupBuilder();

			//erstellen der Defaultoptions
			DefaultOption server_option1 = dob.withLongName("host1").withShortName("h1").withRequired(true).
					withDescription("Der 1 Hostname")
					.withArgument(ab.withName("host1").withMinimum(0).withMaximum(1).create()).create();

			//erstellen der Defaultoptions
			DefaultOption server_option2 = dob.withLongName("host2").withShortName("h2").withRequired(true).
					withDescription("Der 2 Hostname")
					.withArgument(ab.withName("host2").withMinimum(0).withMaximum(1).create()).create();

			DefaultOption user_option = dob.withLongName("user").withShortName("u").withRequired(true).
					withDescription("Der Username")
					.withArgument(ab.withName("user").withMinimum(0).withMaximum(1).create()).create();
			
			DefaultOption user2_option = dob.withLongName("user2").withShortName("u2").withRequired(true).
					withDescription("Der Username2")
					.withArgument(ab.withName("user2").withMinimum(0).withMaximum(1).create()).create();

			DefaultOption pass_option = dob.withLongName("password").withShortName("p").withRequired(false).
					withDescription("Das Passwort")
					.withArgument(ab.withName("password").withMinimum(0).withMaximum(1).create()).create();
			
			DefaultOption pass2_option = dob.withLongName("password2").withShortName("p2").withRequired(false).
					withDescription("Das Passwort2")
					.withArgument(ab.withName("password2").withMinimum(0).withMaximum(1).create()).create();

			DefaultOption datenbank_option1 = dob.withLongName("dbname1").withShortName("d1").withRequired(true).
					withDescription("Die 1 Datenbank")
					.withArgument(ab.withName("dbname1").withMinimum(0).withMaximum(1).create()).create();

			DefaultOption datenbank_option2 = dob.withLongName("dbname2").withShortName("d2").withRequired(true).
					withDescription("Die 2 Datenbank")
					.withArgument(ab.withName("dbname2").withMinimum(0).withMaximum(1).create()).create();
			
			DefaultOption type_option1 = dob.withLongName("type1").withShortName("s1").withRequired(true).
					withDescription("Der 1 Datenbanktyp")
					.withArgument(ab.withName("type1").withMinimum(0).withMaximum(1).create()).create();
			
			DefaultOption type_option2 = dob.withLongName("type2").withShortName("s2").withRequired(true).
					withDescription("Der 2 Datenbanktyp")
					.withArgument(ab.withName("type2").withMinimum(0).withMaximum(1).create()).create();

			//Groupen der Options
			Group options = gb.withName("options").withOption(server_option1).withOption(server_option2).withOption(user_option).
					withOption(pass_option).withOption(datenbank_option1).withOption(datenbank_option2).withOption(type_option1).withOption(type_option2).withOption(pass2_option).withOption(user2_option).create();

			//erstellen des Parsers und setzen der Gruppe
			Parser parser = new Parser();
			parser.setGroup(options);

			CommandLine cl = parser.parse(args);

			//Befuellen der Variablen
			server1 = (String)cl.getValue(server_option1);
			server2 = (String)cl.getValue(server_option2);

			user = (String)cl.getValue(user_option);
			user2 = (String)cl.getValue(user2_option);

			pass = (String)cl.getValue(pass_option);		
			pass2 = (String)cl.getValue(pass2_option);

			datenbank1 = (String)cl.getValue(datenbank_option1);	
			datenbank2 = (String)cl.getValue(datenbank_option2);

			type1 = (String)cl.getValue(type_option1);	
			type2 = (String)cl.getValue(type_option2);
		}

		catch(OptionException oe){
			System.out.println("[--host1|-h1] [--host2|-h2] [--user|-u]  [--user2|-u2] [--dbname1|-d1] [--dbname2|-d2] [--type1|-s1] [--type2|-s2] (--pass|-p) (--pass2|-p2)\n" +
					"Types: post, mysql, sqlite");
			oe.printStackTrace();
		}
	}


	/**
	 * Eine Getter Methode die den server Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Server
	 */
	public String getServer1() {
		return server1;
	}
	
	/**
	 * Eine Getter Methode die den server Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Server
	 */
	public String getServer2() {
		return server2;
	}

	/**
	 * Eine Getter Methode die den User Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei User
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Eine Getter Methode die den User Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei User
	 */
	public String getUser2() {
		return user2;
	}

	/**
	 * Eine Getter Methode die den Passwort Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Passwort
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * Eine Getter Methode die den Passwort Text zurueckgibt
	 * 
	 * @return   Der eingegebene Text bei Passwort
	 */
	public String getPass2() {
		return pass2;
	}

	/**
	 * Eine Getter Methode die den Datenbank Text zurueckgibt
	 * 
	 * @return    Der eingegebene Text bei Datenbank
	 */
	public String getDatenbank1() {
		return datenbank1;
	}
	
	/**
	 * Eine Getter Methode die den Datenbank Text zurueckgibt
	 * 
	 * @return    Der eingegebene Text bei Datenbank
	 */
	public String getDatenbank2() {
		return datenbank2;
	}

	/**
	 * Eine Getter Methode die den Datenbank Typ zurueckgibt
	 * 
	 * @return    Der eingegebene Text bei Type
	 */
	public String getType1() {
		return type1;
	}
	
	/**
	 * Eine Getter Methode die den Datenbank Typ zurueckgibt
	 * 
	 * @return    Der eingegebene Text bei Type
	 */
	public String getType2() {
		return type2;
	}
}
