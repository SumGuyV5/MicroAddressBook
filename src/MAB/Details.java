/************************************************************************
**  Program Name:   MAB (Micro Address Book)	      			**
**  Version Number: V1.0B                                     		**
**  Copyright (C):  March 2, 2010  Richard W. Allen			**
**  Date Started:   February 21, 2010	                    		**
**  Date Ended:     March 2, 2010	                 		**
**  Author:         Richard W. Allen                       		**
**  Webpage:        http://richard-allen.homelinux.com			**
**  IDE:            NetBeans 6.7.1					**
**  Compiler:	    Java 6 update 16					**
**  Langage:        Java ME						**
**  License:	    GNU GENERAL PUBLIC LICENSE Version 2		**
**		    see license.txt for for details	    		**
*************************************************************************/
package MAB;

/**
 * <p>Title: Micro Address Book</p>
 * <p>Description: Micro Address Book(MAB)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: </p>
 * @author Richard Allen
 * @version 1.0B Demo
 */

import javax.microedition.lcdui.*;

public class Details extends Form implements CommandListener  {
    private int id = 0;
    //Commands
    private Command backCommand = new Command("Back", Command.BACK, 1);
    private Command addCommand = new Command("Add", Command.OK, 2);
    private Command saveCommand = new Command("Save", Command.OK, 2);
    private Command deleteCommand = new Command("Delete", Command.OK, 2);
    //TextFields
    private TextField firstName = new TextField("First Name", "", 50, TextField.ANY);
    private TextField lastName = new TextField("Last Name", "", 50, TextField.ANY);
    private TextField email = new TextField("E-mail Address", "", 100, TextField.EMAILADDR);
    private TextField phone = new TextField("Telephone #", "", 22, TextField.PHONENUMBER);
    private TextField fax = new TextField("Fax #", "", 22, TextField.PHONENUMBER);
    private TextField address1 = new TextField("Address 1", "", 100, TextField.ANY);
    private TextField address2 = new TextField("Address 2", "", 100, TextField.ANY);
    private TextField city = new TextField("City", "", 100, TextField.ANY);
    private TextField provstate = new TextField("Province/Sate", "", 2, TextField.ANY);
    private TextField country = new TextField("Country", "", 100, TextField.ANY);
    private TextField postalCode = new TextField("Postal/ZIP Code", "", 20, TextField.ANY);

    private Database database = null;

    /** Constructor **/
    public Details(Database data) {
        super("New Contact");
        database = data;
        try {
            Init();
            addCommand(addCommand);
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in Details", "There was a error when initializing Details Class.  Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    public Details(Database data, int id) {
        super("Contact Details");
        database = data;
        this.id = id;
        try {
            Init();
            addCommand(saveCommand);
            addCommand(deleteCommand);
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in Details", "There was a error when initializing Details Class. Error Message "  + e.getMessage(), AlertType.ERROR);
        }

        try {
            Contacts contact = new Contacts(database.getContactRecord(this.id));
            firstName.setString(contact.getFirstName());
            lastName.setString(contact.getLastName());
            email.setString(contact.getEmail());
            phone.setString(contact.getPhone());
            fax.setString(contact.getFax());
            address1.setString(contact.getAddress1());
            address2.setString(contact.getAddress2());
            city.setString(contact.getCity());
            provstate.setString(contact.getProvState());
            country.setString(contact.getCountry());
            postalCode.setString(contact.getPostalCode());
            contact = null;
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in Details", "There was a error when reading from the database. Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    /** Methods **/
    /** Component initialization **/
    private void Init() throws Exception {
        // Set up this Displayable to listen to command events
        setCommandListener(this);
        // add the Exit command
        addCommand(backCommand);
        append(firstName);
        append(lastName);
        append(email);
        append(phone);
        append(fax);
        append(address1);
        append(address2);
        append(city);
        append(provstate);
        append(country);
        append(postalCode);
    }

    /** Handle command events **/
    public void commandAction(Command command, Displayable displayable) {
        /** @todo Add command handling code **/
        if (command == backCommand) {
        }else if(command == addCommand) {
            // Create a record for the contact and add it to the database
            Contacts contact = new Contacts(firstName.getString(), lastName.getString(), email.getString(), phone.getString(), fax.getString(),  address1.getString(), address2.getString(), city.getString(), provstate.getString(), country.getString(), postalCode.getString());
            database.AddContactRecord(contact.Pack());
            contact = null;
        }else if(command == saveCommand) {
            Contacts contact = new Contacts(firstName.getString(), lastName.getString(), email.getString(), phone.getString(), fax.getString(),  address1.getString(), address2.getString(), city.getString(), provstate.getString(), country.getString(), postalCode.getString());
            database.setContactRecord(id, contact.Pack());
            contact = null;
        }else if(command == deleteCommand) {
            database.DeleteContactRecord(id);
        }
        MAB.instance.Display();
    }
}
