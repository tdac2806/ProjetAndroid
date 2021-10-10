# Projet Kotlin/Android : Mes Chats

## Work team

This app was made by 4 students during 1st year at ESGI : 
* Benoit PEGAZ
* Tristan DA COSTA
* Aldrick CLERET
* Eliott DELANNAY


## Environment and architecture
This app was build using **Kotlin** and **Android Studio**
This app is using : 
- [x] [Room](https://developer.android.com/training/data-storage/room)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [x] [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [x] [Fragment](https://developer.android.com/guide/fragments)


List of all fragments used in this app :+1: 
- Splash Fragment (Loading page)
- Login Fragment
- Home Fragment
- Guess game Fragment
- Result Fragment
- Admin Fragment
- Admin Add Cat Fragment
- Admin Remove Cat Fragment

![Navigation graph](/gitressources/navigation_graph.PNG)

**Admin fragments can only be accessed by the administrator** (see more below)

## Rules of the game
Cat names are displayed and the letters have unfortunately been mixed up, find the correct cat name!

## Use case
The user starts the application, the loading screen appears and displays the **Login fragment**.

On the **Login fragment** the user can enter a username and password.
If the identifier does not exist, it is created in the database and the application redirects to the **Home fragment**.
If the identifier exists, the password is compared to the password found in the database.

On the **Home fragment**, the user can see the 4 best scores of other players
The user can click on the "Jouer" button which redirects him to the **Game Fragment**

**Only the administrator can click on the Administration button**

On the **Game Fragment**, the user sees the name of a cat with the mixed letters, he can enter the correct name of the cat in an input box, validate or skip.

The score is incremented if the user finds the right name
The game ends when the user has seen all the cat names
The score is then displayed in **Result Fragment**, and the user can replay or return to the main menu

On the **Admin fragment**, the administrator can see the number of cats already in the database, and add and delete cats using specific fragments

To log in as an administrator:


>Password: Admin
Username: Admin