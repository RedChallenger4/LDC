package app.theducksneezes.com.ldc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Andie on 11/25/2017.
 */

public class PhonecallFragment extends Fragment {
    private static final String TAG = "PhonecallFragment";

    private TelephonyManager mTelephonyManager;
    private String connectedWith;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phonecall, container, false);
        String[] contactNames = {"Randy Pierce", "Joshua Cadavez", "Anthony Phan", "Anders Zetterlund", "Cassandra Renfrew"};

        mTelephonyManager = (TelephonyManager) getActivity().getSystemService(getActivity().getApplicationContext().TELEPHONY_SERVICE);
        ListView listView = view.findViewById(R.id.contacts);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                contactNames
        );

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.CALL_PHONE") != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE"}, 101);
                    return;
                }
                if(position == 0) { //Randy
                    String no = "tel:0000000000";
                    connectedWith = "Randy";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(no)));
                } else if (position == 1) { //Josh
                    String no = "tel:0000000000";
                    connectedWith = "Josh";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(no)));
                } else if (position == 2) { //Tony
                    String no = "tel:0000000000";
                    connectedWith = "Tony";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(no)));
                } else if (position == 3) { //Andy
                    String no = "tel:0000000000";
                    connectedWith = "Andy";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(no)));
                } else if (position == 4) { //Andie
                    String no = "tel:0000000000";
                    connectedWith = "Andie";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(no)));
                }
            }
        });

        return view;
    }
}