/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.DataAccess;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import model.News;

/**
 *
 * @author Admin
 */
@ManagedBean(name = "navigate")
@SessionScoped
public class NavigateBean {

    private List<News> listNews;
    private News selectNews;
    private List<News> itemByCategory;
    private String selectCategory;
    private String keySearch;
    private List<News> listSearch;

    public NavigateBean() {
        DataAccess da = new DataAccess();
        this.listNews = da.getAll();
    }

    public void searchStringValueChanged(ValueChangeEvent vce) {
        
        try {
            keySearch = (String)vce.getNewValue();
            listSearch = new LinkedList<>();
            for (News n : listNews) {
                if ((n.getTitle().toLowerCase()).contains(keySearch.toLowerCase())) {
                    listSearch.add(n);
                }
            }
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("search.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(NavigateBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchPage() {
        
        
    }

    public void categoryPage() {
        try {
            itemByCategory = new LinkedList<>();
            for (News c : listNews) {
                if (c.getCategory().equals(this.selectCategory)) {

                    itemByCategory.add(c);
                }
            }
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("category.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(NewsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String itemPage() {

        this.selectCategory = this.selectNews.getCategory();

        return "item";
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public List<News> getListSearch() {
        return listSearch;
    }

    public void setListSearch(List<News> listSearch) {
        this.listSearch = listSearch;
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }

    public News getSelectNews() {
        return selectNews;
    }

    public void setSelectNews(News selectNews) {
        this.selectNews = selectNews;
    }

    public List<News> getItemByCategory() {
        itemByCategory = new LinkedList<>();
        for (News c : listNews) {
            if (c.getCategory().equals(this.selectCategory)) {

                itemByCategory.add(c);
            }
        }
        return itemByCategory;
    }

    public void setItemByCategory(List<News> itemByCategory) {
        this.itemByCategory = itemByCategory;
    }

    public String getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(String selectCategory) {
        this.selectCategory = selectCategory;
    }

}
