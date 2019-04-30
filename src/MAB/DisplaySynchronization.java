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

public class DisplaySynchronization extends Form implements CommandListener {
    private Command backCommand = new Command("Back", Command.BACK, 1);
    private Command synchronizeCommand = new Command("Synchronize", Command.OK, 1);

    private TextField url = new TextField("URL", "http://", 50, TextField.URL);
    private TextField username = new TextField("Username", "", 20, TextField.ANY);
    private TextField password = new TextField("Password", "", 20, TextField.PASSWORD);

    private Database database = new Database("synchronize");
    private ProgressSynchronization synchronizationProgress = null;

    /** Constructor **/
    public DisplaySynchronization() {
        super("Synchronizing");
        try {
            Init();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** Methods **/
    /** Component initialization **/
    private void Init() throws Exception {
        // Set up this Displayable to listen to command events
        setCommandListener(this);
        // add the Exit command
        addCommand(backCommand);
        addCommand(synchronizeCommand);

        append(url);
        append(username);
        append(password);
    }

    /** Handle command events **/
    public void commandAction(Command command, Displayable displayable) {
        /** @todo Add command handling code **/
        if (command == backCommand) {
            MAB.instance.Display();
        }else if (command == synchronizeCommand) {
            //synchronizationProgress = new ProgressSynchronization(database);
            //MAB.instance.Display(synchronizationProgress);
        }
    }
}
