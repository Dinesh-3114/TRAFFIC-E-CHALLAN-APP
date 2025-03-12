package com.example.challanapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.challanapp.Challan;
import java.util.ArrayList;
public class ChallanAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<com.example.challanapp.Challan> challanList;
    public ChallanAdapter(Context context, ArrayList<com.example.challanapp.Challan> challanList) {
        this.context = context;
        this.challanList = challanList;
    }@Override
    public int getCount() {
        return challanList.size();
    }@Override
    public Object getItem(int position) {
        return challanList;
    }@Override
    public long getItemId(int position) {
        return position;
    }@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.challan_item, parent, false);
        }
        com.example.challanapp.Challan challan;
        challan = (com.example.challanapp.Challan) getItem(position);
        TextView vehicleNumberTextView = convertView.findViewById(R.id.vehicleNumberTextView);
        TextView ownerNameTextView = convertView.findViewById(R.id.ownerNameTextView);
        TextView fineAmountTextView = convertView.findViewById(R.id.fineAmountTextView);
        vehicleNumberTextView.setText(challan.getVehicleNumber());
        ownerNameTextView.setText(challan.getOwnerName());
        fineAmountTextView.setText(challan.getFineAmount());
        return convertView;
    }
}
