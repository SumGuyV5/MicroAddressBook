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

import java.util.*;

public class Contacts {
    //Variables
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String fax = "";
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String provstate = "";
    private String country = "";
    private String postalCode = "";

    private Date dateCreated = new Date();
    private Date lastModified = new Date();
    private Date lastSync = null;

    /** Constructor **/
    public Contacts(String firstname, String lastname, String email, String phone, String fax, String address1, String address2, String city, String provstate, String country, String postalcode ) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.provstate = provstate;
        this.country = country;
        this.postalCode = postalcode;
        this.fax = fax;
    }

    public Contacts(byte[] data) {
        Unpack(data);
    }

    /** Methods **/
    public String Pack() {
        if (lastSync == null) {
            lastSync = new Date();
            lastSync.setTime(0);
        }
        lastModified = new Date();

        return (firstName + ';' + lastName + ';' + email + ';' + phone + ';' + fax + ';' + address1 + ';' + address2 + ';' + city + ';' + provstate + ';' + country + ';' + postalCode + ';' + Long.toString(dateCreated.getTime()) + ';' + Long.toString(lastModified.getTime()) + ';' + Long.toString(lastSync.getTime()));
    }

    public void Unpack(byte[] data) {
        Unpack(new String(data));
    }

    public void Unpack(String data) {
        int start = 0;
        int end = data.indexOf(';');
      //Name First
        firstName = data.substring(start, end);
      //Name Last
        start = end + 1;
        end = data.indexOf(';', start);
        lastName = data.substring(start, end);
      //Email
        start = end + 1;
        end = data.indexOf(';', start);
        email = data.substring(start, end);
      //Phone
        start = end + 1;
        end = data.indexOf(';', start);
        phone = data.substring(start, end);
      //fax
        start = end + 1;
        end = data.indexOf(';', start);
        fax = data.substring(start, end);
      //Address 1
        start = end + 1;
        end = data.indexOf(';', start);
        address1 = data.substring(start, end);
      //Address 2
        start = end + 1;
        end = data.indexOf(';', start);
        address2 = data.substring(start, end);
      //City
        start = end + 1;
        end = data.indexOf(';', start);
        city = data.substring(start, end);
      //Province/Sate
        start = end + 1;
        end = data.indexOf(';', start);
        provstate = data.substring(start, end);
      //Country
        start = end + 1;
        end = data.indexOf(';', start);
        country = data.substring(start, end);
      //Postal Code
        start = end + 1;
        end = data.indexOf(';', start);
        postalCode = data.substring(start, end);
      //Date Created
        start = end + 1;
        end = data.indexOf(';', start);
        dateCreated.setTime(Long.parseLong(data.substring(start, end)));
      //Last Modified
        start = end + 1;
        end = data.indexOf(';', start);
        lastModified.setTime(Long.parseLong(data.substring(start, end)));
      //Last Sync
        start = end + 1;
        lastSync = new Date();
        lastSync.setTime(Long.parseLong(data.substring(start, data.length())));
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getProvState() {
        return provstate;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public Date getLastSync() {
        return lastSync;
    }
}
