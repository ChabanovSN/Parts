<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta  charset="UTF-8">
    <title>Parts</title>
     <style>
         <%@include file="/WEB-INF/pages/style.css"%>
     </style>
</head>
<body>
  <header>
      <h1>Тестовое задание для JavaRush </h1>
  </header>
  <br />
  <div>
      <p>Тестовое приложения работает с английским и русский шрифтом <br>
      Рассчет минимального количество доступных для сборки деталей ПК <br>
      происходит при условии что все детали совместимы и уникальны    <br>
       </p>
      <p>Что бы начать работать перейдите по ссылке: <a href="<c:url value="/parts/1"/>">Parts operations</a>.</p>

  </div>
</body>
</html>