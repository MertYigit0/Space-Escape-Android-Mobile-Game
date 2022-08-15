package com.merty.spaceescape;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import  com.badlogic.gdx.Game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;



public class SpaceEscape extends Game {


	public SpriteBatch batch;


	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new menu(this));




	}

	@Override
	public void render() {
		super.render();
	}
}