/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndam_observer;

import OBSERVER.Ara;
import OBSERVER.Ekle;
import OBSERVER.Guncelle;
import OBSERVER.IslemBirimi;
import OBSERVER.Sil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author fince
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane pane_main;
    @FXML
    private Accordion pane_acordion;
    @FXML
    private TitledPane ucus_ekle_pane;
    @FXML
    private TextField ucus_ekle_txtUcusNo;
    @FXML
    private TextField ucus_ekle_txtNerden;
    @FXML
    private TextField ucus_ekle_txtNereye;
    @FXML
    private TextField ucus_ekle_txtFiyat;
    @FXML
    private Button ucus_ekle_BtnEkle;
    @FXML
    private Button ucus_ara_BtnAra;
    @FXML
    private TextField ucus_ara_txtFiyat;
    @FXML
    private TextField ucus_ara_txtNereye;
    @FXML
    private TextField ucus_ara_txtNerden;
    @FXML
    private TextField ucus_ara_txtUcusNo;
    @FXML
    private RadioButton radio_buyuk;
    @FXML
    private ToggleGroup grup1;
    @FXML
    private RadioButton radio_esit;
    @FXML
    private RadioButton radio_kucuk;
    @FXML
    private Button ucus_ara_BtnSfiirla;
    @FXML
    private TitledPane ucus_guncelle_pane;
    @FXML
    private TextField ucus_guncelle_txtUcusNo;
    @FXML
    private TextField ucus_guncelle_txtNerden;
    @FXML
    private TextField ucus_guncelle_txtNereye;
    @FXML
    private TextField ucus_guncelle_txtFiyat;
    @FXML
    private Button ucus_guncelle_BtnGuncelle;
    @FXML
    private TableView<Ucus> table_flies;
    @FXML
    private ContextMenu context_menu;
    @FXML
    private TableColumn<Ucus, String> col_ucusNo;
    @FXML
    private TableColumn<Ucus, String> col_Nerden;
    @FXML
    private TableColumn<Ucus, String> col_Nereye;
    @FXML
    private TableColumn<Ucus, String> col_Fiyat;
    
    private ObservableList<Ucus> ucus_listesi;
    private ObservableList<Ucus> arama_ucus_listesi;
    private IslemBirimi islemBirimi;
    private NumberFormatController numberFormatController;
    private Ekle ekle;
    private Ara ara;
    private Guncelle guncelle;
    private Sil sil;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ucus_listesi=FXCollections.observableArrayList(
                 new Ucus(
                 "123","Isparta","İstanbul","150"),
                 new Ucus("112","Ankara","İstanbul","175"),
                 new Ucus("324","Trabzon","İstanbul","250"),
                 new Ucus("354","İstanbul","Hatay","350")
                 
         );
        islemBirimi=new IslemBirimi(ucus_listesi);
        numberFormatController=new NumberFormatController();
        ekle=new Ekle();
        ara=new Ara();
        sil=new Sil();
        guncelle=new Guncelle();
        tabloyuDoldur();
    }    
     private void tabloyuDoldur() {
         
      
         if(ucus_listesi.size() != 0)
         {
            col_ucusNo.setCellValueFactory(new PropertyValueFactory<Ucus,String>("ucusNo"));
            col_Nerden.setCellValueFactory(new PropertyValueFactory<Ucus,String>("nerden"));
            col_Nereye.setCellValueFactory(new PropertyValueFactory<Ucus,String>("nereye"));
            col_Fiyat.setCellValueFactory(new PropertyValueFactory<Ucus,String>("fiyat"));
            
            table_flies.setItems(ucus_listesi);
         }
       
        
    }

    @FXML
    private void ekle_tiklandi(ActionEvent event) {
        String islem="Ekleme";
         if(!ucus_ekle_txtUcusNo.getText().isEmpty() &&!ucus_ekle_txtNerden.getText().isEmpty() 
                &&!ucus_ekle_txtNereye.getText().isEmpty() && !ucus_ekle_txtFiyat.getText().isEmpty())
        {
            if(numberFormatController.stringHasOnlydigit(ucus_ekle_txtFiyat.getText().toString())
                    && numberFormatController.stringHasOnlydigit(ucus_ekle_txtUcusNo.getText().toString()))
                
            {
                 Ucus ucus=new Ucus(
                    ucus_ekle_txtUcusNo.getText().toString(),
                    ucus_ekle_txtNerden.getText().toString(),
                    ucus_ekle_txtNereye.getText().toString(),
                    ucus_ekle_txtFiyat.getText().toString()
            );
            islemBirimi.islemEkle(ekle, ucus, islem);
            JOptionPane.showMessageDialog(null, "Ekleme işlemi başarılı.","Bilgilendirme Mesajı"
                        ,JOptionPane.INFORMATION_MESSAGE);
                  ucus_listesi=islemBirimi.ucus_listesi;
                  tabloyuDoldur();
           
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sayısal değerleri doğru girdiğinizden emin olun.\n"
                        + "(Uçuş No/Fiyat)","Hata Mesajı"
                        ,JOptionPane.ERROR_MESSAGE);
                ucus_ekle_temizle();
            }
                
                
           
          
        }
    }

    @FXML
    private void ucus_ekle_paneTiklandi(MouseEvent event) {
         if(ucus_ekle_pane.isExpanded())
        {
           
            tabloyuDoldur();
        }
    }

    @FXML
    private void ara_tiklandi(ActionEvent event) {
        
        int fiyat_kistasi=-1;
       Ucus arama_kriterleri=new Ucus(
               ucus_ara_txtUcusNo.getText().toString(),
               ucus_ara_txtNerden.getText().toString(),
               ucus_ara_txtNereye.getText().toString(),
               ucus_ara_txtFiyat.getText().toString()
       );
       if(radio_buyuk.isSelected())
           fiyat_kistasi=2;
       else if(radio_esit.isSelected())
           fiyat_kistasi=1;
       else
           fiyat_kistasi=0;
       
       arama_kriterleri.setFiyat_kistasi(fiyat_kistasi);
       islemBirimi.islemEkle(ara, arama_kriterleri, "Arama");
       arama_ucus_listesi=islemBirimi.arama_ucus_listesi;
       //tabloyu doldur
        if(arama_ucus_listesi.size() != 0)
         {
            col_ucusNo.setCellValueFactory(new PropertyValueFactory<Ucus,String>("ucusNo"));
            col_Nerden.setCellValueFactory(new PropertyValueFactory<Ucus,String>("nerden"));
            col_Nereye.setCellValueFactory(new PropertyValueFactory<Ucus,String>("nereye"));
            col_Fiyat.setCellValueFactory(new PropertyValueFactory<Ucus,String>("fiyat"));
            
            table_flies.setItems(arama_ucus_listesi);
         }
    }

    @FXML
    private void sifirla_tiklandi(ActionEvent event) {
        tabloyuDoldur();
    }

    @FXML
    private void guncelle_tiklandi(ActionEvent event) {
        if(!ucus_guncelle_txtUcusNo.getText().isEmpty() &&!ucus_guncelle_txtNerden.getText().isEmpty() 
                &&!ucus_guncelle_txtNereye.getText().isEmpty() && !ucus_guncelle_txtFiyat.getText().isEmpty()&&
                 table_flies.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(numberFormatController.stringHasOnlydigit(ucus_guncelle_txtFiyat.getText().toString())
                    && numberFormatController.stringHasOnlydigit(ucus_guncelle_txtUcusNo.getText().toString()))
            {
                Ucus ucus=new Ucus(
                        ucus_guncelle_txtUcusNo.getText().toString(),
                        ucus_guncelle_txtNerden.getText().toString(),
                        ucus_guncelle_txtNereye.getText().toString(),
                        ucus_guncelle_txtFiyat.getText().toString()
                );
                ucus.setIndis(table_flies.getSelectionModel().getSelectedIndex());
                islemBirimi.islemEkle(guncelle, ucus, "Güncelleme");
                
                ucus_listesi=islemBirimi.ucus_listesi;
                tabloyuDoldur();
                JOptionPane.showMessageDialog(null, "Güncelleme işlemi başarılı.","Bilgilendirme Mesajı"
                        ,JOptionPane.INFORMATION_MESSAGE);
               
                
             }
            else
            {
                 JOptionPane.showMessageDialog(null, "Sayısal değerleri doğru girdiğinizden emin olun.\n"
                        + "(Uçuş No/Fiyat)","Hata Mesajı"
                        ,JOptionPane.ERROR_MESSAGE);
                 ucus_guncelle_temizle();
            }
                
    }
        
    }

    @FXML
    private void ucus_guncelle_paneTiklandi(MouseEvent event) {
         if(ucus_guncelle_pane.isExpanded())
        {
          
            tabloyuDoldur();
        }
    }

    @FXML
    private void sil_basildi(ActionEvent event) {
        
        Ucus u=new Ucus();
        u.setIndis(table_flies.getSelectionModel().getSelectedIndex());
        islemBirimi.islemEkle(sil, u, "Silme");
        ucus_listesi=islemBirimi.ucus_listesi;
        tabloyuDoldur();
    }

    @FXML
    private void context_menu_clicked(ContextMenuEvent event) {
        context_menu.show(pane_main,event.getScreenX(),event.getScreenY());
    }

    @FXML
    private void table_tiklandi(MouseEvent event) {
         if(table_flies.getSelectionModel().getSelectedIndex() >= 0 && ucus_guncelle_pane.isExpanded())
        {
            Ucus ucus=table_flies.getSelectionModel().getSelectedItem();
            ucus_guncelle_txtUcusNo.setText(ucus.getUcusNo());
            ucus_guncelle_txtNerden.setText(ucus.getNerden());
            ucus_guncelle_txtNereye.setText(ucus.getNereye());
            ucus_guncelle_txtFiyat.setText(ucus.getFiyat());
           
        }
    }
     public void ucus_ara_temizle()
    {
         ucus_ara_txtFiyat.clear();
         ucus_ara_txtUcusNo.clear();
         ucus_ara_txtNerden.clear();
         ucus_ara_txtNereye.clear();
         radio_buyuk.setSelected(false);
         radio_kucuk.setSelected(false);
         radio_esit.setSelected(false);
    }
    public void ucus_ekle_temizle()
    {
         ucus_ekle_txtFiyat.clear();
         ucus_ekle_txtUcusNo.clear();
         ucus_ekle_txtNerden.clear();
         ucus_ekle_txtNereye.clear();
    }
    public void ucus_guncelle_temizle()
    {
         ucus_guncelle_txtFiyat.clear();
         ucus_guncelle_txtUcusNo.clear();
         ucus_guncelle_txtNerden.clear();
         ucus_guncelle_txtNereye.clear();
    }
    
}
