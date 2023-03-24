/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.dao;


import nurul.model.Anggota;
import java.sql.Connection;

/**
 *
 * @author DELL
 */
public interface AnggotaDao {
    void insert(Connection con,Anggota anggota) throws Exception;
}
