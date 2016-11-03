package test.bwei.com.listview_quanxuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import test.bwei.com.listview_quanxuan.R;

/**
 * Created by zhangjie on 2016/11/2.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> list ;
    private Context context;
    private boolean isHide;
    private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

    public MyAdapter(List<String> list, Context context, boolean isHide) {
        this.list = list;
        this.context = context;
        this.isHide = isHide;
        //初始化
        for (int i = 0; i < list.size(); i++) {
            getSelect().add(false);
        }
    }
    public List<Boolean> getSelect() {
        return linkedList;
    }

    public void setHide(boolean isHide){
        this.isHide = isHide;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if (view==null){
            view=View.inflate(context, R.layout.list_item,null);
            vh=new ViewHolder();
            vh.tv=(TextView) view.findViewById(R.id.tv_name);
            vh.ck=(CheckBox) view.findViewById(R.id.ck);

            view.setTag(vh);

        }else{
            vh=(ViewHolder)view.getTag();
        }
        vh.tv.setText(list.get(i));
        vh.ck.setChecked(linkedList.get(i));
        //不能用onCheckChangedListner()复用的时候
        vh.ck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                linkedList.set(i, ! linkedList.get(i));

                //刷新
                notifyDataSetChanged();
            }
        });
        return view;
    }
    class ViewHolder{
        TextView tv;
        CheckBox ck;
    }
}

