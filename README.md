# muzzTest
Structural design pattern
The app is built with the Model-View-ViewModel (MVVM) is its structural design pattern that separates objects into three distinct groups:

Models hold application data. They’re usually structs or simple classes.
Views display visual elements and controls on the screen. They’re typically subclasses of UIView.
View models transform model information into values that can be displayed on a view. They’re usually classes, so they can be passed around as references.

Clean Architecture with 3 separated layers: data, domain, presentation(ui)
- Dagger Hilt
- Coroutine
- MVVM + Kotlin flow
- Jetpack compose
- Navigation component

### Usage

By tapping on the user profile you can toggle between sender and receiver to fake the send receive funcionality.

#### Bugs

List is not scrollable until its overflow the app boundary
Message not visible when keyboard up due to have not enough message to scroll to

#### TODO
For start a nice firebase db instead the local one so it becames a real chat app.
On the same note using socket would be needed.

#### MuzzTest App Screenshots

<table>
  <tr>
    <td>With User 1 (Sarah)</td>
     <td>Keyboard up</td>
     <td>With User 2 (Max)</td>
  </tr>
  <tr>
    <td><img src="/assets/screenshots/Screenshot_1.jpg" width=400></td>
    <td><img src="/assets/screenshots/Screenshot_2.jpg" width=400></td>
    <td><img src="/assets/screenshots/Screenshot_3.jpg" width=400></td>
  </tr>
 </table>
