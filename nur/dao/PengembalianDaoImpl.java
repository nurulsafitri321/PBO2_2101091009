/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nur.dao;

import java.sql.Connection;
import java.util.List;
import nur.model.Pengembalian;

/**
 *
 * @author DELL
 */
public class PengembalianDaoImpl implements PengembalianDao{

    @Override
    public void insert(Connection con, Pengembalian pengembalian) throws Exception {
        
       String sql = "insert into pengembalian value(?,?,?,?,?,?)";
    }

    @Override
    public void update(Connection con, Pengembalian pengembalian) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Connection con, Pengembalian pengembalian) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pengembalian getPengembalian(Connection con, String kodeanggota, String kodebuku, String tglpinjam) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pengembalian> getAllPeminjaman(Connection con) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
