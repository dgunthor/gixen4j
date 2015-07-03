package uk.co.gslimited.gixen4j;

import lombok.Getter;
import lombok.ToString;

@ToString
public class GixenRequest {

	@Getter
	@SuppressWarnings("unused")
	private final String uri;

	public GixenRequest(String uri) {
		this.uri = uri;
	}
}

