// Created by Vologda developer.
// Date: 16.11.2019
// Time: 15:03

package ru.belyaev.shop.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import java.net.URLEncoder;

public class URLEncodeTag extends SimpleTagSupport {
    private String var;
    private String url;


    @Override

    public void doTag() throws JspException, IOException {

        String encodedUrl = URLEncoder.encode(url, "UTF-8");
        getJspContext().setAttribute(var, encodedUrl);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
