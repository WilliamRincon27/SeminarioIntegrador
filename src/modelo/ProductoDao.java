/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author William Rincon julio
 */
public class ProductoDao implements CRUD{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select * from producto";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Producto p = new Producto();
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCantidad(rs.getInt(4));
                p.setEstado(rs.getString(5));
                
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido listar: " + e);
        }
        
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into producto(idProducto, nombre, precio, cantidad, estado) values(?,?,?,?,?)";
        
        try {
            con= cn.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido añadir: " + e);
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update productos set nombre = ?, precio = ?, cantidad = ?, estado = ? where idProducto = ?";
        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar: " + e);
        }
        return r;
    }

    @Override
    public void eliminar(String id) {
        String sql = "delete from producto where idProducto = ?";
        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar: " + e);
        }
    }
    
    
}
