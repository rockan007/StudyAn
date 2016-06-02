package com.study.an.Expandable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.an.all.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**expandable_list
 * Created by admin on 2016/1/21.
 */
public class MainFragment extends Fragment  {
    ExpandableListView mExpandableListView;
    private View view;
    private List<String> parent;
    private HashMap<String,List<String>> map;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent, final Bundle savedInstanceState){
        view=inflater.inflate(R.layout.expandable_main_fragment,parent,false);
        mExpandableListView=(ExpandableListView)view.findViewById(R.id.expand_list_view);
        initData();
        mExpandableListView.setAdapter(new myExpandableListAdapter());
        mExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.getId();
                Log.e("position",i+" "+view.getId());
                Log.e("id",l+"");
                int groupPos=(int)view.getTag(R.id.parent_id);
                int childPos=(int)view.getTag(R.id.child_id);
                if(groupPos==-1){
                    switch (childPos){
                        case 0:
                            Toast.makeText(getActivity(),"这是父项",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(),"这是父项1",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getActivity(),"这是父项2",Toast.LENGTH_SHORT).show();
                            break;
                        default:break;
                    }

                }else {
                    switch (groupPos){
                        case 0:
                            switch (childPos){
                                case 0:Toast.makeText(getActivity(),R.string.expandable,Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:Toast.makeText(getActivity(), R.string.answer_false,Toast.LENGTH_LONG).show();
                                    break;
                                case 2:Toast.makeText(getActivity(),R.string.editText_hint,Toast.LENGTH_SHORT).show();
                            default:
                            break;
                            }
                            break;
                        case 1:
                            switch (childPos){
                                case 0:Toast.makeText(getActivity(),R.string.action_bar,Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:Toast.makeText(getActivity(),R.string.app_name,Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:Toast.makeText(getActivity(),R.string.back_step,Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;

                            }
                            break;
                        case 2:
                            switch (childPos){
                                case 0:Toast.makeText(getActivity(),R.string.communist_party,Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:Toast.makeText(getActivity(),R.string.crime_isSolved,Toast.LENGTH_LONG).show();
                                    break;
                                case 2:Toast.makeText(getActivity(),R.string.out_of_the_system,Toast.LENGTH_SHORT).show();
                                    break;
                                default:break;
                            }
                            break;
                            default:
                                break;

                    }
                }


                return true;
            }
        });
        return view;
    }
    private void initData(){
        map=new HashMap<String,List<String>>();
        parent=new ArrayList<>();
        parent.add("人族");
        parent.add("神族");
        parent.add("妖族");
        List<String> list=new ArrayList<>();
        list.add("大禹");
        list.add("顾工");
        list.add("工兵");
        map.put("人族", list);
        List<String> list1=new ArrayList<>();
        list1.add("玉皇大帝");
        list1.add("如来");
        list1.add("二郎神");
        map.put("神族", list1);
        List<String> list2=new ArrayList<>();
        list2.add("孙悟空");
        list2.add("牛魔王");
        list2.add("白骨精");
        map.put("妖族",list2);
    }
    class myExpandableListAdapter extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public int getChildrenCount(int i) {
            String key=parent.get(i);
            int size=map.get(key).size();
            return size;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            String key = parent.get(i);
            return (map.get(key).get(i1));
        }


        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        public myExpandableListAdapter() {
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            if(view==null){
                LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.expandable_list_parent,null);
            }
            TextView textView=(TextView)view.findViewById(R.id.expandable_list_parent);
            textView.setText(parent.get(i));
            view.setTag(R.id.parent_id, -1);
            view.setTag(R.id.child_id,i);
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            String key=parent.get(i);
            String info=map.get(key).get(i1);
            if(view==null){
                LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.expandable_list_child,null);
            }
            TextView childText=(TextView)view.findViewById(R.id.expandable_list_children);
            childText.setText(info);
            view.setTag(R.id.parent_id, i);
            view.setTag(R.id.child_id,i1);
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
