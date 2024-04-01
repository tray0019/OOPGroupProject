/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccessLayer;
import Model.ItemDTO;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Tom
 */
public interface ItemDAO {
    void addItem(ItemDTO item, HttpSession session);
    void selectItem();

}
