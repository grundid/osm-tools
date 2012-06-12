package org.osmtools.taginfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class TagInfoTemplate {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestOperations restOperations;

	public KeysValuesResponse getValuesWithKey(String key, TagInfoFilter filter, TagInfoSort sort, Pagination pagination) {
		TagInfoUrlBuilder builder = new TagInfoUrlBuilder("/db/keys/values");
		builder.append("key", key).append(pagination).append(filter).append(sort, TagInfoSortOrder.DESC);
		String url = builder.toString();
		log.info(url);
		return restOperations.getForObject(url, KeysValuesResponse.class);
	}

}
