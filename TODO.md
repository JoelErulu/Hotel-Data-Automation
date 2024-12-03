## OBJECTIVE

Write a program that automatically stores and analyzes the price of hotel rooms of five hotels franchises in five cities.

#### HOTELS

- Four Seasons
- Ritz-Carlton
- Park Hyatt
- St. Regis Hotel
- Waldorf Astoria Hotel

#### CITIES

- Las Vegas
- New York City
- Miami
- Paris
- Los Angeles



## GOALS

- Find 10 dates with the lowest price for a room in each of the hotels from each of the cities.
- Analysis will be performed from current day to last day data is available.
- Project must run without non-obvious errors
- Hotel data is stored in a SQLite DB
- Find the dates with the lowest price for each hotel in each city (Submit in Word document)

## PLAN
1. Generate custom URLs for each hotel-city-room combo
2. Parse & store results in data structure locally & validate they are being read.
3. After validating, create/access DB file and store data
4. Iterate throughout date range & store new data.
5. At the end, iterate through database for each hotel & find which days have lowest price
6. Print them in console