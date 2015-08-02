/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.DataAccess;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import model.News;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class NewsBean implements Serializable {
    
    private int id;
    private String name;
    private String date;
    private String title;
    private String descript;
    private String details;
    private String category;
    private String img;

    private List<News> listNews;
    private DataTable newsTable;
    private List<String> catagorys;
    private List<News> listFiltered;
    private News selectNews;

    public NewsBean() {
        
    }
    
    public String getImgPath(String str){
        List<String> list = new LinkedList<>();
        list.add(".png");
        list.add(".jpg");
        list.add(".bmp");
        list.add(".gif");
        
        int i1 = str.indexOf("img src=\"");
        int i2 = 0;
        for (String e : list) {
            if ((str.toLowerCase()).contains(e)) {
                i2 = str.indexOf(e);
                break;
            }
        }
        if (i1>0 && i2>0) {
            return str.substring(i1, i2-i1+10).replaceAll("img src=\"", "");
        }
        
        return"";
    }
    
    public String add() {        
        DataAccess da = new DataAccess();
        this.name = "admin";        
        this.img = getImgPath(details);
        if ((title.trim()).isEmpty()) {
            title="Empty";
        }
        News news = new News(name, title, descript, details, category, img);
        boolean rs = da.addNews(news);
        if (rs) {
            return "show";
        } else {
            return "";
        }
    }

    public String remove() {
        News n = (News) newsTable.getRowData();
        DataAccess da = new DataAccess();
        da.removeNews(n.getId());
        return "";
    }

    public String update() {
        News n = (News) newsTable.getRowData();
        this.id = n.getId();
        this.name = n.getName();
        this.title = n.getTitle();
        this.descript = n.getDescript();
        this.details = n.getDetails();
        this.category = n.getCategory();
        this.img = n.getImg();
        return "update";
    }

    public String doUpdate() {
        News n = new News(name, title, descript, details, category, img);
        n.setId(id);
        DataAccess da = new DataAccess();
        da.updateNews(n);
        return "show";
    }

    public List<News> getListFiltered() {
        return listFiltered;
    }

    public void setListFiltered(List<News> listFiltered) {
        this.listFiltered = listFiltered;
    }

    public List<String> getCatagorys() {
        catagorys = new LinkedList<>();
        catagorys.add("Category 1");
        catagorys.add("Category 2");
        catagorys.add("Category 3");
        catagorys.add("Category 4");
        return catagorys;
    }

    public void setCatagorys(List<String> catagorys) {
        this.catagorys = catagorys;
    }

    public News getSelectNews() {
        return selectNews;
    }

    public void setSelectNews(News selectNews) {
        this.selectNews = selectNews;
    }
    
    public DataTable getNewsTable() {
        return newsTable;
    }

    public void setNewsTable(DataTable newsTable) {
        this.newsTable = newsTable;
    }

    public List<News> getListNews() {
        DataAccess da = new DataAccess();
        this.listNews = da.getAll();        
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
