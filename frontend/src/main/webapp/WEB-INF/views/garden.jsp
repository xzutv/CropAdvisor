<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Min Trädgård - Planty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Min Trädgård</h1>
            <a href="${pageContext.request.contextPath}/garden/add" class="btn btn-primary">+ Lägg till gröda</a>
        </header>

        <c:if test="${empty gardenItems}">
            <div class="empty-state">
                <p>Din trädgård är tom. Lägg till din första gröda!</p>
                <a href="${pageContext.request.contextPath}/garden/add" class="btn btn-primary">Lägg till gröda</a>
            </div>
        </c:if>

        <c:if test="${not empty gardenItems}">
            <table class="garden-table">
                <thead>
                    <tr>
                        <th>Gröda</th>
                        <th>Typ</th>
                        <th>Plats</th>
                        <th>Temperatur</th>
                        <th>Råd</th>
                        <th>Åtgärd</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${gardenItems}" var="item">
                        <tr class="clickable-row" onclick="window.location='${pageContext.request.contextPath}/garden/detail/${item.cropItem.id}'">
                            <td class="crop-name">${item.crop.name}</td>
                            <td>${item.crop.type}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty item.cropItem.city}">
                                        ${item.cropItem.city}, ${item.cropItem.country}
                                    </c:when>
                                    <c:otherwise>
                                        Ingen plats
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="temp">
                                <c:choose>
                                    <c:when test="${not empty item.weather and not empty item.weather.temp}">
                                        ${item.weather.temp}°C
                                    </c:when>
                                    <c:otherwise>
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="advice-cell">
                                <c:choose>
                                    <c:when test="${item.hasActionableAdvice()}">
                                        <span class="advice-badge warning">
                                            <c:forEach items="${item.advices}" var="advice">
                                                <c:if test="${advice.actionRequired}">
                                                    <c:choose>
                                                        <c:when test="${advice.message.contains('frost') || advice.message.contains('kallt')}">❄️</c:when>
                                                        <c:when test="${advice.message.contains('varmt') || advice.message.contains('varm')}">☀️</c:when>
                                                        <c:when test="${advice.message.contains('vatten') || advice.message.contains('torrt')}">💧</c:when>
                                                        <c:when test="${advice.message.contains('vind')}">🌬️</c:when>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                            (${item.actionableAdviceCount})
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="advice-badge ok">✅ OK</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td onclick="event.stopPropagation()">
                                <form action="${pageContext.request.contextPath}/garden/delete/${item.cropItem.id}" method="post" style="display: inline;">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Är du säker på att du vill ta bort ${item.crop.name}?')">Ta bort</button>
                                </form>
                            </td>
                        </tr>
                        <c:if test="${not empty item.advices}">
                            <tr class="advice-details">
                                <td colspan="6">
                                    <div class="advice-list">
                                        <strong>Väderråd:</strong>
                                        <ul>
                                            <c:forEach items="${item.advices}" var="advice">
                                                <li class="${advice.actionRequired ? 'action-required' : 'info'}">
                                                    ${advice.message}
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>