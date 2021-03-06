package app.theducksneezes.com.ldc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Andie on 11/25/2017.
 */

public class VideocallFragment extends Fragment {
    private static final String TAG = "VideocallFragment";

    private Button btnTest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videocall, container, false);
        btnTest = (Button) view.findViewById(R.id.button2);

        btnTest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Testing Button Click - video", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
