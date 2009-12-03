
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
import java.awt.Color;


public class Tile 
{
	private float whatHue;
	private boolean hard;
	private long startTime;
	
	public Tile()
	{
		hard=false;
		setWhatHue(0.0f);
		startTime=System.currentTimeMillis();
	}

	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean found) {
		this.hard = found;
	}

	public Tile (float newHue)
	{
		setWhatHue(newHue);
		startTime=System.currentTimeMillis();
	}
	public Tile (float newHue, boolean newhard)
	{

		startTime=System.currentTimeMillis();
		setWhatHue(newHue);
		setHard(newhard);
	}

	public void setWhatHue(float whatColor) {
		this.whatHue= whatColor;
	}

	public float getWhatHue() {
		return whatHue;
	}
	public Color getWhatColor()
	{
		float ofset =0.0f;
		if(hard)
		{
			long timeDif = System.currentTimeMillis()-startTime;
			ofset = (((float)timeDif) / 20000.0f)%1.0f;
			
			//System.out.println(ofset);
			//ofset = 
		}
		
		return new Color( Color.HSBtoRGB(whatHue+ofset,1.0f,1.0f));
		
	}
	
	
	
}

