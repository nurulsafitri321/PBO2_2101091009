/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nurul.controller;

import com.sun.jdi.connect.spi.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private AnggotaDao anggotaDao;
    private Connection con;
    private Koneksi koneksi;
    
    public AnggotaController(formAnggota formAnggota){
        try {
            this.formAnggota = new formAnggota();
            anggotaDao = new AnggotaDaoImpl();
            con = (Connection) new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnggotaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formAnggota.getTxtkodeAnggota().setText("");
        formAnggota.getTxtnamaAnggota().setText("");
        
    }
    
    public void isiCboJenisKelamin(){
        formAnggota.getCboJenisKelamin().removeAllItems();
        formAnggota.getCboJenisKelamin().addItem("L");
        formAnggota.getCboJenisKelamin().addItem("P");
    }
}
