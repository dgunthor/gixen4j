package uk.co.gslimited.gixen4j.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * POJO representing item xml element.
 *
<item>
	<itemid>311393998273</itemid>
	<endtime>1436035598</endtime>
	<maxbid>0.01</maxbid>
	<currentbid>0.01</currentbid>
	<status>N</status>
	<message></message>
	<title>Hot-selling Fashion Unisex Currency Notes Pattern Pound Purse Wallet</title>
	<snipegroup>0</snipegroup>
	<bidoffset>6</bidoffset>
	<comment></comment>
	<currency>GBP</currency>
	<imageurl>http://i.ebayimg.com/00/s/NTY0WDYwNw==/z/JTQAAOSwY45UOAEI/$_35.JPG</imageurl>
</item>
*/
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
@ToString
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private long itemid;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private long endtime;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private double maxbid;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private double currentbid;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String status;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String message;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String title;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private int snipegroup;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private int bidoffset;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String comment;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String currency;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private String imageurl;
}
