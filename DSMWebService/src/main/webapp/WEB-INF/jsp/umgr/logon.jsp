<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><bean:message key="login.page.title" /></title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
<script type="text/javascript">
  if (top != self) {
    top.location=self.location;
  }
</script>
<script src="${pageContext.request.contextPath}/lib/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.4.1.js" type="text/javascript"></script>
<script>
  var $j = jQuery.noConflict();
</script>
<script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">
  function focusAlias() {
    $j('#alias').select().focus();
  }
  function focusPassword() {
    var aliasValue = $j('#alias').val();
    if (aliasValue && aliasValue.length > 0) {
      $j('#password').focus();
    } else {
      focusAlias();
    }
  }
  $j(document).ready(function() {
    ${userManagerSettings.rememberMeEnabled ? 'focusAlias();' : 'focusPassword();'}
  });
</script>
<style type="text/css">
  html, body {
    height: 100%;
    width:100%;
  }
  body {
    margin: 0 auto;
    text-align:center;
  }
  #maintable, #maintable tr, #maintable td {
    height: 100%;
    border:0;
    margin:0 auto;
  }
</style>
</head>
<body>
  <table id="maintable"><tr><td>
<html:form action="/logonUser" method="POST" styleId="loginform" >
    <table class="logonform" cellspacing="0" cellpadding="0" border="0" align="center">
      <caption class="global-background">
        <bean:message key="login.page.form.title" />
      </caption>
      <tr><td align="left"><label for="alias"><bean:message key="login.page.username" />:</label></td><td><html:text property="alias" styleId="alias" size="20" tabindex="1" /></td></tr>
      <tr><td align="left"><label for="password"><bean:message key="login.page.password" />:</label></td><td><html:password property="password" styleId="password" size="20" value="" tabindex="2" /></td></tr>
<c:if test="${userManagerSettings.rememberMeEnabled}">
      <tr><td colspan="2"><label><input type="checkbox" name="remember_me" value="true" tabindex="3" /> <bean:message key="login.page.remember.me" /></label></td></tr>
</c:if>
      <tr><td colspan="2" class="buttons"><html:submit tabindex="4"><bean:message key="login.page.submit" /></html:submit></td></tr>
      <tr>
        <td colspan="2" class="footer">
          <jsp:include page="/WEB-INF/jsp/umgr/login-footer.jsp" flush="true" />
        </td>
      </tr>
    </table>
</html:form>
<div id="loginMessages" style="margin: 10px 0;">
<logic:messagesPresent message="true">
<div class="msgMacro macroMargin">
  <html:messages id="message" message="true">
    <bean:write name="message" filter="false" ignore="true"/><br>
  </html:messages>
</div>
</logic:messagesPresent>
<logic:messagesPresent >
<div class="errorMacro macroMargin">
  <html:messages id="message" >
    <bean:write name="message" filter="false" ignore="true" /><br>
  </html:messages>
</div>
</logic:messagesPresent>
</div>
</td></tr></table>
</body>
</html>