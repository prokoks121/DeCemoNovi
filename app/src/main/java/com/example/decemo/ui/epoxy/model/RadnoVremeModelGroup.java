//package com.example.decemo.ui.epoxy.model;
//
//import android.view.View;
//
//import com.airbnb.epoxy.EpoxyModel;
//import com.airbnb.epoxy.EpoxyModelGroup;
//import com.example.decemo.R;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class RadnoVremeModelGroup extends EpoxyModelGroup {
//
//
//    public RadnoVremeModelGroup(data data) {
//        super(R.layout.epoxy_radno_vreme_lokal, modelgroup(data));
//        id("RadnoVreme");
//    }
//
//
//    private static List<EpoxyModel<?>> modelgroup(data data) {
//        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//        if (currentDay == 1)
//            currentDay = 8;
//        ArrayList<EpoxyModel<?>> models = new ArrayList<>();
//        int i = 1;
//
//        if (data.showAll) {
//            for (int j = 0; j < data.list.size(); j++) {
//                models.add(new LokalRadnoVremeViewModel_().id(i++)
//                        .type(data.list.get(j))
//                        .clickListener(new View.OnClickListener() {
//
//                            @Override
//                            public void onClick(View v) {
//                                data.callBack.onClick();
//
//                            }
//                        }).day(i));
//            }
//        } else {
//            models.add(new LokalRadnoVremeViewModel_().id(i)
//                    .type(data.list.get(currentDay - 2))
//                    .clickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            data.callBack.onClick();
//
//                        }
//                    }).day(currentDay));
//        }
//
//
//        return models;
//    }
//
//
//    public interface callBack {
//        void onClick();
//    }
//
//    public static class data {
//        private callBack callBack;
//        private ArrayList<String> list;
//        private Boolean showAll;
//
//        public data(RadnoVremeModelGroup.callBack callBack, ArrayList<String> list, Boolean showAll) {
//            this.callBack = callBack;
//            this.list = list;
//            this.showAll = showAll;
//        }
//
//        public RadnoVremeModelGroup.callBack getCallBack() {
//            return callBack;
//        }
//
//        public void setCallBack(RadnoVremeModelGroup.callBack callBack) {
//            this.callBack = callBack;
//        }
//
//        public ArrayList<String> getList() {
//            return list;
//        }
//
//        public void setList(ArrayList<String> list) {
//            this.list = list;
//        }
//
//        public Boolean getShowAll() {
//            return showAll;
//        }
//
//        public void setShowAll(Boolean showAll) {
//            this.showAll = showAll;
//        }
//    }
//}