/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccessLayer;
import Model.dto.*;
/**
 *
 * @author Tom
 */
public interface ItemDAO {
    void addItem(ItemDTO item);
    void selectItem();
    void subscribe();
}
