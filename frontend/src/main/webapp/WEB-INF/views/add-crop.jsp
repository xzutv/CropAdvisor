<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lägg till gröda - Planty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Lägg till gröda i trädgården</h1>
        </header>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/garden/add" method="post">
                <div class="form-group">
                    <label for="cropId">Välj gröda:</label>
                    <select id="cropId" name="cropId" required>
                        <option value="">-- Välj en gröda --</option>
                        <c:forEach items="${crops}" var="crop">
                            <option value="${crop.id}">
                                ${crop.name} (${crop.type})
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="location">Välj plats:</label>
                    <select id="location" name="location" required onchange="setLocation(this)">
                        <option value="">-- Välj en plats --</option>
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.city}|${location.country}">
                                ${location.city}, ${location.country}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="hidden" id="city" name="city">
                    <input type="hidden" id="country" name="country">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Lägg till</button>
                    <a href="${pageContext.request.contextPath}/garden" class="btn btn-secondary">Avbryt</a>
                </div>
            </form>
        </div>

        <div class="info-box">
            <h3>Tips:</h3>
            <p>Välj en gröda och plats för att lägga till den i din trädgård. Du kommer att få väderbaserade råd baserat på den valda platsen.</p>
        </div>
    </div>

    <script>
        function setLocation(select) {
            const value = select.value;
            if (value) {
                const [city, country] = value.split('|');
                document.getElementById('city').value = city;
                document.getElementById('country').value = country;
            }
        }
    </script>
</body>
</html>