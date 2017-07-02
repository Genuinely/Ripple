package genuinely.ripple;



import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;

    TextView textViewTime;
    AudioManager am;
    final android.os.Handler myHandler = new android.os.Handler();
    long remainingTime = 600000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        final MediaPlayer mp = new MediaPlayer();


        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("Meditation.mp3");
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        am = new AudioManager(mp);

        textViewTime.setText("00:10:00");

        class CounterClass extends CountDownTimer {

            long millis;

            public CounterClass(long millisInFuture, long countdownInterval) {
                super(millisInFuture, countdownInterval);
            }

            public long getMillis() {
                return millis;
            }

            public void onTick(long millisUntilFinished) {

                millis = millisUntilFinished;
                final String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.
                                toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes(millis)));

                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewTime.setText(hms);
                    }
                });
            }

            @Override
            public void onFinish() {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewTime.setText("Completed.");
                    }
                });
            }


        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            CounterClass timer = null;

            public void onClick(View view) {
                if (am.isStopped()) {
                    am.start();
                    btnStart.setText("PAUSE");
                    timer = new CounterClass(remainingTime, 1000);
                    timer.start();

                } else if (am.isPaused()) {
                    am.resume();
                    btnStart.setText("PAUSE");
                    timer = new CounterClass(remainingTime, 1000);
                    timer.start();

                } else if (am.isPlaying()) {
                    am.pause();
                    btnStart.setText("START");
                    if (timer != null) {
                        timer.cancel();
                        remainingTime = timer.getMillis();
                    }
                }
            }
        });
    }
}
