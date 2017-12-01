package app.theducksneezes.com.ldc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private Button btnTest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phonecall, container, false);
//        Map<String, String> contacts= new LinkedHashMap<>();
//        contacts.put("Randy Pierce", "0000000000");
//        contacts.put("Joshua Cadavez", "0000000000");
//        contacts.put("Tony Phan", "0000000000");
//        contacts.put("Anders Zetterlund", "0000000000");
//        contacts.put("Cassandra Renfrew", "0000000000");
//
//        String[] contactNames = contacts.keySet().toArray(new String[contacts.keySet().size()]);
        String[] contactNames = {"Randy Pierce", "Joshua Cadavez", "Anthony Phan", "Anders Zetterlund", "Cassandra Renfrew"};

        ListView listView = view.findViewById(R.id.contacts);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                contactNames
        );
//        getActivity(),
//                android.R.layout.simple_list_item_1,
//                contacts.keySet().toArray()

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0) { //Randy
                    Toast.makeText(getActivity(), "Randy", Toast.LENGTH_SHORT).show();
                } else if (position == 1) { //Josh
                    Toast.makeText(getActivity(), "Joshua", Toast.LENGTH_SHORT).show();

                } else if (position == 2) { //Tony
                    Toast.makeText(getActivity(), "Tony", Toast.LENGTH_SHORT).show();
                } else if (position == 3) { //Andy

                } else if (position == 4) { //Andie

                }
            }
        });

        return view;
    }
}