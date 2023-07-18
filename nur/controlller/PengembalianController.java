/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nur.controlller;

//import com.sun.jdi.connect.spi.Connection;
//import nur.dao.AnggotaDao;
//import nur.dao.BukuDao;
//import nur.dao.PeminjamanDao;
//import nur.dao.PengembalianDao;
//import nur.model.Pengembalian;
//import nur.view.FormPengembalian;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import nur.dao.AnggotaDao;
import nur.dao.AnggotaDaoImpl;
import nur.dao.BukuDao;
import nur.dao.BukuDaoImpl;
import nur.dao.Koneksi;
import nur.dao.PeminjamanDao;
import nur.dao.PeminjamanDaoImpl;
import nur.dao.PengembalianDao;
import nur.dao.PengembalianDaoImpl;
import nur.model.Anggota;
import nur.model.Peminjaman;
import nur.model.Pengembalian;
import nur.view.FormPengembalian;

/**
 *
 * @author DELL
 */

public class PengembalianController {
    FormPengembalian formPengembalian;
    AnggotaDao anggotaDao;
    BukuDao BukuDao;
    PeminjamanDao peminjamanDao;
    PengembalianDao pengembalianDao;
    Pengembalian pengembalian;
    Connection connection;

    public PengembalianController(FormPengembalian formPengembalian) {
        try {
            this.formPengembalian = formPengembalian;
            anggotaDao = new AnggotaDaoImpl();
            BukuDao = new BukuDaoImpl();
            peminjamanDao = new PeminjamanDaoImpl();
            pengembalianDao = new PengembalianDaoImpl();
            Koneksi k = new Koneksi();
            connection = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void clearForm(){
        formPengembalian.getTxtTglPinjam().setText("");
        formPengembalian.getTxtTglKembali().setText("");
        formPengembalian.getTxtTglDikembalikan().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
        formPengembalian.getTxtKodeAnggota().setText("");
        formPengembalian.getTxtKodebuku().setText("");
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel)
                    formPengembalian.getTabelPengembalian().getModel();
            tabelModel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(connection);
            for (Pengembalian p : list) {
                Anggota anggota = anggotaDao.getAnggota(connection, 
                        p.getKodeanggota());
                Peminjaman pinjam = peminjamanDao
                        .getPeminjaman(connection, p.getKodeanggota(), 
                                p.getKodebuku(), p.getTglpinjam());
                Object[] row = {
                    p.getKodeanggota(),
                    anggota.getNamaanggota(),
                    p.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabelModel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPengembalian(){
        try {
            String kodeAnggota = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 2).toString();
            String tglpinjam = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 3).toString();
            pengembalian = new Pengembalian();
            Peminjaman peminjaman = peminjamanDao
                    .getPeminjaman(connection, kodeAnggota, kodebuku, tglpinjam);
            int terlambat = pengembalianDao
                    .SelisihTanggal(connection, pengembalian.getTgldikembalikan(),
                            peminjaman.getTglkembali());
            pengembalian.setTerlambat(terlambat);
            double denda = pengembalian.getDenda();
            formPengembalian.getTxtKodeAnggota().setText(kodeAnggota);
            formPengembalian.getTxtKodebuku().setText(kodebuku);
            formPengembalian.getTxtTglPinjam().setText(tglpinjam);
            formPengembalian.getTxtTglKembali().setText(peminjaman.getTglkembali()); 
            formPengembalian.getTxtTglDikembalikan().setText(pengembalian.getTgldikembalikan());
            formPengembalian.getTxtTerlambat().setText(terlambat+""); 
            formPengembalian.getTxtDenda().setText(denda+"");
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

//public class PengembalianController {
//    
//    FormPengembalian formengembalian;
//    AnggotaDao anggotadao;
//    BukuDao bukuDao;
//    PeminjamanDao peminjamanDao;
//    PengembalianDao pengembalianDao;
//    Pengembalian pengembalian;
//    Connection connection;
//    
//    public PengembalianController(FormPengembalian formPengembalian){
//    
//    }
//}
