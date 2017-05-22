package cn.niit.englishhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.bean.StudyBean;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:21:43
 */

public class StudyAdapter extends BaseAdapter {
    private Context mContext;
    private List<StudyBean> studyBeanList;

    public StudyAdapter(Context context, List<StudyBean> studyBeanList) {
        this.mContext = context;
        this.studyBeanList = studyBeanList;
    }

    @Override
    public int getCount() {
        return studyBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return studyBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.study_item, null);
            viewHolder.title = (TextView) view.findViewById(R.id.item_tv_title);
            viewHolder.content = (TextView) view.findViewById(R.id.item_tv_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(studyBeanList.get(i).getTitle());
        viewHolder.content.setText(studyBeanList.get(i).getContent());
        return view;
    }

    private class ViewHolder {
        TextView title;
        TextView content;

    }
}
