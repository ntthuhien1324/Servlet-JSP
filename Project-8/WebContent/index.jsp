<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UploadFile</title>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data">
		File: <input type="file" name="file"/><br/>
		<input type="submit" value="Submit" name="btnUpload" />
	</form>
</body>
</html>