package cn.niit.englishhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.bean.Record;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:23:41
 */

public class HistoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<Record> recordList;

    public HistoryAdapter(Context context, List<Record> recordList) {
        this.mContext = context;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
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
            view = mInflater.inflate(R.layout.record_item, null);
            viewHolder.query = (TextView) view.findViewById(R.id.item_tv_query);
            viewHolder.explain = (TextView) view.findViewById(R.id.item_tv_explain);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.query.setText(recordList.get(i).getExplain());
        viewHolder.explain.setText(recordList.get(i).getQuery());
        return view;
    }

    private class ViewHolder {
        TextView query;
        TextView explain;

    }
}
