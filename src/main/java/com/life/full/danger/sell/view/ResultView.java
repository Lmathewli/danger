package com.life.full.danger.sell.view;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author LiHongHui
 * @date 2018/9/14 11:44
 * @description
 */
@Data
public class ResultView<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的对象
     */
    private T data;
}
