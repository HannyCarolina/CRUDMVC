/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcorrea
 */
public class Querty implements SQLInterface {

    Config config;
    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    ResultSetMetaData rsmd;
    Var var;

    public Querty() {
        config = new Config();
        var = new Var();
    }

///insertar
    @Override
    public boolean create(Object ob) {
        var = (Var) ob;
        String sql = "INSERT INTO Users (Domain, Email, FullName, GrantLevel, UserName, Password) VALUES (?,?,?,?,?,?)";
        try {

            con = DriverManager.getConnection(config.getConnectionstr());
            ps = con.prepareStatement(sql);

            ps.setString(1, var.getDomain());
            ps.setString(2, var.getEmail());
            ps.setString(3, var.getFullName());
            ps.setString(4, var.getNivel());
            ps.setString(5, var.getName());
            ps.setString(6, var.getPassword());
            ps.executeUpdate();
            con.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Querty.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

///consultar
    @Override
    public ArrayList<Object[]> read() {
        String sql = "SELECT ID, UserName, FullName, Password, Email, Domain, GrantLevel FROM Users";
        ArrayList<Object[]> data = new ArrayList<>();
        try {
            con = DriverManager.getConnection(config.getConnectionstr());
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();

            while (rs.next()) {

                Object[] fila = new Object[rsmd.getColumnCount()];

                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                data.add(fila);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Querty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

///actualizar
    @Override
    public boolean update(Object ob) {
        var = (Var) ob;
        System.out.println(var);
        String sql = "UPDATE Users SET Domain=?, Email =?, FullName =?, GrantLevel =?, UserName =?, Password =? WHERE ID =?";
        try {
            con = DriverManager.getConnection(config.getConnectionstr());
            ps = con.prepareStatement(sql);

            ps.setString(1, var.getDomain());
            ps.setString(2, var.getEmail());
            ps.setString(3, var.getFullName());
            ps.setString(4, var.getNivel());
            ps.setString(5, var.getName());
            ps.setString(6, var.getPassword());
            ps.setString(7, Integer.toString(var.getId()));
            ps.executeUpdate();
            con.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Querty.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

///borrar
    @Override
    public boolean delete(Object ob) {
        var = (Var) ob;
        String sql = "DELETE FROM Users WHERE ID =?";
        try {
            con = DriverManager.getConnection(config.getConnectionstr());
            ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(var.getId()));
            ps.executeUpdate();
            con.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Querty.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

///buscar    
    @Override
    public boolean search(Object ob) {
        //var = (Var) ob;
        String sql = "SELECT ID, UserName, FullName, Password, Email, Domain, GrantLevel FROM Users WHERE ID = '%" + (int) ob + "%'";

        try {
            con = DriverManager.getConnection(config.getConnectionstr());
            ps = con.prepareStatement(sql);
            ps.setString(1, Integer.toString(var.getId()));
            ps.executeQuery();

            con.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Querty.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
