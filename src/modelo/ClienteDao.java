/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author William Rincon julio
 */
public class ClienteDao implements CRUD{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from cliente";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNombres(rs.getString(2));
                c.setTelefono(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEstado(rs.getString(5));
                
                lista.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido listar: " + e);
        }
        
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into cliente(idCliente, nombres, telefono, direccion, estado) values(?,?,?,?,?)";
        
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
        String sql = "update cliente set nombres = ?, telefono = ?, direccion = ?, estado = ? where idCliente = ?";
        
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
        String sql = "delete from cliente where idCliente = ?";
        
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
