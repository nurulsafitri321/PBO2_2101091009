/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.controller;

import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nurul.dao.AnggotaDao;
import nurul.dao.AnggotaDaoImpl;
import nurul.dao.Koneksi;
import nurul.model.Anggota;
import nurul.view.formAnggota;

/**
 *
 * @author DELL
 */
public class AnggotaController {

    private formAnggota formAnggota;
    private Anggota anggota;
    private AnggotaDao dao;
    private Connection con;
    private Koneksi k;

    public AnggotaController(formAnggota formAnggota) {
        try {
            this.formAnggota = formAnggota;
            dao = new AnggotaDaoImpl();
            k = new Koneksi();
            con = k.getKoneksi();
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
    }

    public void clearForm() {
        formAnggota.getTxtkodeAnggota().setText("");
        formAnggota.getTxtnamaAnggota().setText("");
        formAnggota.getTxtAlamat().setText("");
    }
    
     public void isiCboJenisKelamin() {
        formAnggota.getCboJenisKelamin().removeAllItems();
        formAnggota.getCboJenisKelamin().addItem("L");
        formAnggota.getCboJenisKelamin().addItem("P");
    }

    public void insert() {
        try {
            anggota = new Anggota();
            anggota.setKodeAnggota(
                    formAnggota.getTxtkodeAnggota().getText());
            anggota.setNamaAnggota(
                    formAnggota.getTxtnamaAnggota().getText());
            anggota.setAlamat(
                    formAnggota.getTxtAlamat().getText());
            anggota.setJenisKelamin(
                    formAnggota.getCboJenisKelamin()
                            .getSelectedItem().toString());
            dao.insert(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Entri Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void update() {
        try {
            anggota = new Anggota();
            anggota.setKodeAnggota(formAnggota.getTxtkodeAnggota().getText());
            anggota.setNamaAnggota(formAnggota.getTxtnamaAnggota().getText());
            anggota.setAlamat(formAnggota.getTxtAlamat().getText());
            anggota.setJenisKelamin(
                    formAnggota.getCboJenisKelamin().getSelectedItem().toString());
            dao.update(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Update Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        try {
            dao.delete(con, anggota);
            JOptionPane.showMessageDialog(formAnggota, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void cari(){
        try {
            String kode = formAnggota.getTxtkodeAnggota().getText();
            anggota = dao.getAnggota(con,kode);
            if (anggota != null){
                anggota.setNamaAnggota(formAnggota.getTxtnamaAnggota().getText());
                anggota.setAlamat(formAnggota.getTxtAlamat().getText());
                anggota.setJenisKelamin(formAnggota.getCboJenisKelamin().getSelectedItem().toString());
            }
         } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel)formAnggota.getTblAnggota().getModel();
            tabel.setRowCount(0);
            List<Anggota> list = dao.getAllAnggota(con);
            for (Anggota anggota1 : list) {
                Object[] row = {
                    anggota1.getKodeAnggota(),
                    anggota1.getNamaAnggota(),
                    anggota1.getJenisKelamin(),
                    anggota1.getAlamat()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
