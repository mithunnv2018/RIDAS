package com.ridas.web.convert;

import org.springframework.core.convert.converter.Converter;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConversionServiceFactoryBeanTest {

	private ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
	private Converter<String, String> converter;
	
	@Before
	public void before() {
		converter = conversionServiceFactoryBean.getStringToStringConverter();
	}
	
	@Test
	public void testConvertNull() {
		Assert.assertNull(converter.convert(null));
	}

	@Test
	public void testConvertEmpty1() {
		Assert.assertNull(converter.convert(""));
	}

	@Test
	public void testConvertEmpty2() {
		Assert.assertNull(converter.convert(" "));
	}

	@Test
	public void testConvertEmpty3() {
		Assert.assertNull(converter.convert("  "));
	}

	@Test
	public void testConvertNotEmpty1() {
		Assert.assertEquals("abc",converter.convert("abc"));
	}

	@Test
	public void testConvertNotEmpty2() {
		Assert.assertEquals("abc",converter.convert("  abc"));
	}

	@Test
	public void testConvertNotEmpty3() {
		Assert.assertEquals("abc",converter.convert("abc  "));
	}

	@Test
	public void testConvertNotEmpty4() {
		Assert.assertEquals("abc",converter.convert("   abc  "));
	}

}
