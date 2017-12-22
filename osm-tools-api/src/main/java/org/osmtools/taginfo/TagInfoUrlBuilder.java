package org.osmtools.taginfo;

public class TagInfoUrlBuilder {

	private String baseUrl = "https://taginfo.openstreetmap.org/api/4";
	private String path;
	private StringBuilder params = new StringBuilder();

	public TagInfoUrlBuilder(String path) {
		this.path = path;
	}

	public TagInfoUrlBuilder append(String key, long value) {
		return append(key, String.valueOf(value));
	}

	public TagInfoUrlBuilder append(String key, String value) {
		if (params.length() != 0)
			params.append('&');
		params.append(key).append('=').append(value);
		return this;
	}

	public TagInfoUrlBuilder append(TagInfoSort sort, TagInfoSortOrder sortOrder) {
		if (sort != null) {
			append("sortname", sort.name().toLowerCase());
			append("sortorder", sortOrder.name().toLowerCase());
		}
		return this;
	}

	public TagInfoUrlBuilder append(Pagination pagination) {
		if (pagination != null) {
			append("page", pagination.getPage()).append("rp", pagination.getResultsPerPage());
		}
		return this;
	}

	public TagInfoUrlBuilder append(TagInfoFilter filter) {
		if (filter != null && filter != TagInfoFilter.ALL) {
			append("filter", filter.name().toLowerCase());
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder url = new StringBuilder(baseUrl);
		url.append(path);
		if (params.length() > 0)
			url.append('?').append(params);
		return url.toString();
	}
}
