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

public class Synchronization {
    //Variables
    private String url = "";
    private String username = "";
    private String password = "";

    /** Constructor **/
    public Synchronization(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Synchronization(byte[] data) {
        Unpack(new String(data));
    }

    /** Methods **/
    public String Pack() {
        return (url + ';' + username + ';' + password);
    }

    public void Unpack(String data) {
        int start = 0;
        int end = data.indexOf(';');
    //Name First
        url = data.substring(start, end);
    //Name Last
        start = end + 1;
        end = data.indexOf(';', start);
        username = data.substring(start, end);
    //Last Sync
        start = end + 1;
        password = data.substring(start, data.length());
    }

    public String getURL(){
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
