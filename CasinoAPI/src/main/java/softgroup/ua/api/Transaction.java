/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.api;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexander
 */
@XmlRootElement
public class Transaction {
    @XmlElement(required = true)
    public String transactionId;
    @XmlElement(required = true)
    public String loginId;
    @XmlElement(required = true)
    public String dateTime;
    @XmlElement(required = true)
    public String amount;
    @XmlElement
    public String info;
}
