# Ripple

Ripple is a **meditation** app that I worked on, with my fellow high schoolers in mind.  My motivation is to let highschoolers use Ripple to meditate for a few minutes everyday and become more mindful. Mindfulness meditation not only helps relieve stress but also helps ease depression and anxiety.  

<img src="https://cdn.mindful.org/Meditation_Goleman.jpg" width="350">

The best time to meditate is in the morning.  You can spend 5-10 minutes on meditating with Ripple after a hot morning shower.  When meditating, try concentrate on your breath and stop your mind when it wanders.  Try to do this every morning and do it consistently four or five times per week. You'll experience the power of medititation and start your school day with fresh mind everyday.

## How does it work?

Basically I created a `MediaPlayer` instance that tells Android to playback a meditation music file.  There is nothing spectacular here.  However, there are three things worth mentioning: power saving features, communication with UI thread, and asynchronous communication.

### Taking Care of Power Saving Interruption

Pay attention to power saving features when it comes to mobile app development.  Your phone is going to dim your display screen (which is one of the most power hungry feature on phone) after few minutes and then put your phone to sleep mode.  To tell Android not to dim the display (because we want user to look at the screen), add this permission request to our project manifest file:

```java
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

### Working with UI Thread

I used `CountDownTimer` to help me do the counting in background.  It means that the counter has its own thread.  If we have a job that will hold up the UI thread for too long (few seconds), Android will not like it and throw us an ANR (Application Not Responding) error.  This is a catastrophic error similar to blue screen in Windows. To prevent ANR, we usually put a lengthy operation on its own worker thread.  However,  when the operation tries to post anything to an UI element, say changing a button from "Playing" to "Stopped", it can't do that directly.  The UI element is an object on the UI thread while we're trying to access from a worker thread.

To work around this kind of inter-thread communication, I use `Handler` to post the message from my `CountDownTimer` worker thread to `textViewTime` UI element:

```java
myHandler.post(new Runnable() {
  @Override public void run() {
    textViewTime.setText(hms);                    
   }
});
```

In Lambda expression, the same statement is written as the following:

```java
myHandler.post(() -> { textViewTime.setText(hms); });
```


### Handling Asynchronous Calls



