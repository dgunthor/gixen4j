package uk.co.gslimited.gixen4j.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * POJO for gixen xml element.
 *
<?xml version="1.0" encoding="UTF-8" ?>
<gixen>
	<status>OK</status>
	<snipesmain>
	...
	</snipesmain>
	<snipesmirror>
	...
	</snipesmirror>
</gixen>
*/
@XmlRootElement(name = "gixen")
@XmlAccessorType(XmlAccessType.NONE)
@ToString
public class Gixen implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private SnipesMain snipesmain;

	@XmlElement
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private SnipesMirror snipesmirror;
}

