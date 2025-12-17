<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${adviceContext.crop.name} - Detaljer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .detail-container {
            max-width: 900px;
            margin: 0 auto;
        }

        .detail-header {
            background: linear-gradient(135deg, #4CAF50 0%, #2c5f2d 100%);
            color: white;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 30px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .detail-header h1 {
            color: white;
            margin-bottom: 10px;
        }

        .detail-header .latin-name {
            font-style: italic;
            opacity: 0.9;
            font-size: 1.1em;
        }

        .detail-header .plant-type {
            display: inline-block;
            background-color: rgba(255,255,255,0.2);
            padding: 5px 15px;
            border-radius: 20px;
            margin-top: 10px;
            font-size: 0.9em;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .info-card {
            background-color: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .info-card h2 {
            color: #2c5f2d;
            font-size: 1.3em;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #e0e0e0;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #f0f0f0;
        }

        .info-row:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 600;
            color: #555;
        }

        .info-value {
            color: #333;
        }

        .weather-current {
            background: linear-gradient(135deg, #2196F3 0%, #1976D2 100%);
            color: white;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 30px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .weather-current h2 {
            color: white;
            margin-bottom: 15px;
        }

        .weather-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 15px;
        }

        .weather-item {
            text-align: center;
            background-color: rgba(255,255,255,0.1);
            padding: 15px;
            border-radius: 8px;
        }

        .weather-item .label {
            font-size: 0.9em;
            opacity: 0.9;
            margin-bottom: 5px;
        }

        .weather-item .value {
            font-size: 1.5em;
            font-weight: 600;
        }

        .advice-section {
            background-color: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .advice-section h2 {
            color: #2c5f2d;
            margin-bottom: 15px;
        }

        .advice-item {
            padding: 15px;
            margin: 10px 0;
            border-radius: 8px;
            display: flex;
            align-items: start;
            gap: 15px;
        }

        .advice-item.critical {
            background-color: #fff3cd;
            border-left: 4px solid #ffc107;
        }

        .advice-item.info {
            background-color: #e7f3ff;
            border-left: 4px solid #2196F3;
        }

        .advice-icon {
            font-size: 1.5em;
        }

        .back-button {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container detail-container">
        <div class="back-button">
            <a href="${pageContext.request.contextPath}/garden" class="btn btn-secondary">← Tillbaka till trädgården</a>
        </div>

        <div class="detail-header">
            <h1>${adviceContext.crop.name}</h1>
            <div class="latin-name">${adviceContext.crop.latinName}</div>
            <div class="plant-type">${adviceContext.crop.type}</div>
        </div>

        <c:if test="${not empty adviceContext.weather}">
            <div class="weather-current">
                <h2>📍 Aktuellt väder: ${adviceContext.cropItem.city}, ${adviceContext.cropItem.country}</h2>
                <div class="weather-grid">
                    <div class="weather-item">
                        <div class="label">Temperatur</div>
                        <div class="value">${adviceContext.weather.temp}°C</div>
                    </div>
                    <div class="weather-item">
                        <div class="label">Nederbörd</div>
                        <div class="value">${adviceContext.weather.rain} mm</div>
                    </div>
                    <div class="weather-item">
                        <div class="label">Molnighet</div>
                        <div class="value">${adviceContext.weather.clouds}%</div>
                    </div>
                    <div class="weather-item">
                        <div class="label">Luftfuktighet</div>
                        <div class="value">${adviceContext.weather.humidity}%</div>
                    </div>
                    <div class="weather-item">
                        <div class="label">Vind</div>
                        <div class="value">${adviceContext.weather.wind} m/s</div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty adviceContext.advices}">
            <div class="advice-section">
                <h2>🌱 Väderråd och rekommendationer</h2>
                <c:forEach items="${adviceContext.advices}" var="advice">
                    <div class="advice-item ${advice.actionRequired ? 'critical' : 'info'}">
                        <div class="advice-icon">
                            <c:choose>
                                <c:when test="${advice.message.contains('frost') || advice.message.contains('kallt')}">❄️</c:when>
                                <c:when test="${advice.message.contains('varmt') || advice.message.contains('varm')}">☀️</c:when>
                                <c:when test="${advice.message.contains('vatten') || advice.message.contains('torrt')}">💧</c:when>
                                <c:when test="${advice.message.contains('vind')}">🌬️</c:when>
                                <c:otherwise>ℹ️</c:otherwise>
                            </c:choose>
                        </div>
                        <div>${advice.message}</div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <div class="info-grid">
            <div class="info-card">
                <h2>🌡️ Temperaturkrav</h2>
                <div class="info-row">
                    <span class="info-label">Optimal min:</span>
                    <span class="info-value">${adviceContext.crop.requirements.optimalTempMin}°C</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Optimal max:</span>
                    <span class="info-value">${adviceContext.crop.requirements.optimalTempMax}°C</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Frostkänslig:</span>
                    <span class="info-value">${adviceContext.crop.requirements.frostSensitive ? 'Ja' : 'Nej'}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Värmekänslig:</span>
                    <span class="info-value">${adviceContext.crop.requirements.heatSensitive ? 'Ja' : 'Nej'}</span>
                </div>
            </div>

            <div class="info-card">
                <h2>💧 Vattenbehov</h2>
                <div class="info-row">
                    <span class="info-label">Liter per vecka:</span>
                    <span class="info-value">${adviceContext.crop.requirements.waterLitersPerWeek} L</span>
                </div>
            </div>
        </div>

        <div class="info-grid">
            <div class="info-card">
                <h2>🌍 Miljökrav</h2>
                <div class="info-row">
                    <span class="info-label">Jordtyp:</span>
                    <span class="info-value">${adviceContext.crop.environmentProfile.soilType}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Solexponering:</span>
                    <span class="info-value">${adviceContext.crop.environmentProfile.sunExposure}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">pH-nivå:</span>
                    <span class="info-value">${adviceContext.crop.environmentProfile.soilPhMin} - ${adviceContext.crop.environmentProfile.soilPhMax}</span>
                </div>
            </div>

            <div class="info-card">
                <h2>📅 Säsonger</h2>
                <div class="info-row">
                    <span class="info-label">Planteringssäsong:</span>
                    <span class="info-value">${adviceContext.crop.environmentProfile.plantingSeason}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Skördesäsong:</span>
                    <span class="info-value">${adviceContext.crop.environmentProfile.harvestSeason}</span>
                </div>
            </div>
        </div>

        <div style="text-align: center; margin-top: 30px;">
            <form action="${pageContext.request.contextPath}/garden/delete/${adviceContext.cropItem.id}" method="post" style="display: inline;">
                <button type="submit" class="btn btn-danger" onclick="return confirm('Är du säker på att du vill ta bort ${adviceContext.crop.name} från trädgården?')">
                    🗑️ Ta bort från trädgården
                </button>
            </form>
        </div>
    </div>
</body>
</html>