package com.ivk23.reactive.app.utils;

/**
 * @author Ivan Kos
 */
public class HtmlHelper {

    private StringBuilder contentBuilder;

    public HtmlHelper() {
        contentBuilder = new StringBuilder();
        contentBuilder.append("<!DOCTYPE HTML>");
        contentBuilder.append("<html>");
        contentBuilder.append("<head>");
        contentBuilder.append("<title>Simple web app</title>");
        contentBuilder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        contentBuilder.append("</head>");
        contentBuilder.append("<body>");
    }

    public String getHtml() {
        contentBuilder.append("</body>");
        contentBuilder.append("</html>");
        return contentBuilder.toString();
    }

    public HtmlHelper addContent(final String text) {
        this.contentBuilder.append(text);
        return this;
    }
}
