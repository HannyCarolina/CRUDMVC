/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Querty;
import Model.Var;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author hcorrea
 */
public class Controller implements ActionListener, MouseListener, KeyListener {

    Screen view;
    Querty mlib;
    Var mvar;
    String[] colum = {"Id", "Usuario", "Nombre", "Clave", "Correo", "Dominio", "Nivel"};
    ArrayList<Object[]> data = new ArrayList<>();
    DefaultTableModel model;
    TableRowSorter trs;

    public Controller() {
        view = new Screen();
        mlib = new Querty();
        mvar = new Var();
        model = new DefaultTableModel(colum, 0);
        trs = new TableRowSorter<>(model);

    }

    public void start() {
        view.setTitle("Usuarios");
        view.getBtnChange().addActionListener(this);
        view.getBtnClear().addActionListener(this);
        view.getBtnDelete().addActionListener(this);
        view.getBtnSave().addActionListener(this);
        view.getBtnSearch().addActionListener(this);
        view.getTblUsers().addMouseListener(this);
        view.getTxtId().addKeyListener(this);
        loadTable();
        view.setVisible(true);
    }

///
    @Override
    public void actionPerformed(ActionEvent e) {

///cambiar
        if (e.getSource().equals(view.getBtnChange())) {

            if (view.getTxtName().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla.");
            } else if (!view.getTxtName().getText().isEmpty()) {
                mvar.setDomain(view.getTxtDomain().getText());
                mvar.setEmail(view.getTxtEmail().getText());
                mvar.setFullName(view.getTxtFullName().getText());
                mvar.setName(view.getTxtName().getText());
                mvar.setNivel(view.getTxtLevel().getText());
                mvar.setPassword(view.getTxtPassword().getText());
                mvar.setId(Integer.parseInt(view.getTxtId().getText().trim()));
                mlib.update(mvar);

                JOptionPane.showMessageDialog(null, "Registro modificado.");
                clearTable();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el registro.");
            }
        }
///limpiar        
        if (e.getSource().equals(view.getBtnClear())) {
            view.getTxtDomain().setText("");
            view.getTxtEmail().setText("");
            view.getTxtFullName().setText("");
            view.getTxtId().setText("");
            view.getTxtLevel().setText("");
            view.getTxtName().setText("");
            view.getTxtPassword().setText("");
            clearTable();
            loadTable();
        }
///eliminar        
        if (e.getSource().equals(view.getBtnDelete())) {
            if (view.getTxtId().getText().isEmpty() || view.getTxtName().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla.");
            } else if (!view.getTxtId().getText().isEmpty()) {
                mvar.setId(Integer.parseInt(view.getTxtId().getText().trim()));
                mlib.delete(mvar);
                JOptionPane.showMessageDialog(null, "Registro eliminado.");
                clearTable();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el registro.");
            }
        }
///agregar        
        if (e.getSource().equals(view.getBtnSave())) {
            if (view.getTxtId().getText().isEmpty() || view.getTxtName().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios.");
            } else if (!view.getTxtId().getText().isEmpty()) {
                mvar.setDomain(view.getTxtDomain().getText());
                mvar.setEmail(view.getTxtEmail().getText());
                mvar.setFullName(view.getTxtFullName().getText());
                mvar.setId(Integer.parseInt(view.getTxtId().getText().trim()));
                mvar.setName(view.getTxtName().getText());
                mvar.setNivel(view.getTxtLevel().getText());
                mvar.setPassword(view.getTxtPassword().getText());
                mlib.create(mvar);
                JOptionPane.showMessageDialog(null, "Registro guardado.");
                clearTable();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el registro.");
            }
        }
///buscar        
        if (e.getSource().equals(view.getBtnSearch())) {
            if (view.getTxtId().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Indique el ID o Usuario que quiere buscar.");
            } else if (!view.getTxtId().getText().isEmpty()) {
                
                mvar.setId(Integer.parseInt(view.getTxtId().getText().trim()));
                mlib.search(mvar);
                
                view.getTxtDomain().setText(mvar.getDomain());
                view.getTxtEmail().setText(mvar.getEmail());
                view.getTxtFullName().setText(mvar.getFullName());
                view.getTxtLevel().setText(mvar.getNivel());
                view.getTxtName().setText(mvar.getName());
                view.getTxtPassword().setText(mvar.getPassword());
                System.out.println("y ahora:: " + mlib.search(mvar));

            }
        }
    }
///

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        view.getTxtId().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 0).toString());
        view.getTxtName().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 1).toString());
        view.getTxtFullName().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 2).toString());
        view.getTxtPassword().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 3).toString());
        view.getTxtEmail().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 4).toString());
        view.getTxtDomain().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 5).toString());
        view.getTxtLevel().setText(view.getTblUsers().getValueAt(view.getTblUsers().getSelectedRow(), 6).toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void loadTable() {
        model.setColumnCount(7);
        data = mlib.read();
        data.forEach(obj -> {
            model.addRow(obj);
        });
        view.getTblUsers().setModel(model);
    }

    public void clearTable() {

        for (int i = 0; i < view.getTblUsers().getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }

    }
///

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        trs.setRowFilter(RowFilter.regexFilter(view.getTxtId().getText(), 0));
        view.getTblUsers().setRowSorter(trs);
    }
}
