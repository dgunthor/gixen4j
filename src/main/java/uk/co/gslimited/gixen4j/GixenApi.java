package uk.co.gslimited.gixen4j;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import uk.co.gslimited.gixen4j.model.Gixen;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GixenApi {

	public static final String GIXEN_URL = "https://www.gixen.com/xmlapi.php";

	@Getter
	@Setter
	private String gixenUrl;

	@Getter
	@Setter
	private String username;

	@Setter
	private String password;

	public GixenApi(String username, String password) {
		this(GIXEN_URL, username, password);
	}

	public GixenApi(String gixenUrl, String username, String password) {
		this.gixenUrl = gixenUrl;
		this.username = username;
		this.password = password;
	}

	public GixenResponse authenticate() {

		GixenResponse response;

		GixenRequest request = new GixenRequestBuilder(gixenUrl).username(username).password(password).build();

		RestTemplate template = new RestTemplate();
		ResponseEntity<?> httpResponse = template.exchange(request.getUri(), HttpMethod.GET, null, String.class);

		String strResponse = (String) httpResponse.getBody();

		if (strResponse.trim().endsWith("<gixen>")) {
			// if ok, we get a malformed response of:
			// <?xml version="1.0" encoding="UTF-8" ?>
			// <gixen>
			log.info("result: OK");
			response = GixenResponse.OK;
		} else {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Gixen.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				Gixen gixen = (Gixen) unmarshaller.unmarshal(new StringReader(strResponse));
				response = new GixenResponse(parseGixenResult(gixen), gixen);
			} catch (JAXBException e) {
				log.error("Error parsing Gixen response", e);
				response = new GixenResponse(GixenResult.ERROR_INTERNAL_GIXEN4J_ERROR);
			}
		}

		return response;
	}

	public GixenResponse getSnipesMain() {

		GixenRequest request = new GixenRequestBuilder(gixenUrl).username(username).password(password).listSnipes().build();
		return send(request);
	}

	public GixenResponse getSnipesMirror() {

		GixenRequest request = new GixenRequestBuilder(gixenUrl).username(username).password(password).listSnipesMirror().build();
		return send(request);
	}

	public GixenResponse addSnipe(String ebayItemId, double maxBid) {

		GixenRequest request = new GixenRequestBuilder(gixenUrl).username(username).password(password).addSnipe(ebayItemId, maxBid).build();
		return send(request);
	}

	public GixenResponse deleteSnipe(String ebayItemId) {

		GixenRequest request = new GixenRequestBuilder(gixenUrl).username(username).password(password).deleteSnipe(ebayItemId).build();
		return send(request);
	}

	private GixenResponse send(GixenRequest request) {

		log.info("send {}", request);

		// gixen is using content type text/html for xml response
		// JAXB fails to handle the response, this works around it
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.TEXT_HTML);
		jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
		converters.add(jaxbMessageConverter);

		RestTemplate template = new RestTemplate();
		template.setMessageConverters(converters);

		ResponseEntity<?> httpResponse = template.exchange(request.getUri(), HttpMethod.GET, null, Gixen.class);
		log.debug("send request {} response. status code: {} body: {}", request, httpResponse.getStatusCode().toString(), httpResponse.getBody());
		log.debug("response. hasBody: {}", httpResponse.hasBody());
		Gixen gixen = (Gixen) httpResponse.getBody();
		if (httpResponse.hasBody()) {
			log.debug("response {}", gixen);
		}

		return new GixenResponse(parseGixenResult(gixen), gixen);
	}

	private static GixenResult parseGixenResult(Gixen gixen) {
		if (gixen == null) {
			return null;
		}

		if (gixen.getStatus().equals("OK")) {
			log.info("result: OK");
			return GixenResult.OK;
		} else {
			GixenResult result = GixenResult.parse(gixen.getMessage());
			log.info("result: {}", result);
			return result;
		}
	}
}

