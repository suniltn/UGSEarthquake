# UGSEarthquake
Coding excersise

# UGSEarthquake
Coding excersise , 100% Kotlin

**Used below:**

MVVM

Repository Pattren(Clean Code Architecture)

Pagination

Coroutines

Nav Garph (Navigation Componenet)

DI using Dagger Hilt from Andorid Framework

Room Database

Retrofit and Gson

RecyclerView with DiffUtil

**Pagination**


I noticed that requesting for 30 days Earthquake data will give more than 10,000 results. User has to wait for a long time seeing the progressbar. 
So I added the pagination for requesting 1 day at a time (~200 results) by uing start time and end time in the API. and updating those on subsequent requests.
Pagination with DiffUtil will process results much faster. 


**TODO:**


Unit test

Adding a Datastore fro repository pattren , I though this was over engineering for this App, So didn't use

Use Executors pattren for coroutines (Main,IO, Default, trampoline) etc this helps if we have unit tests

More attractive UI

Support for Master-Detail pattren for tablet landscape mode.
