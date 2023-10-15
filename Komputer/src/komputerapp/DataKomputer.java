
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komputerapp;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ramadhaan
 */

public class DataKomputer implements AppInterface {
    final Komputer[] komputer;

    public DataKomputer() {
        komputer = new Komputer[1000];
    }

    @Override
    public int pilihanMenu() {
        String p = JOptionPane.showInputDialog(null, ""
            + "<html>"
            + "======Pilihan==============<br>"
            + "1 &rarr; Tambah Data Komputer<br>"
            + "2 &rarr; Cari berdasarkan Brand Komputer<br>"
            + "3 &rarr; Cari berdasarkan Model Komputer<br>"
            + "4 &rarr; Tampilkan Semua Data Komputer<br>"
            + "5 &rarr; Keluar Aplikasi<br>"
            + "===========================<br>"
            + "<b>Ketik Pilihan Anda:</b>"
            + "</html>",
            "Menu Pilihan",
            JOptionPane.QUESTION_MESSAGE);
        int pilihan = Integer.parseInt(p);
        return pilihan;
    }

    @Override
    public void add() {
        Komputer kom = new Komputer();
        String brand = JOptionPane.showInputDialog(null, "Ketik Brand:", ""
            + "Brand", JOptionPane.QUESTION_MESSAGE);

        kom.setBrand(brand);
        String model = JOptionPane.showInputDialog(null, "Ketik Model:", ""
            + "Model", JOptionPane.QUESTION_MESSAGE);

        kom.setModel(model);
        String disk = JOptionPane.showInputDialog(null, "Tipe Disk (SSD/Harddisk):", ""
            + "Tipe Disk", JOptionPane.QUESTION_MESSAGE);

        kom.setDiskType(disk);
        String size = JOptionPane.showInputDialog(null, "Kapasitas Disk (Angka):", ""
            + "Kapasitas Disk (dalam GB)", JOptionPane.QUESTION_MESSAGE);
        int diskSize = Integer.parseInt(size);
        kom.setDiskSize(diskSize);
        String ram = JOptionPane.showInputDialog(null, "Kapasitas RAM (Angka):", ""
            + "Ukuran RAM (dalam GB)", JOptionPane.QUESTION_MESSAGE);
        int ramSize = Integer.parseInt(ram);
        kom.setRam(ramSize);

        for (int i = 0; i < komputer.length; i++) {
            if (komputer[i] == null) {
                komputer[i] = kom;
                break;
            }
        }

        viewData(kom);
    }

    @Override
    public String keyword(String type) {
        String key = JOptionPane.showInputDialog(null,
                "Ketik " + type + " komputer",
                type, JOptionPane.QUESTION_MESSAGE);
        return key;
    }

    @Override
    public Komputer searchByBrand(String brand) {
        Komputer komp = null;
        String brandLowerCase = brand.toLowerCase();
        for (Komputer k : komputer) {
            if (k != null) {
                String kBrand = k.getBrand().toLowerCase();
                if (kBrand.contains(brandLowerCase)) {
                    komp = k;
                    break;
                }
            }
        }
        return komp;
    }

    @Override
    public Komputer searchByModel(String model) {
        Komputer komp = null;
        String modelLowerCase = model.toLowerCase();
        for (Komputer k : komputer) {
            if (k != null) {
                String kModel = k.getModel().toLowerCase();
                if (kModel.contains(modelLowerCase)) {
                    komp = k;
                    break;
                }
            }
        }
        return komp;
    }

    @Override
    public void viewData(Komputer k) {
        if (k == null) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        } else {
            JOptionPane.showMessageDialog(null,
                "Brand\t\t: " + k.getBrand() +
                "\nModel\t\t: " + k.getModel() +
                "\nDisk Type\t: " + k.getDiskType() +
                "\nDisk Size\t: " + k.getDiskSize() +
                "\nRAM Size\t: " + k.getRam(),
                "Data Komputer",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void showAllData(Komputer[] komputer) {
        JFrame frame = new JFrame("Data Komputer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Brand", "Model", "Disk Type", "Disk Size (GB)", "RAM (GB)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (Komputer k : komputer) {
            if (k != null) {
                tableModel.addRow(new Object[]{k.getBrand(), k.getModel(), k.getDiskType(), k.getDiskSize(), k.getRam()});
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}