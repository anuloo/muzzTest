# muzzTest
Structural design pattern
The app is built with the Model-View-ViewModel (MVVM) is its structural design pattern that separates objects into three distinct groups:

Models hold application data. They’re usually structs or simple classes.
Views display visual elements and controls on the screen. They’re typically subclasses of UIView.
View models transform model information into values that can be displayed on a view. They’re usually classes, so they can be passed around as references.

Clean Architecture with 3 separated layers: data, domain, presentation(ui)
- Dagger Hilt
- Courountine
- MVVM + Kotlin flow
- Jetpack compose
- Navigation component

### Usage

By tapping on the user profile you can toggle between sender and receiver to fake the send receive funcionality.

#### Tricks and tips

I've used a sealed class to section the date and the message data
Also added an extra class for MessageRegister to differeciate between sender and receiver.

#### TODO
For start a nice firebase db instead the local one so it becames a real chat app.
On the same note using socket would be needed.

