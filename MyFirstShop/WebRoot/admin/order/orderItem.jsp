<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table border="0" width="100%">
	<s:iterator var="oi" value="list">
	<tr>
		<td><img width="30px" height="40px" src="${pageContext.request.contextPath }/<s:property value="#oi.product.image"/>"></td>
		<td><s:property value="#oi.count"/></td>
		<td><s:property value="#oi.subtotal"/></td>
	</tr>
	</s:iterator>
</table>