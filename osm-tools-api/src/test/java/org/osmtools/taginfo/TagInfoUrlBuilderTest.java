package org.osmtools.taginfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TagInfoUrlBuilderTest {

	@Test
	public void testUrl() throws Exception {
		TagInfoUrlBuilder builder = new TagInfoUrlBuilder("/key/values");
		builder.append("key", "route");
		builder.append(TagInfoSort.COUNT_RELATIONS, TagInfoSortOrder.DESC);
		builder.append(new Pagination(1, 20));
		assertEquals("https://taginfo.openstreetmap.org/api/4/key/values?"
				+ "key=route&sortname=count_relations&sortorder=desc&page=1&rp=20", builder.toString());
	}
}
