package org.osmtools.taginfo;

public class Pagination {

	private int page;
	private int resultsPerPage;

	public Pagination(int page, int resultsPerPage) {
		this.page = page;
		this.resultsPerPage = resultsPerPage;
	}

	public int getPage() {
		return page;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}
}
