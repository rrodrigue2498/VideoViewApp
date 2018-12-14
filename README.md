# Adding a High Quality Video to a Mobile Application



| Author                   | Date              | Difficulty | Audience                |
| ------------------------ | ----------------- | ---------- | ----------------------- |
| Rafael Rodriguez-Charris | December 14, 2018 | Medium     | Any level of experience |



​	In the audiovisual world in which we live today, there is an ever-growing need to showcase work in the most appealing way possible to the user. This is the reason why it is not hard to understand why online streaming services like YouTube have gained and will continue to gain popularity in the socially-connected world existent today.

​	Such being the case, streaming videos to mobile applications is becoming a must, even in the least-developed Android or iOS operated applications out there. Therefore, in this lesson, we will teach the user how to stream high quality video on a mobile application using Android Studio. For the purposes of including a larger audience with any level of experience with Java, this lesson is designed to guide you through every step of the way -- from creating a mobile application in Android Studio to implementing the video on the mobile application. 



## Table of Contents

[TOC]

## Prerequisites

​	All the work used to develop this lesson can be found in the following GitHub repository: https://github.com/rrodrigue2498/VideoViewApp.git

​	Throughout this tutorial, we will relate back to the source code to understand step-by-step how to create a mobile application that streams high quality video(s).

​	Besides the GitHub repository that we have shared, it is important to understand the programs used, as well as the programming languages used, for this lesson:

- **Programming Languages**
  - *Java*
  - *XML* (markdown language)
- **Programs**
  - *Android Studio*, which can be downloaded here: https://developer.android.com/studio/?gclid=EAIaIQobChMIs8uYwZOg3wIVCZ6fCh2Egw3BEAAYASAAEgKWoPD_BwE#downloads
  - *GenyMotion*, an mobile device emulator to test the outcome of your source against it. You could also connect an Android device to your computer and display your built application there. You can download *GenyMotion* here: https://www.genymotion.com/desktop/. They offer the emulator for free as long as you mention that it is for *personal use*. 



**Note**: It is important to note that Android Studio only serves to create mobile applications on Android devices. If you're interest in building the same for an iOS device, check more on React Native: https://facebook.github.io/react-native/.



## Creating the Mobile App

​	Firstly, open up Android Studio. 

​	Now, click on **File**, then on **New**, and finally on **New Project**. It should look something like this:


![Start Project](https://github.com/rrodrigue2498/VideoViewApp/blob/master/Blog_Photos/New%20File.png)



​	Once you have done that, you should be prompted to enter the name of your application. I called my app **VideoViewApp**, but you can put any name you want for it. From there, make sure you use an API that is of **15 or more**, so that as many devices can get the application installed as possible. Then, use the layout you want to initialize your app. I chose the **basic layout** design to get the application started. Once all the steps have been finalized, Android Studio will make sure to get all the gradles set up to have you working in no time. If you want to learn more about what is a *gradle*, visit the following site: https://developer.android.com/studio/build/.

​	Take some time to explore the files that have been created for you before you proceed with the next section. More importantly, become familiar with the *MainActivity*.*java* file that was created and the *activity_layout.xml* file that will be edit the appearance of your application. Below is a snapshot of how your program should look like:



![Screen Shot 2018-12-14 at 4.01.25 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 4.01.25 PM.png)



## Editing the Application Layout

​	Now that the application has been created and the gradle is built in, it's time to set up the appearance of your mobile application that will contain the view of your video (hence the name, *VideoView*, that will get to in just a moment). 

​	We need to work on the *activity_layout.xml*. This file is found under **app**, then the **java** folder, and then under the **res** (resource) folder. The original code and screen design should look something like this:

![Screen Shot 2018-12-14 at 4.11.33 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 4.11.33 PM.png)

```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>
```



​	In order for our app to have the basic appearance that we want it to have -- a play button with the video playing in the center of the app --, you need to modify the above code with the following:

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/videoView1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingLeft="16dp"
    android:paddingTop="16dp"
    android:paddingRight="16dp"
    android:paddingBottom="16dp"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="PLAY"
        android:layout_gravity="center_horizontal"
        android:onClick="videoplay"/>

    <VideoView
        android:id="@+id/videoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
```



​	This code displays a *LinearLayout*, which organizes controls linearly in either a vertical or horizontal fashion. Within this type of layout chosen, we implement two important widgets: the *Button* that will play the video and the *VideoView* widget that will contain the video itself. The appearance of the application will be the following if you use this layout design. You are allowed to play around with this accordingly:



![Screen Shot 2018-12-14 at 4.19.05 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 4.19.05 PM.png)



​	Now that our appearance is good to go, it's time to implement the most important part of this lesson: the video streaming integration.



## Implementing *VideoView* Class

​	Once done with our layout design, we can focus on implementing the class that will display the video to be played. You can transition now from the xml file editing the appearance of the application (*activity_layout.xml*) to the ***MainActivity*.*java*** file, which is the code that will implement the *VideoView* class.

​	The original code in this file should be the following:

```java
package com.example.familiamaldonado.videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```



​	Now, let us show the finalized code on this file. Then, we will work our way through the code that will stream the video file:

```java
package com.example.familiamaldonado.videoviewapp;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clk = (Button) findViewById(R.id.button2);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);
    }

public void videoplay (View v)
{

    String videopath = "android.resource://com.example.familiamaldonado.videoviewapp/"+R.raw.cloud;
    Uri uri = Uri.parse(videopath);
    videov.setVideoURI(uri);
    videov.setMediaController(mediaC);
    mediaC.setAnchorView(videov);


    videov.start();

}
}
```

​	

​	In first place, we need to set some names to the *Button* widget that will play the video and the *VideoView* widget that will contain the video itself:

```java
public class MainActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;
    MediaController mediaC;
```

​	The implementation of these two widgets (plus the *MediaController* widget that will be get to later) will import the classes automatically, so that you can work on them and also execute them after you have designed them on your activity layout. We have set the names **clk, videov, and mediaC**, but you are free to use any other that you want. Make sure that you keep these in mind, as you will utilize them later in your code.

​	The following step is to match the names that you have assigned to each widget with its corresponding resource id on the activity layout. If you go back to the code that we wrote in the activity layout file, you will find that each widget was assigned an *android id*. This is a unique way to identify the created widget within the source code, so it can be accessed, edited, or executed within the app itself. The id names assigned to each widget can be applied as you wish, even though you can also utilize the names that we have provided. It is always good to name them in relation to the widget or class that they will be responding to in the layout file.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clk = (Button) findViewById(R.id.button2);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);
    }

```

​	

​	Now, before we proceed to the last snippet of code to finalize our mobile application, it is imperative that we create the folder that will contain the video to be played. For the purpose of this tutorial, we will have the video within the application. Those applications that utilize multiple videos usually use a database that stores that information, and thus reduces the size of the application. Also, it is common practice to link the video to be played from an online streaming service like YouTube or Vimeo. However, we do not explore those options on this lesson. You will have to use an acceptable video format, in this case, an MP4 video was used. Let's open the parenthesis, create the folder that will contain the video file, and, of course, copy and paste the file inside of it.



______



### Creating *Raw Resource Folder* & Adding Video File

​	In order to implement the video file, it is important that you create what is called a **Raw Resource Folder**. This is a folder that will be within the resource folder (res) in your application, and will hold the various files to be used on the mobile application. A more recent version of Android Studio will allow you to create the folder as soon as you right-click on the res folder, but older versions simply require you to create a regular resource folder, and then name it "raw". The following picture shows how to go about creating a raw resource folder:

![Screen Shot 2018-12-14 at 4.52.37 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 4.52.37 PM.png)



​	As you can note, you simply have to right-click on *res*, then click on *folder*, and finally click on *Raw Resources Folder*. This will create an empty raw res folder inside res, where you will copy and paste your video -- literally.

​	**Note**: Make sure that your video file name is in lower case. Sometimes Android Studio poses some conflicts with the way that the file is named, or can not show the raw folder as part of the res folder. Also, make sure that you do not simply drag and drop the video file onto the raw folder. Literally, copy and paste it. 

_________________

​	

​	Now that you have created your resource folder, and have placed your video file inside of it, you can add the code that will integrate the video in the app. This is the code we wrote to do so:

```java
public void videoplay (View v)
{

    String videopath = "android.resource://com.example.familiamaldonado.videoviewapp/"+R.raw.cloud;
    Uri uri = Uri.parse(videopath);
    videov.setVideoURI(uri);
    videov.start();
}
}
```



​	In this code, we are basically defining the path within the app to find the video -- that we named *cloud* in the application. As you can see, the `+R.raw.cloud` only works once the raw folder is created. Otherwise, if we had tried to implement this line without creating the folder, we would have gotten a conflict in Android Studio on this specific line.  Also, the URI code (URI stands for Uniform Resource Identifier) would not be able to parse the video, so it can be adapted and played in the Android system. The other lines of code simply link the video widget name (videov) with the parsed Uri and starts the video as soon as the play Button is clicked/pressed on.

​	We are finally done with our implementation of our application, thus left with two last steps before we can see the fruits of our work: *MediaController* implementation and launch on device emulator.



### Extending *MediaController* Method

​	To explain it in simple terms: *MediaController* is the method extension that allows the video to have the controls we are accustomed to using in our daily lives -- play, pause, rewind, forward, etc. In other words, if we do not integrate this method, our video will play non-stop once the play button is clicked on. We won't be able to play from a specific location, pause the video, or advance it a couple of seconds. 

​	At the beginning of this section, we set a variable for each of the two widgets that are used in the application: Button and VideoView. We also set a variable for *MediaController*. This variable will be utilized furthermore to implement various controls in the video. The implementation of this variable will automatically import the *MediaController* method to the application. If not, make sure it does. Otherwise, Android Studio will return a red underlining on your variable:

```java
package com.example.familiamaldonado.videoviewapp;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;
    MediaController mediaC;
```

​	

​	Then, we have to assign this new variable to a new *MediaController* method that will be used to add the controllers in the video. This line of the final code does that:

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clk = (Button) findViewById(R.id.button2);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);
    }
```

​	

​	Finally, we just need to link the *MediaController* method to the video variable that plays the video. Besides, we need to make sure that you set an anchor to the mediaC variable, so that the video controllers actually have some functionality in the application. This very final snippet of code will look like this:

```java
public void videoplay (View v)
{

    String videopath = "android.resource://com.example.familiamaldonado.videoviewapp/"+R.raw.cloud;
    Uri uri = Uri.parse(videopath);
    videov.setVideoURI(uri);
    videov.setMediaController(mediaC);
    mediaC.setAnchorView(videov);
    videov.start();
}
}
```

​	

​	You got it! You should have the final product of a very basic mobile application with streaming capabilities. The last step is to see it reflected on the emulator. Remember to install GenyMotion to preview the application before you send it off to the outside world.

​	**Note**: It is important to mention that every time that the *Play Button* is clicked, the video restarts. You can further explore the *VideoView* class and its various methods to change this current implementation. 



## Launching Application to Emulator

​	

​	Open up GenyMotion. If you have not added a device yet, make sure you install one to showcase your work. Most devices should be good to go for this part. Assuming that you started the application project with an API greater than 15, most devices in the emulator should run just fine. Once you install the device, run the application from Android Studio. When prompted to choose a device, you should see the device you installed in GenyMotion listed there. You may not see the device listed if you haven't started it in GenyMotion, so make sure you do that before you run the application in Android Studio.

​	Here are some pictures to help you visualize what is there to do:

![Screen Shot 2018-12-14 at 5.26.42 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 5.26.42 PM.png)



![Screen Shot 2018-12-14 at 5.27.45 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 5.27.45 PM.png)



​	This is how your application should look like once it successfully runs in Android Studio:

![Screen Shot 2018-12-14 at 5.29.51 PM](/Users/familiamaldonado/Desktop/Screen Shot 2018-12-14 at 5.29.51 PM.png)

