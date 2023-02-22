# Git Hub User Search
<p>A simple application for finding users of the Git Hub information platform, based on the MVVM architecture.</p>
<p>
<img src="https://firebasestorage.googleapis.com/v0/b/spotifyclonetest-68b4a.appspot.com/o/image_for_project%2FGHUS%2Fsplash.jpg?alt=media&token=ec572a90-0ed7-42a9-a420-42ad1dd2c6f5" alt="Splash Screen" width="23%">
<img src="https://firebasestorage.googleapis.com/v0/b/spotifyclonetest-68b4a.appspot.com/o/image_for_project%2FGHUS%2Fuser_search_main.jpg?alt=media&token=8180c627-7267-4137-adc5-390e659d5537" alt="Search main screen" width="23%">
<img src="https://firebasestorage.googleapis.com/v0/b/spotifyclonetest-68b4a.appspot.com/o/image_for_project%2FGHUS%2Fuser_search.jpg?alt=media&token=ff9d4fc0-177e-490b-a5c1-8d8f0f82cd19" alt="Search user name" width="23%">
<img src="https://firebasestorage.googleapis.com/v0/b/spotifyclonetest-68b4a.appspot.com/o/image_for_project%2FGHUS%2Fuser_detail.jpg?alt=media&token=c3e648f2-5f00-44ca-8b0e-104412c2cdad" alt="User Detai Screen" width="23%">
</p>

# DEVELOPMENT TIME
<p>2023-02-17 ~ 2023-02-22</p>

# SPECIFICATIONS AND OPEN SOURCE LIBRARIES
<ul>
<li>Minimum SDK 24</li>
<li>MVVM Architecture</li>
<li><a href="https://github.com/JakeWharton/timber">Timber</a> for logging</li>
<li>Navigation Component</li>
<li>Glide</li>
<li>RecyclerView</li>
<li>ViewModel</li>
<li>LiveData</li>
<li>Moshi</li>
<li>Retrofit</li>
<li>Coroutines</li>
<li>AppAuth</li>
<li>CircleImageView</li>
</ul>

# PLANNING
<p>TWe implement a screen with user search. In the search bar, enter the name you are looking for, a list of found items is displayed under the line, consisting of the user name, avatar and the number of followers. When clicking on a list item, we go to the screen where the list of repositories of this user is displayed. The list item should consist of the repository name, description, date of the last commit, default branch, number of forks, stars, YAP of the project source code.</p>

<p>Task with an asterisk:</p>
<p>To implement the authorization function, after successful authorization, we give the opportunity to open your profile, display information on the screen of your profile at your discretion.</p>

<p>The base url of the API <a href="https://api.github.com">api.github.com</a></p>
