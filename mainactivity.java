package com.example.challanapp;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private EditText vehicleNumberEditText;
    private EditText ownerNameEditText;
    private EditText fineAmountEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehicleNumberEditText = findViewById(R.id.vehicleNumberEditText);
        ownerNameEditText = findViewById(R.id.ownerNameEditText);
        fineAmountEditText = findViewById(R.id.fineAmountEditText);
        Button saveChallanButton = findViewById(R.id.saveChallanButton);
        saveChallanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChallan();
            }
        });
        loadChallans();
    }
    private void saveChallan() {
        String vehicleNumber = vehicleNumberEditText.getText().toString();
        String ownerName = ownerNameEditText.getText().toString();
        String fineAmount = fineAmountEditText.getText().toString();
        if (vehicleNumber.isEmpty() || ownerName.isEmpty() || fineAmount.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("vehicleNumber", vehicleNumber);
        editor.putString("ownerName", ownerName);
        editor.putString("fineAmount", fineAmount);
        editor.apply();
        vehicleNumberEditText.setText("");
        ownerNameEditText.setText("");
        fineAmountEditText.setText("");
        Toast.makeText(this, "Challan saved", Toast.LENGTH_SHORT).show();
        loadChallans();
    }
    private void loadChallans() {
        SharedPreferences sharedPreferences = getSharedPreferences("ChallanPrefs", Context.MODE_PRIVATE);
        String vehicleNumber = sharedPreferences.getString("vehicleNumber", null);
        String ownerName = sharedPreferences.getString("ownerName", null);
        String fineAmount = sharedPreferences.getString("fineAmount", null);
        List<String> challans = new ArrayList<>();
        if (vehicleNumber != null && ownerName != null && fineAmount != null) {
            challans.add("Vehicle: " + vehicleNumber + ", Owner: " + ownerName + ", Fine: " + fineAmount);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challans);
        ListView listView = findViewById(R.id.challanListView);
        listView.setAdapter(adapter);
    }
}
