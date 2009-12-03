
/*

Copyright (c) 2009, Greg Hendrickson 
All rights reserved. 

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met: 

 * Redistributions of source code must retain the above copyright notice, 
   this list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright 
   notice, this list of conditions and the following disclaimer in the 
   documentation and/or other materials provided with the distribution. 
 * Neither the name of MFD nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE. 

 */
 import java.util.*;


public class Board
{
	private Map < Integer ,Tile> tiles;
	private int x,y;
	private boolean hardashell;
	
	public Board()
	{
		tiles = new HashMap<Integer, Tile>();
		x=4;
		y=5;
		setHardashell(false);
		creatNew();
	}
	public Board(int nx, int ny)
	{
		x = nx;
		y = ny;
		setHardashell(false);
		tiles = new HashMap<Integer, Tile>();
		
		creatNew();
	}
	public Board(int nx, int ny, boolean hard)
	{
		x = nx;
		y = ny;

		//System.out.println("making them "+hard);
		setHardashell(hard);

		tiles = new HashMap<Integer, Tile>();
		creatNew();
		
	}
	
	private void creatNew() {
		//System.out.println("Generating a "+((Integer)x).toString());
		float theta = 1.0f/(x*y/2);
		float curentHue = (float)Math.random();
		for(int i=0;i<(x*y/2);i++)
		{
			curentHue+=theta;
			//System.out.println("making them "+hardashell);
			addTile( new Tile(curentHue,hardashell));
		}
	}

	public Map < Integer ,Tile> getTiles()
	{
		return tiles;
	}
	
	public void setTiles(Map < Integer ,Tile> newTiles)
	{
		tiles = newTiles;
	}
	
	public void addTile(Tile newTile)// add two of these tiles to a random spot
	{
		int spot = (int)Math.floor(Math.random()*(x*y));
		while( tiles.containsKey(spot))
		{
			spot = (int)Math.floor(Math.random()*(x*y));
		};
		//we now have a open spot.
		
		
		tiles.put(spot, newTile);
		while( tiles.containsKey(spot))
		{
			spot = (int)Math.floor(Math.random()*(x*y));
		};
		tiles.put(spot, newTile);//do it twice so we have a pair
	}
	public Tile getTile(Integer gridSpot)
	{
		return tiles.get(gridSpot);
	}
	public void setHardashell(boolean hard) {
		this.hardashell = hard;
	}
	public boolean isHardashell() {
		return hardashell;
	}
	
	
	
	
	
	
	
	
}
