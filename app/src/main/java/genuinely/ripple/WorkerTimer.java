package genuinely.ripple;

import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

/**
 * Created by neilyeung on 5/22/17.
 */
public class WorkerTimer extends CountDownTimer implements Runnable {

    public WorkerTimer(long millisInFuture, long countdownInterval) {
        super(millisInFuture, countdownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

        long millis = millisUntilFinished;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.
                        toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                        toMinutes(millis)));

        System.out.println(hms);
        textViewTime.setText(hms);
    }

    @Override
    public void run() {
        
    }

    @Override
    public void onFinish() {
        textViewTime.setText("Completed.");
    }
}


}
