package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class GenericReply {
    @XmlElement(required=true)
    public Integer retcode = 0;
    @XmlElement(required=true)
    public String apiVer = "0.0.1";
    @XmlElement(required=false)
    public String error_message;
}
