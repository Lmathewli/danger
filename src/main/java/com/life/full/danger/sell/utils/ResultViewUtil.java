package com.life.full.danger.sell.utils;

import com.life.full.danger.sell.view.ResultView;

/**
 * @author LiHongHui
 * @date 2018/9/14 14:52
 * @description
 */
public class ResultViewUtil {
    public static ResultView success(Object object) {
        ResultView resultView = new ResultView();
        resultView.setData(object);
        resultView.setMsg("成功");
        resultView.setCode(0);
        return resultView;
    }

    public static ResultView success() {
        return success(null);
    }

    public static ResultView error(Integer code, String msg) {
        ResultView resultView = new ResultView();
        resultView.setMsg(msg);
        resultView.setCode(code);
        return resultView;
    }

}
