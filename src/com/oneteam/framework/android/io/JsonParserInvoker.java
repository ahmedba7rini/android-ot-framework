package com.oneteam.framework.android.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.util.JsonReader;

import com.oneteam.framework.android.utils.Logger;

public class JsonParserInvoker<T> {

	public List<T> parseNodes(InputStream in, Parser<T> parser)
			throws UnsupportedEncodingException, IOException {

		if (in == null) {

			Logger.w("Invalid input stream");

			return null;
		}

		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

		try {

			return parseNodes(reader, parser);

		} finally {

			reader.close();
		}
	}

	protected List<T> parseNodes(JsonReader reader, Parser<T> parser)
			throws IOException {

		List<T> nodes = parser.execute(reader);

		return nodes;
	}

	// XXX
	// Remove the following code

	// protected List<IJsonNode> parseNodes(JsonReader reader, Parser parser)
	// throws IOException {
	//
	// List<IJsonNode> nodes = null;
	//
	// JsonToken token = reader.peek();
	//
	// if (token == JsonToken.BEGIN_ARRAY) {
	//
	// nodes = parseArray(reader, parser);
	//
	// } else if (token == JsonToken.BEGIN_OBJECT) {
	//
	// nodes = new ArrayList<IJsonNode>();
	//
	// IJsonNode node = parseObject(reader, parser);
	//
	// nodes.add(node);
	// } else {
	// reader.skipValue();
	// }
	//
	// return nodes;
	// }
	//
	// /**
	// * @param reader
	// * @param parser
	// * @throws IOException
	// */
	// protected IJsonNode parseObject(JsonReader reader, Parser parser)
	// throws IOException {
	//
	// IJsonNode node = null;
	//
	// reader.beginObject();
	//
	// while (reader.hasNext()) {
	//
	// node = parser.execute(reader);
	// }
	//
	// reader.endArray();
	//
	// return node;
	// }
	//
	// /**
	// * @param reader
	// * @param parser
	// * @throws IOException
	// */
	// protected List<IJsonNode> parseArray(JsonReader reader, Parser parser)
	// throws IOException {
	//
	// List<IJsonNode> nodes = new ArrayList<IJsonNode>();
	//
	// reader.beginArray();
	//
	// while (reader.hasNext()) {
	//
	// IJsonNode node = null;
	//
	// node = parser.execute(reader);
	//
	// nodes.add(node);
	// }
	//
	// reader.endArray();
	//
	// return nodes;
	// }

}
