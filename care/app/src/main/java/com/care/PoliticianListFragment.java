package com.care;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.care.model.Politician;
import com.care.model.PoliticianListModel;
import com.care.model.PoliticianWebService;
import com.care.model.PoliticianWebServiceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class PoliticianListFragment extends Fragment implements MyItemRecyclerViewAdapter.AdapterDelegate {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    PoliticianListModel politicianListModel = PoliticianListModel.getInstance();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PoliticianListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PoliticianListFragment newInstance(int columnCount) {
        PoliticianListFragment fragment = new PoliticianListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_politican_list_list, container, false);
        RecyclerView listView = fragView.findViewById(R.id.list);

        PoliticianListFragment fragment = this;

        Context context = listView.getContext();
        if (mColumnCount <= 1) {
            listView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            listView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(politicianListModel.getPoliticianList());
        adapter.delegate = this;
        listView.setAdapter(adapter);

        if(getArguments() == null) {
            this.politicianListModel.updatedList(new PoliticianListModel.UpdateListCompletionHandler() {
                @Override
                public void didComplete() {
                    MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(politicianListModel.getPoliticianList());
                    adapter.delegate = fragment;
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        if(getArguments() != null) {
            TextView textViewState = fragView.findViewById(R.id.textViewState);
            String state = getArguments().getString("state");
            if (state.length() > 0)
                textViewState.setText(state + " Legislators");

            TextView textViewType = fragView.findViewById(R.id.textViewType);
            String type = getArguments().getString("type");
            if (type.length() > 0)
                textViewType.setText(type);

            TextView textViewParty = fragView.findViewById(R.id.textViewParty);
            String party = getArguments().getString("party");
            if (party.length() > 0)
                textViewParty.setText(party);

            List<Politician> politicians = new ArrayList<>();
            this.politicianListModel.updatedList(new PoliticianListModel.UpdateListCompletionHandler() {
                @Override
                public void didComplete() {
                    for (Politician politician : politicianListModel.getPoliticianList()) {
                        if ((state.length() == 0 || politician.state.equals(state)) && (type.length() == 0 || politician.type.equals(type)) && (party.length() == 0 || politician.party.equals(party)))
                            politicians.add(politician);
                    }
                    fragment.politicianListModel.setPoliticianList(politicians);

                    MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(politicians);
                    adapter.delegate = fragment;
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        fragView.findViewById(R.id.buttonSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_politicanListFragment_to_settingsFragment);
            }
        });

        fragView.findViewById(R.id.buttonHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_politicanListFragment_to_helpFragment);
            }
        });

        return fragView;
    }

    public void didSelectRow(View view, int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        Navigation.findNavController(view).navigate(R.id.action_politicanListFragment_to_politicanFragment, bundle);
    }

    public List<Politician> getList(){
        return politicianListModel.getPoliticianList();
    }
}