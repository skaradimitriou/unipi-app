# University of Piraeus (UoP) University App

This app is live at [Google Play](https://play.google.com/store/apps/details?id=com.stathis.unipiapp) <br/>

## Main Goal

This app was developed for University of Piraeus (UoP), so users can gain:<br/>
a) useful student information about the department <br/>
b) look up their professor's e-mail and send them e-mails from the app <br/>
c) get new announcements of the department and [E-Class](https://gunet2.cs.unipi.gr/)

## Users

a. Students of the department <br/>
b. People who want to learn more about the programme (CS Postgraduate Degree) <br/>

### Programming Language 

[Kotlin](https://kotlinlang.org/)

## Architecture
Model - View - ViewModel (MVVM)

## Remote sources
- [Unistudents Api](https://github.com/UniStudents/unistudents-api) for student authentication & data
- [University of Piraeus Informatics Department](https://www.cs.unipi.gr/index.php?lang=el) for department announcements
- [Gunet2 E-Class](https://gunet2.cs.unipi.gr/) for e-class announcements

## Technologies Used
- Navigation Component <br/>
- Kotlin Coroutines <br/>
- Data Binding

### Theme 

[Material Design](https://material.io/)

### Libraries

[Retrofit](https://square.github.io/retrofit/) to consume REST web services</br>
[Room Persistence Library](https://developer.android.com/training/data-storage/room) for local database (SQLite)</br>
[Dagger2](https://developer.android.com/training/dependency-injection/dagger-android) for dependency injection </br> 
[Gson](https://github.com/google/gson) for serialization/deserialization</br>
[Glide](https://github.com/bumptech/glide) to load images <br/> 
[JSOUP](https://jsoup.org/) HTML parser <br/>
[SimpleXML]() to parse XML responses<br/>
[Shimmer Loading](https://facebook.github.io/shimmer-android/) to implement loading effect
[Timber](https://github.com/JakeWharton/timber)
