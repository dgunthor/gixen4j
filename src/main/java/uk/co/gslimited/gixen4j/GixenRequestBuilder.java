package uk.co.gslimited.gixen4j;

import java.util.HashMap;
import java.util.Map;

public class GixenRequestBuilder {

	private String gixenUrl;

	private Map<String, String> parameters;

	public GixenRequestBuilder(String gixenUrl) {
		this.gixenUrl = gixenUrl;
		parameters = new HashMap<>();
	}

	public GixenRequestBuilder username(String username) {
		parameters.put("username", username);
		return this;
	}

	public GixenRequestBuilder password(String password) {
		parameters.put("password", password);
		return this;
	}

	public GixenRequestBuilder listSnipes() {
		parameters.put("listsnipesmain", "1");
		return this;
	}

	public GixenRequestBuilder listSnipesMirror() {
		parameters.put("listsnipesmirror", "1");
		return this;
	}

	public GixenRequestBuilder addSnipe(String ebayItemId, double maxBid) {
		parameters.put("itemid", ebayItemId);
		parameters.put("maxbid", "" + maxBid);
		return this;
	}

	public GixenRequestBuilder deleteSnipe(String ebayItemId) {
		parameters.put("ditemid", ebayItemId);
		return this;
	}

	public GixenRequest build() {
		StringBuilder url = new StringBuilder(gixenUrl).append("?");

		boolean first = true;
		for (Map.Entry<String, String> parameter : parameters.entrySet()) {
			if (first) {
				first = false;
			} else {
				url.append("&");
			}
			url.append(parameter.getKey()).append("=").append(parameter.getValue());
		}

		return new GixenRequest(url.toString());
	}
}

