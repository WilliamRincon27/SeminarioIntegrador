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
public class UsuarioDao{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public Usuario validarUsuario(String email, String contraseña) {
        Usuario user = new Usuario();
        String sql = "select * from usuarios where email=? and contraseña=?";
        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setContraseña(rs.getString(5));
                user.setTelefono(rs.getString(6));
            }
        } catch (Exception e) {
        }
        
        return user;
    }

    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into usuarios(id, nombre, apellido, email, contraseña, telefono) values(?,?,?,?,?,?)";
        
        try {
            con= cn.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido añadir: " + e);
        }
        return r;
    }

    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update usuarios set nombre = ?, apellido = ?, email = ?, contraseña = ?, telefono = ? where id = ?";
        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido actualizar: " + e);
        }
        return r;
    }

    public void eliminar(String id) {
        String sql = "delete from usuarios where id = ?";
        
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
