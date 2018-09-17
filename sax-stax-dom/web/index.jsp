<%@ page language ="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title> Insert title here </title>

</head>
<body>
<form action="controller" method="post">
  <input type="hidden" name="command" value="DOM" />
  <input type="submit" name="submit" value="DOM"/><br/>
</form>
<form action="controller" method="post">
  <input type="hidden" name="command" value="SAX" />
  <input type="submit" name="submit" value="SAX"/><br/>
</form>
<form action="controller" method="post">
  <input type="hidden" name="command" value="STAX" />
  <input type="submit" name="submit" value="StAX"/><br/>
</form>
<form action="controller" method="post">
</form>

</body>
</html>
