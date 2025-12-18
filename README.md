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

  ---                                                                                                                                                                   
### 🌱 Cropservice (Port 8081)

GET - Hämta alla crops

http GET http://localhost:8081/api

GET - Sök crops efter planttyp (RequestParam → URI)

http GET http://localhost:8081/api/search?type=VEGETABLE                                                                                                              
http GET http://localhost:8081/api/search?type=FRUIT                                                                                                                  
http GET http://localhost:8081/api/search?type=HERB

GET - Hämta crop via ID (PathVariable)

http GET http://localhost:8081/api/crop/1

GET - Hämta crop via namn (PathVariable)

http GET http://localhost:8081/api/crop/name/Tomato

POST - Skapa ny crop med default-profil (RequestBody → JSON)

http POST http://localhost:8081/api/create-default \                                                                                                                  
{                                                                                                                                
"name": "Gurka",                                                                                                                                                    
"latinName": "Cucumis sativus",                                                                                                                                     
"type": "VEGETABLE",                                                                                                                                                
"soilType": "LOAM",                                                                                                                                                 
"sunExposure": "FULL_SUN"                                                                                                                                           
}

Enum-värden för CreateCropRequest:
- type: VEGETABLE, FRUIT, HERB, FLOWER
- soilType: CLAY, SAND, LOAM, SILT, CHALK, PEAT
- sunExposure: FULL_SUN, PARTIAL_SHADE, FULL_SHADE

  ---                                                                                                                                                                   
☀️ Weatherservice (Port 8082)

GET - Hämta all väderdata

http GET http://localhost:8082/weather

GET - Hämta alla platser

http GET http://localhost:8082/locations

GET - Hämta väder för specifik plats (RequestParam → URI)

http GET "http://localhost:8082/weather-location?city=Stockholm&country=Sweden"                                                                                       
http GET "http://localhost:8082/weather-location?city=London&country=UK"
                                                                                                                                                                        
---                                                                                                                                                                   
### 🌿 Growthservice (Port 8083)

CropItem Endpoints

GET - Hämta alla crop items

http GET http://localhost:8083/api/cropitem

POST - Skapa ny crop item (RequestBody → JSON)

http POST http://localhost:8083/api/cropitem \                                                                                                                        
{                                                                                                                                
"cropId": 1,                                                                                                                                                        
"city": "Stockholm",                                                                                                                                                
"country": "Sweden"                                                                                                                                                 
}

http POST http://localhost:8083/api/cropitem \                                                                                                                        
{                                                                                                                                
"cropId": 2,                                                                                                                                                        
"city": "London",                                                                                                                                                   
"country": "UK"                                                                                                                                                     
}

GET - Hämta crop item via ID (PathVariable)

http GET http://localhost:8083/api/cropitem/1

PUT - Uppdatera crop item (RequestBody → JSON)

http PUT http://localhost:8083/api/cropitem/1 \                                                                                                                       
{                                                                                                                                
"cropId": 1,                                                                                                                                                        
"city": "Göteborg",                                                                                                                                                 
"country": "Sweden"                                                                                                                                                 
}

DELETE - Ta bort crop item (PathVariable)

http DELETE http://localhost:8083/api/cropitem/1

GET - Hämta crop item via crop ID (PathVariable)

http GET http://localhost:8083/api/cropitem/by-crop/1

Advice Endpoints (Alla är GET med PathVariable eller ingen parameter)

GET - Hämta alla råd

http GET http://localhost:8083/api/advice

GET - Hämta råd för specifik crop item (PathVariable)

http GET http://localhost:8083/api/advice/1

GET - Hämta råd med kontext (PathVariable)

http GET http://localhost:8083/api/advice/1/context

GET - Hämta alla råd med kontext

http GET http://localhost:8083/api/advice/all-with-context

Crop Endpoints (GET med PathVariable)

GET - Hämta alla crops

http GET http://localhost:8083/api/crop

GET - Hämta crop via ID

http GET http://localhost:8083/api/crop/1                                                                                                                             
                                             