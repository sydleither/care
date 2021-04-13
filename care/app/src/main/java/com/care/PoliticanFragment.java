package com.care;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.care.model.Politician;
import com.care.model.PoliticianListModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoliticanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PoliticanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PoliticanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PoliticanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PoliticanFragment newInstance(String param1, String param2) {
        PoliticanFragment fragment = new PoliticanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_politican, container, false);

        List<Politician> politicianList = PoliticianListModel.getInstance().getPoliticianList();
        Politician politician = politicianList.get(getArguments().getInt("index"));

        TextView textViewName = fragView.findViewById(R.id.textViewPoliticianName);
        textViewName.setText(politician.name);
        TextView textViewType = fragView.findViewById(R.id.textViewPoliticianType);
        textViewType.setText(politician.type);
        TextView textViewState = fragView.findViewById(R.id.textViewPoliticianState);
        textViewState.setText(politician.state);
        TextView textViewParty = fragView.findViewById(R.id.textViewPoliticianParty);
        textViewParty.setText(politician.party);

        fragView.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        return fragView;
    }
}