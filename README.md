# FlightAppAndroid
Advanced Programming 2, BIU

**Features Implemented:**
* Joystick - changes values for aileron & elevator during flight
* Throttle Seekbar
* Rudder Seekbar
* Client side of server, connects to FlightGear app

**Project Structure:**

 The app is built using the MVVM structure 
* View
   * [MainActivity](https://github.com/tashmanr/FlightAppAndroid/blob/master/app/src/main/java/com/example/flightappandroid/MainActivity.kt) is responsible for the text boxes, connect button, and throttle & rudder seekbars in the app 
   * [JoystickView](https://github.com/tashmanr/FlightAppAndroid/blob/master/app/src/main/java/com/example/flightappandroid/JoystickView.kt) is responsible for the joystick in the app
   * [activity_main](https://github.com/tashmanr/FlightAppAndroid/blob/master/app/src/main/res/layout/activity_main.xml) has the XML layout for the view
* ViewModel
   *  [ViewModel](https://github.com/tashmanr/FlightAppAndroid/blob/master/app/src/main/java/com/example/flightappandroid/ViewModel.kt) is sent the data by the View, and then sends it onward to the Model 
* Model
   *  [Model](https://github.com/tashmanr/FlightAppAndroid/blob/master/app/src/main/java/com/example/flightappandroid/Model.kt) opens the socket to connect to FlightGear, and then sends it the data to fly the plane 

**Necessary Installations:**
* FlightGear

**Instruction to Run App:**
* Download and install the FlightGear app at https://flightgear.org/
* Upon opening the FlightGear app, go to settings -> additional settings and write: `--telnet=socket,in,10,127.0.0.1,6400,tcp`
* Click on "Fly" in the FlightGear app, and once it's up and running, press `v` twice. If you see the plane on the runway, you're good to go.
* In our Flight App, type in the IP address *127.0.0.1* and port *6400* , and then click on **Connect** in order to connect to FlightGear's server. 
* Bring the throttle up to 100%, and then in FlightGear, click on **autostart**
* Once the engine has started, you can takeoff, and start to fly! 

**Additional Links:**
* UML layout
* Video demo of App
* Slideshow Presentation
