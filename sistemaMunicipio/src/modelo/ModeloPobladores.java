/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author josej
 */
public class ModeloPobladores extends AbstractTableModel{
     private ArrayList<Object[]> datos;
    String nombreCols[] = {"ID","NOMBRE","APELLIDO PATERNO" ,"APELLIDO MATERNO","FECHA NACIMIENTO"};

    
    public ModeloPobladores(ArrayList d){
    datos = d;
    }
    
    public String getColumName(int i){
     return nombreCols[i];
    }
    
    
    
    @Override
    public int getRowCount() {
        return datos.size();
    }
    
   
    

    @Override
    public int getColumnCount() {
        return nombreCols.length;
    }
    
  

    @Override
    public Object getValueAt(int r, int c) {
        return datos.get(r)[c];
    }
    
   
  
}
