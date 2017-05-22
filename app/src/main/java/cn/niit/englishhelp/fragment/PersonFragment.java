package cn.niit.englishhelp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.ui.AddRecordActivity;
import cn.niit.englishhelp.ui.LoginActivity;
import cn.niit.englishhelp.ui.RegisterActivity;
import cn.niit.englishhelp.ui.StudyRecordActivity;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:15:52
 */

public class PersonFragment extends Fragment implements View.OnClickListener {

    private TableLayout mine_add, mine_quit, mine_add_record, mine_study;

    public static PersonFragment newInstance() {
        PersonFragment personFragment = new PersonFragment();
        return personFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_person, container, false);
        mine_add = (TableLayout) view.findViewById(R.id.mine_add);
        mine_quit = (TableLayout) view.findViewById(R.id.mine_quit);
        mine_add_record = (TableLayout) view.findViewById(R.id.mine_add_record);
        mine_study = (TableLayout) view.findViewById(R.id.mine_study);
        mine_add.setOnClickListener(this);
        mine_quit.setOnClickListener(this);
        mine_add_record.setOnClickListener(this);
        mine_study.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_add:
                Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
                startActivity(alarmas);
                break;
            case R.id.mine_quit:
                System.exit(0);
                break;
            case R.id.mine_add_record:
                startActivity(new Intent(getActivity(), AddRecordActivity.class));
                break;
            case R.id.mine_study:
                startActivity(new Intent(getActivity(), StudyRecordActivity.class));
                break;
        }
    }
}
