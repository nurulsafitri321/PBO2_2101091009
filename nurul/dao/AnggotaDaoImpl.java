/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.dao;

import nurul.model.Anggota;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class AnggotaDaoImpl implements AnggotaDao {

    @Override
    public void insert(Connection con,Anggota anggota) throws Exception {
        String sql = "Insert into anggota values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.setString(2, anggota.getNamaAnggota());
        ps.setString(3, anggota.getAlamat());
        ps.setString(4, anggota.getJenisKelamin());
        ps.executeUpdate();
    }

    @Override
    public void update(Connection con, Anggota anggota) throws Exception {
        String sql =
                "Update anggota set namaanggota=?, alamat=?,"
                + "jeniskelamin=? where kodeanggota=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.setString(2, anggota.getNamaAnggota());
        ps.setString(3, anggota.getAlamat());
        ps.setString(4, anggota.getJenisKelamin());
        ps.executeUpdate();
        
    }

    @Override
    public void delete(Connection con, Anggota anggota) throws Exception {
        String sql = "delete from anggota"
                + "where kodeanggota = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.executeUpdate();
        
    }

    @Override
    public Anggota getAnggota(Connection con,String kode) throws Exception {
        String sql = "select * from Anggota"
                + "where kodeAnggota = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        Anggota anggota = null;
        if(rs.next()) {
            anggota = new Anggota();
            anggota.setKodeAnggota(rs.getString(1));
            anggota.setNamaAnggota(rs.getString(2));
            anggota.setAlamat(rs.getString(3));
            anggota.setJenisKelamin(rs.getString(4));     
        }
        return anggota;    
    }

    @Override
    public List<Anggota> getAllAnggota(Connection con) throws Exception {
        String sql 
                = "select * from Anggota";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Anggota>  list = new ArrayList<>();
        Anggota anggota = null;
        while(rs.next()) {
            anggota = new Anggota();
            anggota.setKodeAnggota(rs.getString(1));
            anggota.setNamaAnggota(rs.getString(2));
            anggota.setAlamat(rs.getString(3));
            anggota.setJenisKelamin(rs.getString(4));
            list.add(anggota);
        }
        return list;    
    }

    
}
    


