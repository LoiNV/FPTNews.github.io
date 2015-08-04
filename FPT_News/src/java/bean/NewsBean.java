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
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.News;
import org.primefaces.component.datatable.DataTable;

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
    private List<News> randomImages;
    
    DataAccess da = new DataAccess();

    public NewsBean() {        
        
        this.listNews = da.getAll();

        this.catagorys = new LinkedList<>();
        this.catagorys.add("Cóc Tin");
        this.catagorys.add("Cóc Sống");
        this.catagorys.add("Cóc Học");
        this.catagorys.add("Cóc Làm");
        this.catagorys.add("Cóc Cụ");
        this.catagorys.add("Cóc Buôn");
        this.catagorys.add("FPT Edu");
        
    }
        
    public List<News> getRandom(){
        Set<Integer> set = new HashSet<>();
        Random rd = new Random();
        List<News> list = new LinkedList<>();
        
        while (set.size() < 5) {
            int i = rd.nextInt(listNews.size());
            set.add(i);
        }
        for (Integer s : set) {            
            list.add(listNews.get(s));
        }
        return list;
    }

    public String getImgPath(String str) {
        
        String pattern = "([a-z\\-_0-9\\/\\:\\.]*\\.(jpg|jpeg|png|gif))";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(0);
        } else {
            return "";
        }
    }

    public String add() {
        DataAccess da = new DataAccess();
        this.name = "User 2";
        this.img = getImgPath(this.details);
        
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
        return "update";
    }

    public String doUpdate() {
        this.img = getImgPath(this.details);
        News n = new News(name, title, descript, details, category, img);
        n.setId(id);
        DataAccess da = new DataAccess();
        da.updateNews(n);
        return "show";
    }

  
    public List<News> getRandomImage() {
        return getRandom();
    }

    public void setRandomImage(List<News> getRandomImage) {
        this.randomImages = getRandomImage;
    }
  
    public List<News> getListFiltered() {
        return listFiltered;
    }

    public void setListFiltered(List<News> listFiltered) {
        this.listFiltered = listFiltered;
    }

    public List<String> getCatagorys() {

        return catagorys;
    }

    public void setCatagorys(List<String> catagorys) {
        this.catagorys = catagorys;
    }

    public DataTable getNewsTable() {
        return newsTable;
    }

    public void setNewsTable(DataTable newsTable) {
        this.newsTable = newsTable;
    }

    public List<News> getListNews() {
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
