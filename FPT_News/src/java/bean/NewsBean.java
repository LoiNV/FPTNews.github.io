/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.DataAccess;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import model.News;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class NewsBean {

    private List<News> listNews;
    private int id;
    private String name;
    private String date;
    private String title;
    private String descript;
    private String details;
    private String category;
    private String img;
    
    private HtmlDataTable newsTable;

    public NewsBean() {
    }

    public String add() {
        DataAccess da = new DataAccess();
        News news = new News( name, title, descript, details, category, img);
        boolean rs = da.addNews(news);
        if (rs) {
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
            return "";
        }
    }

    public String remove(){
        News n = (News) newsTable.getRowData();
        DataAccess da = new DataAccess();
        boolean rs = da.removeNews(n.getId());
        FacesMessage msg = null;
        if (rs) {
            msg = new FacesMessage(n.getName()+" was removed");
        }else{
            msg = new FacesMessage("Error removing");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "admin";
    }
    
    public String update(){
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
    
    public String doUpdate(){
        News n = new News(name, title, descript, details, category, img);
        n.setId(id);
        DataAccess da = new DataAccess();
        boolean rs = da.updateNews(n);
        FacesMessage msg = null;
        if (rs) {
            msg = new FacesMessage(n.getName()+" was updated");
        }else{
            msg = new FacesMessage("Error updating");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "admin";
    }

    public HtmlDataTable getNewsTable() {
        return newsTable;
    }

    public void setNewsTable(HtmlDataTable newsTable) {
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
