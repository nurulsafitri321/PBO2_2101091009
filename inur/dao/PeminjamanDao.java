/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inur.dao;

import java.sql.Connection;
import java.util.List;
import inur.model.Peminjaman;

/**
 *
 * @author Dell
 */
public interface PeminjamanDao {
    public void insert(Connection con, Peminjaman peminjaman) throws Exception;
    public void update(Connection con, Peminjaman peminjaman) throws Exception;
    public void delete(Connection con, Peminjaman peminjaman) throws Exception;
    public Peminjaman getPeminjaman(Connection con, 
            String kodeanggota, String kodebuku, String tglpinjam) throws Exception;
    public List<Peminjaman> getAllPeminjaman(Connection con) throws Exception;
}
