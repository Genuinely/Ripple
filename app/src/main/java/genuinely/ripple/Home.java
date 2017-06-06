package genuinely.ripple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private static Button button_sbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
            OnClickButtonListener();
    }

    public void OnClickButtonListener() {
        button_sbm = (Button)findViewById(R.id.button1);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("genuinely.ripple.MainActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
