package cn.niit.englishhelp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.List;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.adapter.HistoryAdapter;
import cn.niit.englishhelp.bean.BasicEntity;
import cn.niit.englishhelp.bean.Translation;
import cn.niit.englishhelp.bean.Web;
import cn.niit.englishhelp.database.DBoperation;
import cn.niit.englishhelp.bean.Record;
import cn.niit.englishhelp.database.ReviewDBOperation;
import cn.niit.englishhelp.utils.Constants;
import cn.niit.englishhelp.utils.HttpUtils;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:15:50
 */

public class HomeFragment extends Fragment {
    private EditText et_content;

    private Button btn_translate;

    private TextView tv_query, tv_pronunciation, tv_explain, tv_web;

    private DBoperation dBoperation;

    private ReviewDBOperation reviewDBOperation;

    private ListView lv;

    HistoryAdapter historyAdapter;

    private List<Record> recordList;

    private ImageView img_save, img_delete;

    private Translation translation;

    private BasicEntity basicEntity;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                translation = JSON.parseObject(msg.obj.toString(), Translation.class);
                tv_query.setText(translation.getQuery());
                basicEntity = translation.getBasic();
                tv_pronunciation.setText("");
                if (basicEntity.getPhonetic() != null) {
                    tv_pronunciation.append("[" + basicEntity.getPhonetic() + "]");
                }
                if (basicEntity.getUs_phonetic() != null) {
                    tv_pronunciation.append("美" + basicEntity.getUs_phonetic());
                }
                if (basicEntity.getUk_phonetic() != null) {
                    tv_pronunciation.append("英" + basicEntity.getUk_phonetic());
                }

                List<String> explains = basicEntity.getExplains();
                tv_explain.setText("");
                for (int j = 0; j < explains.size(); j++) {
                    tv_explain.append(explains.get(j));
                }
                List<Web> webs = translation.getWeb();
                tv_web.setText("");
                for (int k = 0; k < webs.size(); k++) {
                    tv_web.append((k + 1) + ":" + webs.get(k).getKey() + "\n");
                }

                Record record = new Record();
                record.setQuery(translation.getQuery());
                record.setExplain(basicEntity.getExplains().get(0));
                dBoperation.insetRecord(record);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, container, false);
        et_content = (EditText) view.findViewById(R.id.et_content);
        btn_translate = (Button) view.findViewById(R.id.btn_translate);
        tv_query = (TextView) view.findViewById(R.id.tv_query);
        tv_pronunciation = (TextView) view.findViewById(R.id.tv_pronunciation);
        tv_explain = (TextView) view.findViewById(R.id.tv_explain);
        tv_web = (TextView) view.findViewById(R.id.tv_web);
        img_save = (ImageView) view.findViewById(R.id.img_save);
        img_delete = (ImageView) view.findViewById(R.id.img_delete);
        lv = (ListView) view.findViewById(R.id.lv);

        dBoperation = new DBoperation(getActivity());
        reviewDBOperation = new ReviewDBOperation(getActivity());

        initViewPager();
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_save.setVisibility(View.VISIBLE);
                getData();
            }
        });
        img_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(translation.getQuery())) {
                    Record record = new Record();
                    record.setQuery(translation.getQuery());
                    record.setExplain(basicEntity.getExplains().get(0));
                    long flag = reviewDBOperation.insetRecord(record);
                    if (flag != 0) {
                        Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_LONG).show();
                    } else Toast.makeText(getActivity(), "该单词已存在", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), "请搜索", Toast.LENGTH_LONG).show();

            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBoperation.deleteRecord();
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void initViewPager() {
        recordList = dBoperation.queryRecord();
        historyAdapter = new HistoryAdapter(getActivity(), recordList);
        lv.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }

    private void getData() {
        if (!TextUtils.isEmpty(et_content.getText().toString())) {
            HttpUtils.doGetAsyn(Constants.URL + et_content.getText().toString(), new HttpUtils.CallBack() {
                @Override
                public void onRequestComplete(String result) {
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            });
        } else Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_LONG).show();


    }
}
