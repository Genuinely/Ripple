package genuinely.ripple;



import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
//import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Handler;


//make pickup backpack
public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    TextView textViewTime;
    AudioManager am;
    final android.os.Handler myHandler = new android.os.Handler();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        final MediaPlayer mp = new MediaPlayer();


        try{
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("Meditation.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        am = new AudioManager(mp);

        textViewTime.setText("00:10:00");

        final Timer timer = new Timer();

 //       final CounterClass timer = new CounterClass(600000, 1000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (am.isStopped()) {
                    am.start();
                    btnStart.setText("PAUSE");
                    timer.start();
                }
            
                else if (am.isPaused()) {
                    am.resume();
                    btnStart.setText("PAUSE");
                    timer.notifyAll();
                }

                else if (am.isPlaying()) {
                    am.pause();
                    btnStart.setText("START");
                    try {
                        synchronized (timer) {
                            timer.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                UpdateGUI();}
            }, 0, 1000);
        }

        void UpdateGUI() {
            i++;
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    textViewTime.setText(String.valueOf(i));
                }
            });
         }






//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                timer.cancel();
//                textViewTime.setText("00:10:00");
//
//                if(am.isPlaying()){
//                    am.stop();
//                }
//            }
//        }
//    }
////
//    public class CounterClass extends CountDownTimer {
//
//        public CounterClass(long millisInFuture, long countdownInterval) {
//                super(millisInFuture, countdownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//            long millis = millisUntilFinished;
//            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
//                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.
//                            toHours(millis)),
//                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
//                            toMinutes(millis)));
//
//            System.out.println(hms);
//            textViewTime.setText(hms);
//        }
//
//        @Override
//        public void onFinish() {
//            textViewTime.setText("Completed.");
//        }
//    }
//
//    private void UpdateGUI() {
//        myHandler.post(WorkerTimer);
//    }

}