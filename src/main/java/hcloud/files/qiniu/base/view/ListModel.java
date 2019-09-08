package hcloud.files.qiniu.base.view;

import java.util.List;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/1
 *
 * @author 杨丁辉
 */
public class ListModel<T> extends Object {
    private List<T> data;

    public List<T> getData() { return this.data; }



    public void setData(List<T> data) { this.data = data; }
}
