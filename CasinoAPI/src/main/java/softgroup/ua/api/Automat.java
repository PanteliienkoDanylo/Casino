package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class Automat {
    @XmlElement
    public Integer automatId;
    @XmlElement(required=true)
    public String automatName;
    @XmlElement(required=true)
    public String description;
    @XmlElement
    public List<Integer> slots = new ArrayList<>();
    @XmlElement
    public Boolean isWon;
}
