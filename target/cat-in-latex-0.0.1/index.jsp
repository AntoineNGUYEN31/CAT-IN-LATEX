<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>BASE64</title>
</head>
<body style="background-image: url('background.jpg');">
  <form action="process" method="post">
    <p>
        <textarea name="input1" rows="10" cols="50">${preinput1}</textarea>
    </p>
    <p>
      <input type="submit" name="encode" value="Encode">
      <input type="submit" name="decode" value="Decode">
    </p>
    <p>
      <textarea name="res1"rows="10" cols="50">${res1}</textarea>
    </p>
  </form>
</body>
