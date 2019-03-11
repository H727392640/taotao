package com.taotao.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taotao.pojo.TbItem;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 15:52
 * Description: No Description
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        if (this.getImage() != null) {
            String[] images = this.getImage().split(",");
            return images;
        }
        return null;
    }
}
