<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta  charset="UTF-8">
<title>Parts</title>
 <style>
         <%@include file="/WEB-INF/pages/style.css"%>
  </style>
</head>
<body>
<div>
 <a href="<c:url value="/"/>">На главную страницу</a>
    <hr />
<h1>Комплектующие для  ПК</h1>

<c:if test="${compsCount == 0}">
    <br />
    <p>Нет комплектующих</p>
    <br />
</c:if>

<c:if test="${!empty listComps}">
    <form action="<c:url value="/parts/search"/>" method="post">
        <input type="text" name="criterion" value="${search.criterion}" />
        <input type="submit" name="search"  value="Поиск" />
        <c:if test="${!empty search.criterion}">
            <a href="<c:url value="/parts/1"/>"><input type="button" name="clear" value="Сброс" /></a>
        </c:if>
    </form>
    <form action="<c:url value="/parts/1"/>" method="post"  id="selectform">
      <select name="necessary" id="selectOpns" form="selectform" >
                                 <option value="2" >все детали</option>
                                 <option value="0">опциональные детали</option>
                                 <option value="1">детали, которые необходимы для сборки</option>
      </select>
      <input type="submit" name="sort"  value="Сортировка" />
     </form>
</c:if>
    <br />
</div>

<div>
<c:if test="${!empty listComps}">
        <table >
            <tr>
             <th>ID</th>
                <th>Наименования</th>
                <th>Обязательно</th>
                <th>Количество</th>
                <th colspan="2"></th>
                 </tr>
            <c:forEach items="${listComps}" var="co">
            <tr>
               <td>${co.id}</td>
               <td>${co.name}</td>
                <td>
                    <c:choose>
                    <c:when test="${co.isNecessary== true}">Да</c:when>
                    <c:otherwise>Нет</c:otherwise>
                    </c:choose>
               </td>
               <td>
               ${co.amount}
               </td>
                  <td><a href="<c:url value="/edit/${co.id}"/>">Редактировать</a></td>
                  <td><a href="<c:url value="/delete/${co.id}"/>">Удалить</a></td>
              </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div>
<table>
    <tr>
        <td class="td_style">
        <c:if test="${!empty AmountOfFullPC}">
            <c:if test="${selected !=0}">
          <p>  Можно собрать: ${AmountOfFullPC} комплекта(ов)</p>
            </c:if>
        </c:if>
        </td>
    </tr>
</table>
</div>
<div>
    <c:if test="${empty search.criterion}">
        <c:if test="${!empty parts.name}">
            <h4>Редактировать детальку:</h4>
        </c:if>
        <c:if test="${empty parts.name}">
            <h4>Добавить детальку:</h4>
        </c:if>
        <c:url var="addAction" value="/parts/add" />
        <form:form action="${addAction}" commandName="parts">
            <table style="padding: 5px">
             <c:if test="${!empty parts.name}">
                                <tr>
                                    <td>
                                        <form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input path="id"  />
                                    </td>
                                </tr>
                            </c:if>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Наименование" />
                        </form:label>
                    </td>
                    <td><form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="isNecessary">
                            <spring:message text="Необходимость" />
                        </form:label>
                    </td>
                    <td><form:checkbox path="isNecessary" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="amount">
                            <spring:message text="Количество" />
                        </form:label>
                    </td>
                    <td><form:input path="amount"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <center>
                            <c:if test="${!empty parts.name}">
                               <input type="submit" value="<spring:message text="Редактировать детальку" />"
                             </c:if>
                             <c:if test="${empty parts.name}">
                             <input type="submit" value="<spring:message text="Добавить детальку" />"
                             </c:if>
                        </center>
                    </td>
                </tr>
            </table>
        </form:form>
    </c:if>
</div>
<div>
       <c:if test="${compsCount > 0}">
            <center>
                <table>
                    <tr>
                        <td>Страница:</td>
                        <c:forEach begin="${1}" end="${(compsCount/10) + (compsCount%10==0?0:1)}" var="i">
                            <td>
                            <c:choose>
                                                <c:when test="${selected == 2}">
                                               <a href="<c:url value="/parts/${i}" />"> ${i} </a>
                                                </c:when>
                                                <c:otherwise>
                                               <a href="#" onClick="sendForm('${i}')"> ${i} </a>
                                                </c:otherwise>
                             </c:choose>

                            </td>
                        </c:forEach>
                    </tr>
                </table>
            </center>
        </c:if>
</div>
<hr>




<script>
var s = document.getElementById("selectOpns");
s.value = ${selected};
function sendForm(page){
document.getElementById("selectform").action = '<c:url value="/parts/\'+page+\'"/>';
document.getElementById("selectform").submit();
}
</script>
</body>
</html>