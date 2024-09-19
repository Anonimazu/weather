# Weather App
* weather app demo clip : https://drive.google.com/file/d/1_Y-jZ8AROF68GurHA8vkQGrw3oKQJIpa/view?usp=sharing

High level Architecture
------------
* MVVM
  * Viewmodel handles business logic and also hold data and UI State.
  * A Weather Repository was injected into the view model.
  * I used unidirectional flow pattern to handle the UI state. That was made the application has good maintain ability.

* Tech Stack
  * Kotlin
  * Jetpack Compose 
  * Retrofit 
  * Kotlin Coroutines 
  * StateFlow 
  * Hilt: For Dependency Injection

* State management work flow
  * Weather App Sequence diagram

```mermaid

sequenceDiagram
    User->>+App: open
    App->>+Server: request weather data(London)
    App->>+User: Show Loading ui
    Server->>+App: recive load weather data(London)
    App->>+App: render London's weather
    App->>+User: Show London's weather or error
    User->>+App: search the location of Bangkok
    App->>+Server: request location of Bangkok
    App->>+User: Show Loading ui
    Server->>+App: recive location of Bangkok
    App->>+App: select Bangkok Location from list
    App->>+Server: request weather of Bangkok by lattiude and longitude
    Server->>+App: recive location of Bangkok
    App->>+App: render Bangkok's weather
    App->>+User: Show Bangkok's weather or error


```
  

