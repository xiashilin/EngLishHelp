package cn.niit.englishhelp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.adapter.ReViewAdapter;
import cn.niit.englishhelp.bean.Record;
import cn.niit.englishhelp.database.ReviewDBOperation;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:16:15
 */

public class ReviewFragment extends Fragment {
    private ListView review_lv;
    private ReviewDBOperation reviewDBOperation;
    private List<Record> recordList;

    public static ReviewFragment newInstance() {
        ReviewFragment reviewFragment = new ReviewFragment();
        return reviewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_review, container, false);
        review_lv = (ListView) view.findViewById(R.id.review_lv);
        reviewDBOperation = new ReviewDBOperation(getActivity());
        recordList = reviewDBOperation.queryRecord();
        if (recordList.size() == 0) {
            LayoutInflater mInflater = LayoutInflater.from(getActivity());
            view = mInflater.inflate(R.layout.layout_empty, null);
            review_lv.setEmptyView(view);
        }

        ReViewAdapter reViewAdapter = new ReViewAdapter(getActivity(), recordList);
        review_lv.setAdapter(reViewAdapter);

        review_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                reviewDBOperation.deleteRecord(recordList.get(i).getQuery());
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return view;
    }
}
