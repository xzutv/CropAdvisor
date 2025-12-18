## TO RUN THIS PROJECT

### SETUP
1. Locate root directory for the project through terminal
2. run chmod +x start-all.sh (for excavation permission)
3. run script in root-directory (planty) ./start-all.sh

### NAVIGATE TO APP
1. Open your favourite browser
2. Navigate to http://localhost:8080
3. Have fun

### Endpoints

#### 🌱Cropservice (Port 8081)

**Hämta all crops**

GET http://localhost:8081/api

**Sök crops efter planttyp**

http GET http://localhost:8081/api/search type==VEGETABLE
**Andra värden: FRUIT, HERB, FLOWER**

**Hämta crop via ID**

http GET http://localhost:8081/api/crop/1

**Hämta crop via namn**

http GET http://localhost:8081/api/crop/name/Tomato

**Skapa ny crop med default-profil**

http POST http://localhost:8081/api/create-default

{
name="Gurka",                                                                                                                                         
latinName="Cucumis sativus",                                                                                                                                 
type="VEGETABLE",                                                                                                                                                
soilType="LOAM",                                                                                                                                            
sunExposure="FULL_SUN"
}

**Andra Enum-värden:**
- PlantType: VEGETABLE, FRUIT, HERB, FLOWER
- SoilType: CLAY, SAND, LOAM, SILT, CHALK, PEAT
- SunExposure: FULL_SUN, PARTIAL_SHADE, FULL_SHADE

  ---                                                                                                                                                                   
#### ☀️ Weatherservice (Port 8082)

**Hämta all väderdata**

http GET http://localhost:8082/weather

**Hämta alla platser**

http GET http://localhost:8082/locations

**Hämta väder för specifik plats**

http GET http://localhost:8082/weather-location 

city==Stockholm 
country==Sweden

http GET http://localhost:8082/weather-location 

{
city==London 
country==UK
}
                                                                                                                                                                        
---                                                                                                                                                                   
#### 🌿 Growthservice (Port 8083)

#### Crop items endpoints:

**Hämta alla crop items**

http GET http://localhost:8083/api/cropitem

**Skapa ny crop item**

http POST http://localhost:8083/api/cropitem 
{
"cropId": 1,                                                                                                                                                     
"city": "Gothenburg",                                                                                                                                         
"country": "Sweden"
}

http POST http://localhost:8083/api/cropitem
{
"cropId": 2,                                                                                                                                                      
"city": "London",                                                                                                                                                   
"country": "UK"
}

**Hämta crop item via ID**

http GET http://localhost:8083/api/cropitem/1

**Uppdatera crop item**

http PUT http://localhost:8083/api/cropitem/1
{
"cropId": 1,                                                                                                                                                         
"city": "Göteborg",                                                                                                                                                
"country": "Sweden"
}

**Ta bort crop item**

http DELETE http://localhost:8083/api/cropitem/1

**Hämta crop item via crop ID**

http GET http://localhost:8083/api/cropitem/by-crop/1

**Hämta alla locations**

http GET http://localhost:8083/api/location

### Advice Endpoints:

**Hämta alla råd**

http GET http://localhost:8083/api/advice

**Hämta råd för specifik crop item**

http GET http://localhost:8083/api/advice/1

**Hämta råd med kontext (crop, väder, location)**

http GET http://localhost:8083/api/advice/1/context

**Hämta alla råd med kontext**

http GET http://localhost:8083/api/advice/all-with-context

#### Crop Endpoints (via Growthservice):

**Hämta alla crops**

http GET http://localhost:8083/api/crop

**Hämta crop via ID**

http GET http://localhost:8083/api/crop/1
                                                                                                                                                                        
---                                                                                                                                                                   
#### 🖥️ Frontend (Port 8080)

**Visa startsida**

http GET http://localhost:8080/garden

**Lägg till crop (form-data)**

http --form POST http://localhost:8080/garden/add \                                                                                                                   
cropId=1 \                                                                                                                                                          
city="Stockholm" \                                                                                                                                                  
country="Sweden"

**Visa detaljer för crop item**

http GET http://localhost:8080/garden/detail/1

**Ta bort crop item**

http --form POST http://localhost:8080/garden/delete/1
                                                                                                                                                                        
---                                                                                                                                                                   
📋 Sammanfattning

Tjänster:
- Cropservice (8081): Hanterar crop-definitioner och profiler
- Weatherservice (8082): Hämtar väderdata för olika platser
- Growthservice (8083): Kombinerar crops, väder och ger odlingsråd
- Frontend (8080): Web-gränssnitt för användaren 