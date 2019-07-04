package rjgc.cn.dateswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 2016-3-1
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //年选择控件
    private TextView mTvPrevDate;
    private TextView mTvCurrentDate;
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;
    private TextView mTvNextDate;

    private CalendarUtils cu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cu=new CalendarUtils();
        this.mCurrentYear = CalendarUtils.getYear();
        this.mCurrentMonth = CalendarUtils.getMonth();
        this.mCurrentDay = CalendarUtils.getDayOfMonth();
//        this.mCurrentYear = CalendarUtils.getYear();
//        this.mCurrentMonth = 8;
//        this.mCurrentDay = 31;

        this.mTvPrevDate = (TextView)findViewById(R.id.prev_tv);
        this.mTvPrevDate.setOnClickListener(this);
        this.mTvCurrentDate = (TextView)findViewById(R.id.current_tv);
        this.mTvNextDate = (TextView)findViewById(R.id.next_tv);
        this.mTvNextDate.setOnClickListener(this);
        dayChange();
    }

    private void dayChange(){
        if(mCurrentDay==1){
            if(this.mCurrentMonth==1){
                this.mTvPrevDate.setText((this.mCurrentYear-1)+"年12月31日");
            }else {
                this.mTvPrevDate.setText((this.mCurrentMonth-1) + "月" + cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth-1) + "日");
            }
        }else {
            this.mTvPrevDate.setText(this.mCurrentYear + "年" + this.mCurrentMonth + "月" + (mCurrentDay - 1) + "日");
        }
        this.mTvCurrentDate.setText(this.mCurrentMonth + "月" + (mCurrentDay) + "日");

        if(mCurrentDay==cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth)){
            if(this.mCurrentMonth==12){
                this.mTvNextDate.setText((this.mCurrentYear+1)+"年1月1日");
            }else {
                this.mTvNextDate.setText((this.mCurrentMonth+1) + "月1日");
            }
        }else {
            this.mTvNextDate.setText(this.mCurrentMonth + "月" + (mCurrentDay + 1) + "日");
        }

        if(this.mCurrentDay==CalendarUtils.getDayOfMonth() && this.mCurrentMonth==CalendarUtils.getMonth() && this.mCurrentYear==CalendarUtils.getYear()){
            this.mTvNextDate.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prev_tv:
                this.mCurrentDay--;
                if(this.mCurrentDay==1){
                    if(this.mCurrentMonth==1){
                        this.mTvPrevDate.setText((this.mCurrentYear-1)+"年12月31日");
                        this.mTvCurrentDate.setText("1月1日");
                        this.mTvNextDate.setText("1月2日");
                    }else {
                        this.mTvPrevDate.setText((this.mCurrentMonth-1) + "月" + cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth-1) + "日");
                        this.mTvCurrentDate.setText(this.mCurrentMonth + "月1日");
                        this.mTvNextDate.setText(this.mCurrentMonth + "月2日");
                    }

                }else if(this.mCurrentDay==0){

                    if(this.mCurrentMonth==1){
                        this.mCurrentYear--;
                        this.mCurrentMonth=12;
                        this.mCurrentDay=31;
                        this.mTvNextDate.setText("1月1日");
                    }else {
                        this.mTvNextDate.setText(this.mCurrentMonth + "月1日");
                        this.mCurrentMonth--;
                        this.mCurrentDay=cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth);
                    }
                    this.mTvPrevDate.setText((this.mCurrentMonth) + "月" + (this.mCurrentDay-1) + "日");
                    this.mTvCurrentDate.setText(this.mCurrentMonth + "月" + this.mCurrentDay + "日");
                }else {
                    dayChange();
                }
                if(!mTvNextDate.isEnabled()){
                    mTvNextDate.setEnabled(true);
                }
                break;
            case R.id.next_tv:
                this.mCurrentDay++;
                if(this.mCurrentDay==cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth)){
                    this.mTvPrevDate.setText((this.mCurrentMonth) + "月" + (this.mCurrentDay-1) + "日");
                    this.mTvCurrentDate.setText(this.mCurrentMonth + "月" + this.mCurrentDay + "日");
                    if(this.mCurrentMonth==12){
                        this.mTvNextDate.setText((this.mCurrentYear+1)+"年1月1日");
                    }else {
                        this.mTvNextDate.setText((this.mCurrentMonth+1) + "月1日");
                    }
                }else if(this.mCurrentDay==(cu.getLastDayOfMonth(this.mCurrentYear,this.mCurrentMonth)+1)){
                    if(this.mCurrentMonth==12){
                        this.mTvPrevDate.setText("12月31日");
                        this.mCurrentYear++;
                        this.mCurrentMonth=1;
                    }else {
                        this.mTvPrevDate.setText((this.mCurrentMonth) + "月" + cu.getLastDayOfMonth(this.mCurrentYear, (this.mCurrentMonth)) + "日");
                        this.mCurrentMonth++;
                    }
                    this.mTvCurrentDate.setText((this.mCurrentMonth) + "月1日");
                    this.mTvNextDate.setText(this.mCurrentYear+"年"+(this.mCurrentMonth) + "月2日");
                    this.mCurrentDay=1;
                }else {
                    dayChange();
                }
                if(this.mCurrentDay==CalendarUtils.getDayOfMonth() && this.mCurrentMonth==CalendarUtils.getMonth() && this.mCurrentYear==CalendarUtils.getYear()){
                    this.mTvNextDate.setEnabled(false);
                }
                break;
            default:
                break;
        }
    }
}
