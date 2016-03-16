<%@ tag body-content="scriptless" display-name="unnom" %>
<%@ attribute name="target" required="false" %>
<%@ attribute name="param" required="false" rtexprvalue="true" type="java.util.Map" %>
<%@ attribute name="classes" required="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
     prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
    prefix="fn" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<a type="button" class="btn btn-default ${classes}" href="${target}?"><jsp:doBody/></a>