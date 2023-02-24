<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Details</title>
</head>
<body>
	<jsp:useBean id="product" class="com.bharath.trainings.jsp.Product">
		<jsp:setProperty name="product" property="*" />
	</jsp:useBean>

	Product Details
	<br /> Id:<jsp:getProperty property="id" name="product" />
	<br /> Name:<jsp:getProperty property="name" name="product" />
	<br /> Description:<jsp:getProperty property="description"
		name="product" />
	<br /> Id:<jsp:getProperty property="price" name="product" />
</body>
</html>