package com.wdk.wanandroid.data.bean.home;

import com.wdk.component_base.data.bean.BaseBean;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 6:11 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 6:11 PM
 * @LastCheckBy: wdk
 */
public class BannerChildBean extends BaseBean {

    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://wanandroid.com/blogimgs/affe33fb-a160-4c63-bcc5-2ba83965a7bc.png
     * isVisible : 1
     * order : 0
     * title : 现在的Android程序员，要具备什么能力才能保持竞争
     * type : 0
     * url : https://www.bilibili.com/video/BV1gp4y1k7mw
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
