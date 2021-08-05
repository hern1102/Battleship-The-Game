/*


Notes:


 */
package battleship;


public class Frigate extends Ship{

    private int[] frigateH = {0,4};
    private int[] frigateV = {5,9}; 
    private int[][] frigateHV = {{0,4},{5,9}};
    
    Frigate(char Direction){
        super("Frigate", Direction);
        this.ShipPieces = frigateHV;
    }
    
}
