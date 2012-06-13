package org.osmtools.api.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.osm.schema.Osm;

@Path("/api/0.6")
public interface OsmApiElements {

	@PUT
	@Path("/node/create")
	public long putNewNode(Osm osm);
	
	@PUT
	@Path("/way/create")
	public long putNewWay(Osm osm);
	
	@PUT
	@Path("/relation/create")
	public long putNewRelation(Osm osm);
	
	@PUT
	@Path("/node/{id}")
	public long putNode(@PathParam("id") long id, Osm osm);
	
	@PUT
	@Path("/way/{id}")
	public long putWay(@PathParam("id") long id, Osm osm);
	
	@PUT
	@Path("/relation/{id}")
	public long putRelation(@PathParam("id") long id, Osm osm);
	
	@GET
	@Path("/node/{id}")
	public Osm getNode(@PathParam("id") long id);
	
	@GET
	@Path("/way/{id}")
	public Osm getWay(@PathParam("id") long id);
	
	@GET
	@Path("/relation/{id}")
	public Osm getRelation(@PathParam("id") long id);
	
	@DELETE
	@Path("/node/{id}")
	public long deleteNode(@PathParam("id") long id, Osm osm);

	@DELETE
	@Path("/way/{id}")
	public long deleteWay(@PathParam("id") long id, Osm osm);

	@DELETE
	@Path("/relation/{id}")
	public long deleteRelation(@PathParam("id") long id, Osm osm);
}