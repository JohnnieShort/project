package com.itacademy.jd2.ikarotki.rwmanager.web.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class I18N extends SimpleTagSupport {
	public static final String SESSION_LOCALE_KEY = "current-locale";
	private static Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();
	private final Locale DEFAULT_LOCALE = new Locale("ru");
	private String key;

	@Override
	public void doTag() throws JspException, IOException {

		final JspContext jspContext = getJspContext();

		Locale locale = (Locale) jspContext.findAttribute(SESSION_LOCALE_KEY);
		if (locale == null) {
			locale = DEFAULT_LOCALE;
		}

		jspContext.getOut().println(getLocalized(key, locale));
	}

	private String getLocalized(String key, Locale locale) {
		ResourceBundle mybundle = bundles.get(locale);
		if (mybundle == null) {
			mybundle = ResourceBundle.getBundle("MyLabels", locale);
			bundles.put(locale, mybundle);
		}
		try {
			String string = mybundle.getString(key);
			return string;
		} catch (MissingResourceException e) {
			return key;
		}

	}

	public void setKey(final String key) {
		this.key = key;
	}

}