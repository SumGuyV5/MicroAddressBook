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
import java.util.*;
import javax.microedition.rms.*;

public class Menu extends List implements CommandListener {
    //Commands
    private Command exitCommand = new Command("Exit", Command.EXIT, 1);
    private Command addCommand = new Command("Add Contact", Command.SCREEN, 2);
    private Command deleteCommand = new Command("Delete Contact", Command.SCREEN, 2);
    private Command synchronizeCommand = new Command("Synchronize MAB", Command.OK, 2);
    private Command helpCommand = new Command("Help", Command.OK, 3);

    //Custom Class's
    private Details details = null;
    private HelpOptions help = null;
    private Database database = new Database("Contacts");
    private DisplaySynchronization synchronizeDisplay = null;

    private Vector contactIDs = new Vector();

    /** Constructor **/
    public Menu() {
        super("Contact List", List.IMPLICIT);
        MAB.instance.Display(this);
        try {
            Init();
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in Menu.", "There was a error when initializing Menu Class. Error Message "  + e.getMessage(), AlertType.ERROR);
        }
    }

    /**Component initialization*/
    private void Init() throws Exception {
        contactIDs.removeAllElements();
        // Set up this Displayable to listen to command events
        setCommandListener(this);
        // add the Exit command
        addCommand(exitCommand);
        addCommand(addCommand);
        addCommand(deleteCommand);
        addCommand(synchronizeCommand);
        addCommand(helpCommand);

        // Read through the database and build a list of record IDs
        RecordEnumeration records = null;

        try {
            records = database.EnumerateContactRecords();
            while(records.hasNextElement())
                contactIDs.addElement(new Integer(records.nextRecordId()));
        }
        catch(Exception e) {
            System.err.println("EXCEPTION: Problem reading the contact records.");
        }


        try {
            records.reset();

            Contacts contact = null;
            if (records.hasNextElement()) {
                contact = new Contacts(records.nextRecord());
                append(contact.getFirstName() + " " + contact.getLastName(), null);

                while(records.hasNextElement()) {
                    contact.Unpack(records.nextRecord());
                    append(contact.getFirstName() + " " + contact.getLastName(), null);
                }
            }
            contact = null;
        }
        catch(Exception e) {
            MAB.instance.Alert("Error in Menu", "There was a error when reading from the database. Error Message "  + e.getMessage(), AlertType.ERROR);
        }
        records = null;
    }

    /** Handle command events **/
    public void commandAction(Command command, Displayable displayable) {
        /** @todo Add command handling code **/
        if (command.getCommandType() == Command.EXIT) {
            // stop the MIDlet
            MAB.quitApp();
        }else if (command == List.SELECT_COMMAND) {
            int id = ((Integer)contactIDs.elementAt(this.getSelectedIndex())).intValue();
            details = new Details(database,id);
            MAB.instance.Display(details);
        }else if (command == addCommand) {
            details = new Details(database);
            MAB.instance.Display(details);
        }else if (command == deleteCommand) {
            try {
                int index = getSelectedIndex();
                if (index >= 0) {
                    int id = ( (Integer) contactIDs.elementAt(index)).intValue();
                    database.DeleteContactRecord(id);
                    delete(index);
                    this.deleteAll();
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                MAB.instance.Alert("WARNING!", "WARNING! There is no Contact to delete!", AlertType.WARNING);
            }

            try {
                Init();
            }
            catch(Exception e) {
                MAB.instance.Alert("Error in cList ", "There was a error when initializing cList Class. Error Message "  + e.getMessage(), AlertType.ERROR);
            }

            MAB.instance.Display(this);
        }else if (command == synchronizeCommand) {
            synchronizeDisplay = new DisplaySynchronization();
            MAB.instance.Display(synchronizeDisplay);
        }else if (command == helpCommand) {
            help = new HelpOptions();
            MAB.instance.Display(help);
        }
    }
}
