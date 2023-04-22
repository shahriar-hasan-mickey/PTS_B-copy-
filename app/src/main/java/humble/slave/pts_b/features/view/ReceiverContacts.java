package humble.slave.pts_b.features.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import humble.slave.pts_b.databinding.ActivityReceiverContactsBinding;
import humble.slave.pts_b.databinding.ActivityWeatherBinding;

public class ReceiverContacts extends AppCompatActivity {
    ActivityReceiverContactsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceiverContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        back(binding.goBack);
        test(binding.testSMS);
    }

    public void back(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void test(View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+", null, "test", null, null);
            }
        });
    }
}