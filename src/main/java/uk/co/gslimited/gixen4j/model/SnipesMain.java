package uk.co.gslimited.gixen4j.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * POJO representing snipesmain xml element.
 *
<snipesmain>
	<item>
	...
	</item>
	<item>
	...
	</item>
</snipesmain>
*/
@XmlRootElement(name = "snipesmain")
@XmlAccessorType(XmlAccessType.NONE)
@ToString
public class SnipesMain implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "item")
	@Getter
	@Setter
	@SuppressWarnings("unused")
	private List<Item> items;
}

