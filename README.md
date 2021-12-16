
# Demo App by Michael Bohdaniec

A demonstration application based on a MVVM architecture. Written by Michael Bohdaniec. 


# Features

1. Product List View. (**Fragment** with a **Recycler View Adapter**)
2. Details View. (**Fragment**)
3. Fetching data from an API. (**RetroFit**, **OkHttp**, **RxJava2**) 
4. API data caching. (**Room**, **Coroutine**)
5. Dependency Injection. (**Dagger2**)
6. Loading/Error/Success states handling.
7. Testing demonstration. (**jUnity**, **Mockito**)

## Architecture
- Architecture is based on an MVVM design pattern.
- View layer consists of the List View (fragment) and Details View (fragment). **For the purpose of this exercise the Main Activity only starts the List Fragment. No requirements were specified for the Main Activity.**
- The data from the cache and API is given to the List View via ListFragment ViewModel.
- The Model layer consists of two types of data models for receiving data from the back-end:
	- Data model for reading API calls.
	- Data model for reading Room database.
- The Network Layer has been implemented as an ApiClient class that communicates with the backend. It provides access to the specified endpoint through the REST architecture. 
- I have decided to use (as much as possible) an architectural style that forces a one-way direction of injecting dependencies. In this instance, the List Fragment contain a ViewModel, but the ViewModel doesn’t know about the View, and the upstream callback is done via **DataFetchingCallback** interface. Similar relationship is repeated between the ViewModel and the APIClient. 

## Functionality

**Loading data:** 
When the application starts, it will initialize the List fragment which attempts to make an API call: 
- If the application is able to retrieve the live data via the network, it will cache the data into a local database after which the live data is displayed to the user. 
**ApiClient -> ViewModel -> Callback Interface -> View -> ProductListAdapter(RecyclerView)**

- If the device fails to establish a connection to the desired server, the application will attempt to read the information from cache (room). If the cache has been populated on an earlier occasion, the cached data is displayed to the user.
**ApiClient(no connection) -> ViewModel -> CacheReader -> ViewModel -> Callback Interface -> View -> ProductListAdapter(RecyclerView)**

- If the device fails to establish a connection to the desired server, and the cache has never been populated, the application displays a "failure to connect" message and a "retry" button which attempts to acquire data from the API again.

**Displaying data:**
The data in the List View is displayed as a **Recycler View** and an **Adapter**. Although the test data provided for this demonstration is limited to three items, the recycler view allows for handling large amounts of information by dynamically assigning elements in an efficient manner.

**Dependency Injection:**
The application utilizes **Dagger2** for it's dependency injection. The Main Component interface has four components: 
- AppModule
- MainModule
- RoomModule
- ViewModelModule

And is able to inject these:
- MainActivity (class)
- ListFragment (class)
- ListFragmentViewModel (class)
- DetailsFragment (class)
- CacheReader (class)
- AppDatabase (abstract class)
- ProductDao (interface)

**Unit Testing:**
A very simple "proof of concept" test has been included in the project to demonstrate a possible way of testing the implementation. Currently, the testing consists of a single test that aims to demonstrate the usage and understanding of mocking. 
 