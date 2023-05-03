/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.dao;



import nurul.model.Anggota;
import java.sql.Connection;
import java.util.List;


/**
 *
 * @author DELL
 */
public interface AnggotaDao {
    
    public void insert(Connection con,Anggota anggota) throws Exception;
    public void update(Connection con,Anggota anggota) throws Exception;
    public void delete(Connection con,Anggota anggota) throws Exception;
    public Anggota getAnggota(Connection con,String kode) throws Exception;
    public List<Anggota> getAllAnggota(Connection con) throws Exception;

    
}
