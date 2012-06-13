package org.osmtools.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.osm.schema.Osm;
import org.osm.schema.OsmChangeset;

@Path("/api/0.6")
public interface OsmApiChangesets {

	@PUT
    @Path("/changeset/create")
	public long createChangeset(Osm osm);

	@GET
    @Path("/changeset/{id}")
	public long getChangeset(@PathParam("id") long id);

	@PUT
	@Path("/changeset/{id}/close")
	public long closeChangeset(@PathParam("id") long id);

	@GET
	@Path("/changeset/{id}/download")
	public OsmChangeset downloadChangeset(@PathParam("id") long id);

	@POST
	@Path("/changeset/#id/expand_bbox")
	public Osm expandBoundingBox(@PathParam("id") long id);

	//todo add GET /api/0.6/changesets
	//todo add /api/0.6/changeset/#id/upload	
}