/** Copyright (c) 2010 Scott A. Crosby. <scott@sacrosby.com>

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Lesser General Public License as 
   published by the Free Software Foundation, either version 3 of the 
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package crosby.binary;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;

import crosby.binary.file.BlockReaderAdapter;
import crosby.binary.file.FileBlock;
import crosby.binary.file.FileBlockPosition;

public abstract class BinaryParser implements BlockReaderAdapter {

	@Override
	public void handleBlock(FileBlock message) {
		try {
			if (message.getType().equals("OSMHeader")) {
				Osmformat.HeaderBlock headerblock = Osmformat.HeaderBlock.parseFrom(message.getData());
				parse(headerblock);
			}
			else if (message.getType().equals("OSMData")) {
				Osmformat.PrimitiveBlock primblock = Osmformat.PrimitiveBlock.parseFrom(message.getData());
				parse(primblock);
			}
		}
		catch (InvalidProtocolBufferException e) {
			throw new RuntimeException("ParseError");
		}
	}

	@Override
	public boolean skipBlock(FileBlockPosition block) {
		if (block.getType().equals("OSMData"))
			return false;
		if (block.getType().equals("OSMHeader"))
			return false;
		System.out.println("Skipped block of type: " + block.getType());
		return true;
	}

	/** Parse a Primitive block (containing a string table, other paramaters, and PrimitiveGroups */
	public void parse(Osmformat.PrimitiveBlock block) {
		String[] strings = readStrings(block);

		OsmObjectFactory context = new OsmObjectFactory(block.getGranularity(), block.getLatOffset(),
				block.getLonOffset(), block.getDateGranularity(), strings);

		for (Osmformat.PrimitiveGroup groupmessage : block.getPrimitivegroupList()) {
			// Exactly one of these should trigger on each loop.
			parseNodes(groupmessage.getNodesList(), context);
			parseWays(groupmessage.getWaysList(), context);
			parseRelations(groupmessage.getRelationsList(), context);
			if (groupmessage.hasDense())
				parseDense(groupmessage.getDense(), context);
		}
	}

	private String[] readStrings(Osmformat.PrimitiveBlock block) {
		Osmformat.StringTable stablemessage = block.getStringtable();
		String[] strings = new String[stablemessage.getSCount()];

		for (int i = 0; i < strings.length; i++) {
			strings[i] = stablemessage.getS(i).toStringUtf8();
		}
		return strings;
	}

	/** Parse a list of Relation protocol buffers and send the resulting relations to a sink. */
	protected abstract void parseRelations(List<Osmformat.Relation> rels, OsmObjectFactory blockContext);

	/** Parse a DenseNode protocol buffer and send the resulting nodes to a sink. */
	protected abstract void parseDense(Osmformat.DenseNodes nodes, OsmObjectFactory blockContext);

	/** Parse a list of Node protocol buffers and send the resulting nodes to a sink. */
	protected abstract void parseNodes(List<Osmformat.Node> nodes, OsmObjectFactory blockContext);

	/** Parse a list of Way protocol buffers and send the resulting ways to a sink. */
	protected abstract void parseWays(List<Osmformat.Way> ways, OsmObjectFactory blockContext);

	/** Parse a header message. */
	protected abstract void parse(Osmformat.HeaderBlock header);

}