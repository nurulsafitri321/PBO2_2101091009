/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inur.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import inur.dao.AnggotaDao;
import inur.dao.AnggotaDaoImpl;
import inur.dao.BukuDao;
import inur.dao.BukuDaoImpl;
import inur.dao.Koneksi;
import inur.dao.PeminjamanDao;
import inur.dao.PeminjamanDaoImpl;
import inur.model.Anggota;
import inur.model.Buku;
import inur.model.Peminjaman;
import inur.view.FormPeminjaman;

/**
 *
 * @author Dell
 */
public class PeminjamanController {
    FormPeminjaman formPeminjaman;
    Peminjaman peminjaman;
    PeminjamanDao peminjamanDao;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    Connection connection;

    public PeminjamanController(FormPeminjaman formPeminjaman) {
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl();
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            Koneksi k = new Koneksi();
            connection = k.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPeminjaman.getTxtTglPinjam().setText("");
        formPeminjaman.getTxtTglKembali().setText("");
    }
    
    public void isiCombo(){
        try {
            formPeminjaman.getCboKodeanggota().removeAllItems();
            formPeminjaman.getCboKodebuku().removeAllItems();
            List<Anggota> anggotaList = anggotaDao.getAllAnggota(connection);
            List<Buku> bukuList = bukuDao.getAllBuku(connection);
            for (Anggota anggota : anggotaList) {
                formPeminjaman.getCboKodeanggota()
                        .addItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            }
            for (Buku buku : bukuList) { 
                formPeminjaman.getCboKodebuku().addItem(buku.getKodebuku());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getCboKodeanggota()
                    .getSelectedItem().toString().split("-")[0]);
            peminjaman.setKodebuku(formPeminjaman.getCboKodebuku().getSelectedItem().toString());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglKembali().getText());
            peminjamanDao.insert(connection, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void getPeminjaman(){
        try {
            String kodeanggota = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 0)
                    .toString();
            String kodebuku = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 1)
                    .toString();
            String tglpinjam = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 2)
                    .toString();
            peminjaman = peminjamanDao
                    .getPeminjaman(connection, kodeanggota, kodebuku, tglpinjam);
            if(peminjaman!=null){
                Anggota anggota = anggotaDao
                        .getAnggota(connection, peminjaman.getKodeanggota());
                formPeminjaman.getCboKodeanggota()
                        .setSelectedItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
                formPeminjaman.getCboKodebuku().setSelectedItem(peminjaman.getKodebuku());
                formPeminjaman.getTxtTglPinjam().setText(peminjaman.getTglpinjam());
                formPeminjaman.getTxtTglKembali().setText(peminjaman.getTglkembali());
             }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPeminjaman.getTabelPeminjaman().getModel();
            tabel.setRowCount(0);
            List<Peminjaman> list = peminjamanDao.getAllPeminjaman(connection);
            for (Peminjaman peminjaman1 : list) { 
                Object[] row = {
                    peminjaman1.getKodeanggota(),
                    peminjaman1.getKodebuku(),
                    peminjaman1.getTglpinjam(),
                    peminjaman1.getTglkembali()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
