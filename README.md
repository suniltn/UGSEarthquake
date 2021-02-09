** Check out master branch for code**

# UGSEarthquake
Coding exercise , 100% Kotlin

**Used below:**

MVVM

Repository Pattern (Clean Code Architecture)

Pagination

Coroutines

Nav Graph (Navigation Component)

DI using Dagger Hilt from Android Framework

Room Database

Retrofit and Gson

RecyclerView with DiffUtil

**Pagination**


I noticed that requesting 30 days of Earthquake data will give more than 10,000 results. User has to wait for a long time seeing the progressbar.
So I added the pagination for requesting 1 day at a time (~200 results) by using start time and end time in the API. and updating those on subsequent requests.
Pagination with DiffUtil will process results much faster.


**Future Improvements:**


Unit test

Adding a Datastore for repository pattern as an Improvement

Use Executors pattern for coroutines (Main,IO, Default, trampoline) etc this helps if we have unit tests

More attractive UI

Support for Master-Detail pattern for tablet landscape mode.
