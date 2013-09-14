package org.osmtools.osmchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

public class ReadOnlyGzipJaxb2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	private final ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class, JAXBContext>(64);

	public ReadOnlyGzipJaxb2HttpMessageConverter() {
		super(new MediaType("application", "x-gzip"));
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return (clazz.isAnnotationPresent(XmlRootElement.class) || clazz.isAnnotationPresent(XmlType.class))
				&& canRead(mediaType);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		throw new UnsupportedOperationException();
	}

	protected Object readFromSource(Class<?> clazz, HttpHeaders headers, Source source) throws IOException {
		try {
			Unmarshaller unmarshaller = createUnmarshaller(clazz);
			if (clazz.isAnnotationPresent(XmlRootElement.class)) {
				return unmarshaller.unmarshal(source);
			}
			else {
				JAXBElement jaxbElement = unmarshaller.unmarshal(source, clazz);
				return jaxbElement.getValue();
			}
		}
		catch (UnmarshalException ex) {
			throw new HttpMessageNotReadableException("Could not unmarshal to [" + clazz + "]: " + ex.getMessage(), ex);
		}
		catch (JAXBException ex) {
			throw new HttpMessageConversionException("Could not instantiate JAXBContext: " + ex.getMessage(), ex);
		}
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		InputStream in = new GZIPInputStream(inputMessage.getBody());
		return readFromSource(clazz, inputMessage.getHeaders(), new StreamSource(in));
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		throw new UnsupportedOperationException();
	}

	protected final Unmarshaller createUnmarshaller(Class clazz) throws JAXBException {
		try {
			JAXBContext jaxbContext = getJaxbContext(clazz);
			return jaxbContext.createUnmarshaller();
		}
		catch (JAXBException ex) {
			throw new HttpMessageConversionException("Could not create Unmarshaller for class [" + clazz + "]: "
					+ ex.getMessage(), ex);
		}
	}

	protected final JAXBContext getJaxbContext(Class clazz) {
		Assert.notNull(clazz, "'clazz' must not be null");
		JAXBContext jaxbContext = this.jaxbContexts.get(clazz);
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(clazz);
				this.jaxbContexts.putIfAbsent(clazz, jaxbContext);
			}
			catch (JAXBException ex) {
				throw new HttpMessageConversionException("Could not instantiate JAXBContext for class [" + clazz
						+ "]: " + ex.getMessage(), ex);
			}
		}
		return jaxbContext;
	}
}
