/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author antho
 */
public class battle_ship extends Ship{
    int[] battleShipH = {0,1,2,3,4};
    int[] battleShipV = {5,6,7,8,9};
    int[][] battleShipHV = {{0,1,2,3,4},{5,6,7,8,9}};
    
    public battle_ship(char Direction) {
        super("Battleship", Direction);
        this.ShipPieces = battleShipHV;
    } 
    
}
