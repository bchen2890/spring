<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <link rel="stylesheet" href="<spring:theme code='stylesheet'/>" type="text/css" />
        <title>Spring MVC ThemeResolver</title>
    </head>
    <body>
        <h1>Spring MVC ThemeResolver</h1>
        <h3>Applied theme: <spring:theme code='name'/> </h3>
        Change theme: <a href="?mytheme=light">Light</a> | <a href="?mytheme=dark">Dark</a>
    </body>
</html>