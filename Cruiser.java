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
public class Cruiser extends Ship{
    int[] cruiserH = {0,1,2,4};
    int[] cruiserV = {5,6,7,9};
    int[][] cruiserHV = {{0,1,2,4},{5,6,7,9}};
    
    public Cruiser(char Direction) {
        super("Cruiser", Direction);
        this.ShipPieces = cruiserHV;
    }
    
}
