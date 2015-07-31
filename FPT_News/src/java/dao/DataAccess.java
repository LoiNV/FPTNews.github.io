/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.News;

/**
 *
 * @author Admin
 */
public class DataAccess {
 
    public List<News> getAll() {
        List<News> list = new LinkedList<>();
        try {            
            Connection conn = DBUtil.getConnection();
            PreparedStatement pre = conn.prepareStatement("select * from tblNews");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("dt");
                String title = rs.getString("title");
                String description = rs.getString("descript");
                String details =rs.getString("details");
                String category = rs.getString("category");
                String imgPath = rs.getString("img");
                News news = new News( name, title, description, details, category, imgPath);
                news.setId(id);
                news.setDate(date);
                list.add(news);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean addNews(News n) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pre = conn.prepareStatement("insert into tblNews values (?,?,?,?,?,?,?)");
            pre.setString(1, n.getName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            pre.setString(2, dateFormat.format(date));
            pre.setString(3, n.getTitle());
            pre.setString(4, n.getDescript());
            pre.setString(5, n.getDetails());
            pre.setString(6, n.getCategory());
            pre.setString(7, n.getImg());
            int rows = pre.executeUpdate();
            conn.close();
            return rows >0;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void readNews() {
    }

    public boolean removeNews(int id) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pre = conn.prepareStatement("delete tblNews where id=?");
            pre.setInt(1, id);
            int rows = pre.executeUpdate();
            conn.close();
            return rows >0;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateNews( News n) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pre = conn.prepareStatement("update tblNews set name=?,dt=?,title=?,descript=?,details=?,category=?,img=? where id=?");
            pre.setString(1, n.getName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            pre.setString(2, dateFormat.format(date));
            pre.setString(3, n.getTitle());
            pre.setString(4, n.getDescript());
            pre.setString(5, n.getDetails());
            pre.setString(6, n.getCategory());
            pre.setString(7, n.getImg());
            pre.setInt(8, n.getId());
            int rows = pre.executeUpdate();
            conn.close();
            return rows >0;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void getByCategory() {
    }
    
    
    
    
}
