package   com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class FirstPage extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView;
    private int[] imageRes = {R.drawable.first, R.drawable.second, R.drawable.third};
    private String[] text1Array = {"FarmHub: Direct Link To Market", "Farm To Market: Simplified", "  Harvesting Prosperity: Farmer First"};
    private String[] text2Array = {"FarmHub bridges the gap for 120 million Indian farmers, enabling direct sales to business and retailers, ensuring fair prices and market access.", "Simplify the farm-to-market journey with our app,connecting Indian farmers directly with  buyers,enhancing income and reducing middlemen.", " Empower farmers with direct market access, fostering sustainable farming and equitable growth across India's agricultural sector."};
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private Button b1;
    private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        final ImageView imageView = findViewById(R.id.imageView);
        final TextView textView1 = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);
        final View dot1 = findViewById(R.id.dot1);
        final View dot2 = findViewById(R.id.dot2);
        final View dot3 = findViewById(R.id.dot3);

        b1=findViewById(R.id.nextButton);
        t1=findViewById(R.id.existingAccountTextView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstPage.this,Login.class);
                startActivity(intent);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstPage.this, FirstPage.class);
                startActivity(intent);
            }
        });
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % imageRes.length;

                imageView.setImageResource(imageRes[currentIndex]);
                textView1.setText(text1Array[currentIndex]);
                textView2.setText(text2Array[currentIndex]);

                dot1.setBackgroundResource(currentIndex == 0 ? R.drawable.green_dot : R.drawable.white_dot);
                dot2.setBackgroundResource(currentIndex == 1 ? R.drawable.green_dot : R.drawable.white_dot);
                dot3.setBackgroundResource(currentIndex == 2 ? R.drawable.green_dot : R.drawable.white_dot);

                horizontalScrollView.smoothScrollTo(currentIndex * horizontalScrollView.getWidth(), 0);

                handler.postDelayed(this, 5000); // Scroll every 2 seconds
            }
        };

        handler.postDelayed(runnable, 5000);
    }
}