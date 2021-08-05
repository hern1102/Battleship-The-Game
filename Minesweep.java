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
public class Minesweep extends Ship{
    
    int[] mineSweepH = {0,1,4};
    int[] mineSweepV = {5,6,9};
    int[][] mineSweepHV = {{0,1,4},{5,6,9}};
    
    public Minesweep(char Direction) {
        super("Minesweep", Direction);
        this.ShipPieces = mineSweepHV;
    }
    
}
