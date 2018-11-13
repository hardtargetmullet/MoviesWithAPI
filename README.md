# MoviesWithAPI
An Android App that lets you see the top rated or most popular movies according to themovieDB API.

Required Tasks:
    
    - Build a UI layout for multiple Activities.
    - Launch these Activities via Intent.
    - Fetch data from themovieDB API
    
Screen Preview:
   ![screen-preview](https://github.com/hardtargetmullet/MoviesWithAPI/blob/master/ScreenCapture.gif.gif)

Upon launch, present the user with an grid arrangement of movie posters.
    
    Allow your user to change sort order via a setting:
    The sort order can be by most popular, or by top rated
    Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
    - original title
    - movie poster image thumbnail
    - A plot synopsis (called overview in the api)
    - user rating (called vote_average in the api)
    - release date
    
Tech Used:
    
    GridView
    JSONObject
    Constraint Layout
    Picasso
    ArrayAdapter(custom)
    ImageView
    TextView
    Intent
    Parcelable
    
In order to get the app to fetch data from themovieDB API you must apply for a API key here:
    https://www.themoviedb.org/
    
    You must create and account and request a key for personal use.
    Replace the line:
        // in the file ApiCaller.java in the utils package
        private static final String MY_API_KEY = "XXXXXXXXXX"; // replace "XXXXXXXXX" with your own personal key
    
