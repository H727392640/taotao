package com.taotao.common.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 15:06
 * Description: EU树形控件节点格式
 */
public class EUTreeNode {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
