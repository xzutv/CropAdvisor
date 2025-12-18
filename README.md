**TO RUN THIS PROJECT**

**SETUP**
1. Locate root directory for the project through terminal
2. run chmod +x start-all.sh (for excavation permission)
3. run script in root-directory (planty) ./start-all.sh

**NAVIGATE TO APP**
1. Open your favourite browser
2. Navigate to http://localhost:8080
3. Have fun

**ENDPOINTS**

Weatherservice - port 8082
1. Find all locations:
localhost:8082/locations
2. Find all weather:
localhost:8082/weather
3. Find weather by location (only has Gothenburg, Sweden)
localhost:8082/weather-location?city=Gothenburg&country=Sweden
