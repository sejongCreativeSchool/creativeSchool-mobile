package com.booreum.view.main.fragment.list;

import com.booreum.model.ErrandResults;

public  interface I_ListView {
    void setProgress(int visibility);
    void setData(ErrandResults results);
}
