package test.bwei.com.listview_quanxuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.bwei.com.listview_quanxuan.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    private MyAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private boolean isHide;
    private Button btn_all;
    private Button btn_no;
    private Button btn_hide;
    private String arr[] = {"这是数据", "这是数据", "这是数据", "这是数据", "这是数据", "这是数据", "这是数据", "这是数据", "这是数据", "这是数据"};
    private ScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        scroll = (ScrollView) findViewById(R.id.scroll);
        scroll.smoothScrollTo(0,0);
        btn_all = (Button) findViewById(R.id.quanxuan);
        btn_all.setOnClickListener(this);
        btn_no = (Button) findViewById(R.id.fanxuan);
        btn_no.setOnClickListener(this);
        btn_hide = (Button) findViewById(R.id.yingcang);
        btn_hide.setOnClickListener(this);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);

        }
        lv = (ListView) findViewById(R.id.lv);

        adapter = new MyAdapter(list, this, isHide);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quanxuan:
                for (int i = 0; i < adapter.getSelect().size(); i++) {
                    adapter.getSelect().set(i, true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.fanxuan:
                if (!adapter.getSelect().contains(true)) {
                    // btn_all.setChecked(false);
                    Toast.makeText(getApplicationContext(), "请选择0", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < adapter.getSelect().size(); i++) {
                        if (adapter.getSelect().get(i)) {
                            adapter.getSelect().set(i, false);
                        } else {
                            adapter.getSelect().set(i, true);
                        }
                    }
                    if (!adapter.getSelect().contains(true)) {
                       // btn_all.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.yingcang:
                isHide = isHide == true ? false : true;
                hide(isHide);
                adapter.notifyDataSetChanged();
                break;
        }

    }

    private void hide(boolean isHide) {

        if (isHide)
            btn_hide.setText("取消隐藏");
        else
            btn_hide.setText("隐藏已选");

        adapter.setHide(isHide);
    }

}
