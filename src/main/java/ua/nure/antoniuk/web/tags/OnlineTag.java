package ua.nure.antoniuk.web.tags;

import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.web.listeners.SessionListener;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class OnlineTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(((SessionListener)pageContext.getSession().getAttribute(Constants.COUNTER)).getCountUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
