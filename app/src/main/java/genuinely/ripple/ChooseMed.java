package genuinely.ripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


public class ChooseMed extends AppCompatActivity {
    private List<Lesson> chooseLessons = new ArrayList<Lesson>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_med);

        populateLessonList();
    }

    private void populateLessonList() {
        chooseLessons.add(new Lesson("Earth Meditation", R.drawable.earth));
    }
}
