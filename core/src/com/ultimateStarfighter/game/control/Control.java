package com.ultimateStarfighter.game.control;

public class Control {
	private static Control control;
	private float percentY;
	private float percentX;

	private Control() {

	}

	public static Control getControl() {
		if (control != null)
			return control;
		else
			return control = new Control();
	}

	public float getPercentY() {
		return percentY;
	}

	public void setPercentY(float percentY) {
		this.percentY = percentY;
	}

	public float getPercentX() {
		return percentX;
	}

	public void setPercentX(float percentX) {
		this.percentX = percentX;
	}

}
