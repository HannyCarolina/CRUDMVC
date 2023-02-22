 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hcorrea
 */
public interface SQLInterface {
    //cruds
    public boolean create(Object ob);
    
    public ArrayList<Object[]> read();
    
    public boolean update(Object ob); 
    
    public boolean delete(Object ob);
    
    public boolean search(Object ob);
    
}
