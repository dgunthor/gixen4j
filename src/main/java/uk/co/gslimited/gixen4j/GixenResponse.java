package uk.co.gslimited.gixen4j;

import lombok.Getter;
import lombok.ToString;
import uk.co.gslimited.gixen4j.model.Gixen;

@ToString
public class GixenResponse {

	public static GixenResponse OK = new GixenResponse(GixenResult.OK);

	@Getter
	private final GixenResult result;

	@Getter
	@SuppressWarnings("unused")
	private final Gixen gixen;

	public GixenResponse(GixenResult result) {
		this(result, null);
	}

	public GixenResponse(GixenResult result, Gixen gixen) {
		this.result = result;
		this.gixen = gixen;
	}

	public boolean isError() {
		return result != null && ! result.equals(GixenResult.OK);
	}
}

