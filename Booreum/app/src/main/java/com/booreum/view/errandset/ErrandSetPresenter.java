package com.booreum.view.errandset;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.booreum.booreum.R;

public class ErrandSetPresenter implements I_ErrandSetPresenter{

    private Context context;
    private int categoryNumbering;

    public ErrandSetPresenter(Context context, int categoryNumbering) {
        this.context = context;
        this.categoryNumbering = categoryNumbering;
    }

    @Override
    public void setViewTitle(TextView set_title_tv , ImageView set_title_image,TextView check_title_tv , ImageView check_title_image) {
        String titleText="";
        int resource=0;

        switch (categoryNumbering){
            case 1:
                titleText = "가져다줘";
                resource = R.drawable.icon_bring_detail;
                break;
            case 2:
                titleText = "사다줘";
                resource = R.drawable.icon_buy_detail;
                break;
            case 3:
                titleText = "전달해줘";
                resource = R.drawable.icon_deliver_detail;
                break;
            case 4:
                titleText = "제출해줘";
                resource = R.drawable.icon_submit_detail;
                break;
            case 5:
                titleText = "프린트해줘";
                resource = R.drawable.icon_print_detail;
                break;
            case 6:
                titleText = "같이해줘";
                resource = R.drawable.icon_together_detail;
                break;
            case 7:
                titleText = "대신해줘";
                resource = R.drawable.icon_instead_detail;
                break;
            case 8:
                titleText = "기타";
                resource = R.drawable.icon_etc_detail;
                break;
        }

        set_title_tv.setText(titleText);
        check_title_tv.setText(titleText);
        set_title_image.setImageResource(resource);
        check_title_image.setImageResource(resource);
    }
}
