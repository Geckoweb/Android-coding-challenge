# Solution

Hi there. I worked on this challange about 5-6 hours on Saturday and 2-3 hours on Sunday. For this implemetation I decided to use some of Architecture components introduced by Google last year like ViewModel and LiveData. The solution contains 3 inner modules. 

* __Adapter__ -  contains the adapter thath provide to fill recyclerView  with data retreived from rest call.
* __DataProvider__ - contains interface and a wrapper class used to fetch data from GitHub repo. To do this I used [Retrofit 2](http://square.github.io/retrofit/) library and [Jackson](https://github.com/FasterXML/jackson) as a serilizator - deserializator.
* __Model__ - contains classes that represents POJO objects deserialized with Jackson. to create these classes I used [jsonschema2pojo](http://www.jsonschema2pojo.org/)

## Tests
In the main project was added also a couple of tests providing check for deserialyzer and rest calls.

## Improvements
* Currently the project doesn't have any kind of persistence management. It could be used Room and sqlite to save data fetched and consult it offline after firs time.
* The data are retrieved from internet every tyme you start the application. is not present a "pull to refresh" system that could be easly implemented using SwipeToRefresh layout and a listener.
* Could be nice use binding in viewHolder to avoid findViewById.
* There is a poor error management. I checked only internet error. Could be useful send different messages for different errors. 
* Are presents only a couple of unit tests for happy path. The project need more tests to have an acceptable coverage
