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
public class Ship
{
    private String shipName;
    protected int[][] ShipPieces;
    char Direction;
    
    Ship(String name, char Direction){
        this.Direction = Direction;
        this.shipName = name;
    }
    
    
    public String getName()
	{
		return this.shipName;
	}
	public int[] getShipPieces()
	{
            int[] placeHolder;
            if(Direction == 'H'){
                placeHolder = ShipPieces[0];
            }else{
                placeHolder = ShipPieces[1];
            }
            
		return placeHolder;
	}
	public int getDirection()
	{
		return this.Direction;
	}
	public int length()
	{
		return ShipPieces[0].length;
	}
    
}