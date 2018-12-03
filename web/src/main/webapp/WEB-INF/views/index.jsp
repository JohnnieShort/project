<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<html>
<head>
<style>
body {
    background-image: url("https://www.cairnsholidayspecialists.com.au/shared_resources/media/kuranda-scenic-railway-15872_1024x768.jpg");
    background-color: #cccccc; /* Used if the image is unavailable */
  	height: 768px; /* You must set a specified height */
 	background-position: center; /* Center the image */
  	background-repeat: no-repeat; /* Do not repeat the image */
  	/*background-size: cover;*/
}
.no-background {
    background-image: url("images/blank.jpg");
}
</style>
</head>
<body>
<div><h1 valign = center><mytaglib:i18n key="index.title.header"/></h1></div>
</body>
</html>