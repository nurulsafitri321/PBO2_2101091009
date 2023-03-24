/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.dao;

import nurul.model.Anggota;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author DELL
 */
public class AnggotaDaoImpl implements AnggotaDao {

    @Override
    public void insert(Connection con, Anggota anggota) throws Exception {
        String sql = "Inset into anggota values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, anggota.getKodeAnggota());
        ps.setString(2, anggota.getNamaAnggota());
        ps.setString(2, anggota.getAlamat());
        ps.setString(2, anggota.getJenisKelamin());
        ps.executeUpdate();
    }

    
    
}
