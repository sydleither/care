package com.care.model;

import android.widget.TextView;

import com.care.R;
import com.care.model.Politician;

import java.util.ArrayList;
import java.util.List;

public class PoliticianListModel {
    private static PoliticianListModel single_instance = null;
    private List<Politician> politicianList = new ArrayList<>();

    public interface UpdateListCompletionHandler {
        void didComplete();
    }

    private PoliticianListModel() {}

    public static PoliticianListModel getInstance() {
        if(single_instance == null)
            single_instance = new PoliticianListModel();
        return single_instance;
    }

    public void updatedList(UpdateListCompletionHandler handler) {
        PoliticianWebServiceModel model = new PoliticianWebServiceModel();
        model.getPoliticians(new PoliticianWebServiceModel.GetPoliticiansResponse() {
            @Override
            public void response(ArrayList<PoliticianWebService> politicians) {
                politicianList.clear();
                for(PoliticianWebService politicianWebService : politicians) {
                    Politician politician = new Politician(politicianWebService.name, politicianWebService.type, politicianWebService.state, politicianWebService.party, politicianWebService.twitter);
                    politicianList.add(politician);
                }
                handler.didComplete();
            }
            @Override
            public void error() {
                politicianList.clear();
                Politician politician = new Politician("Error","","","","");
                politicianList.add(politician);
                handler.didComplete();
            }
        });

    }

    public int getCount() {
        return politicianList.size();
    }

    public List<Politician> getPoliticianList() {
        return politicianList;
    }
}
