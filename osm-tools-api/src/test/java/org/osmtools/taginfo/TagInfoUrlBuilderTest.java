package org.osmtools.taginfo;

import static org.junit.Assert.*;

import org.junit.Test;

public class TagInfoUrlBuilderTest {

	@Test
	public void testUrl() throws Exception {
		TagInfoUrlBuilder builder = new TagInfoUrlBuilder("/db/keys/values");

		builder.append("key", "route");
		builder.append(TagInfoSort.COUNT_RELATIONS, TagInfoSortOrder.DESC);
		builder.append(new Pagination(1, 20));

		assertEquals("http://taginfo.openstreetmap.org/api/2/db/keys/values?"
				+ "key=route&sortname=count_relations&sortorder=desc&page=1&rp=20", builder.toString());
	}
}
