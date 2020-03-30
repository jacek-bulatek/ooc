package com.packtpub.libgdx.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.packtpub.libgdx.bludbourne.screens.MainGameScreen;

public class PlayerInputComponent extends InputComponent {

	private final static String TAG = PlayerInputComponent.class.getSimpleName();
	private Vector3 _lastMouseCoordinates;

	public PlayerInputComponent(){
		this._lastMouseCoordinates = new Vector3();
	}

	@Override
	public void receiveMessage(String message) {
		String[] string = message.split(MESSAGE_TOKEN);

		if( string.length == 0 ) return;

		//Specifically for messages with 1 object payload
		if( string.length == 2 ) {
			if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
				_currentDirection = _json.fromJson(Entity.Direction.class, string[1]);
			}
		}
	}

	@Override
	public void dispose(){
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void update(Entity entity, float delta){
		//Keyboard input
		if(keys.get(Keys.Q)) {
			sendStateUpdate(Entity.State.Q, entity);
		}
		else if(keys.get(Keys.W)) {
			sendStateUpdate(Entity.State.Q, entity);
		}
		else if(keys.get(Keys.E)) {
			sendStateUpdate(Entity.State.Q, entity);
		}
		else if(keys.get(Keys.R)) {
			sendStateUpdate(Entity.State.Q, entity);
		}
		else if(keys.get(Keys.UP)) {
            if (keys.get(Keys.LEFT)) {
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
            		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.NW));
            }
            else if (keys.get(Keys.RIGHT)) {
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
            		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.NE));
            }
            else {
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
            		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.N));
            }
        }
        else if(keys.get(Keys.DOWN)){
            if(keys.get(Keys.RIGHT)){
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
            		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.SE));
            }
            else if (keys.get(Keys.LEFT)){
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
    				entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.SW));
            }
            else {
            	if(sendStateUpdate(Entity.State.RUNNING, entity))
            		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.S));
            }
        }
        else if(keys.get(Keys.RIGHT)){
        	if(sendStateUpdate(Entity.State.RUNNING, entity))
        		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.E));
		}
        else if(keys.get(Keys.LEFT)){
        	if(sendStateUpdate(Entity.State.RUNNING, entity))
        		entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.W));
        }
		else{
			sendStateUpdate(Entity.State.IDLE, entity);
			if( _currentDirection == null ){
				entity.sendMessage(MESSAGE.CURRENT_DIRECTION, _json.toJson(Entity.Direction.S));
			}
		}

		//Mouse input
		if( mouseButtons.get(Mouse.SELECT)) {
			//Gdx.app.debug(TAG, "Mouse LEFT click at : (" + _lastMouseCoordinates.x + "," + _lastMouseCoordinates.y + ")" );
			entity.sendMessage(MESSAGE.INIT_SELECT_ENTITY, _json.toJson(_lastMouseCoordinates));
			mouseButtons.put(Mouse.SELECT, false);
		}
		
		// Update timers
		_animationTimer.update(delta);
		for(Timer timer : _cooldownTimers) {
			timer.update(delta);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if( keycode == Input.Keys.LEFT){
			this.leftPressed();
		}
		if( keycode == Input.Keys.RIGHT){
			this.rightPressed();
		}
		if( keycode == Input.Keys.UP){
			this.upPressed();
		}
		if( keycode == Input.Keys.DOWN){
			this.downPressed();
		}
		if( keycode == Input.Keys.Q){
			this.qPressed();
		}
		if( keycode == Input.Keys.W ){
			this.wPressed();
		}
		if( keycode == Input.Keys.E ){
			this.eReleased();
		}
		if( keycode == Input.Keys.R ){
			this.rReleased();
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if( keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
			this.leftReleased();
		}
		if( keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
			this.rightReleased();
		}
		if( keycode == Input.Keys.UP || keycode == Input.Keys.W ){
			this.upReleased();
		}
		if( keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
			this.downReleased();
		}
		if( keycode == Input.Keys.Q){
			this.qReleased();
		}
		if( keycode == Input.Keys.W ){
			this.wReleased();
		}
		if( keycode == Input.Keys.E ){
			this.eReleased();
		}
		if( keycode == Input.Keys.R ){
			this.rReleased();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Gdx.app.debug(TAG, "GameScreen: MOUSE DOWN........: (" + screenX + "," + screenY + ")" );

		if( button == Input.Buttons.LEFT || button == Input.Buttons.RIGHT ){
			this.setClickedMouseCoordinates(screenX, screenY);
		}

		//left is selection, right is context menu
		if( button == Input.Buttons.LEFT){
			this.selectMouseButtonPressed(screenX, screenY);
		}
		if( button == Input.Buttons.RIGHT){
			this.doActionMouseButtonPressed(screenX, screenY);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//left is selection, right is context menu
		if( button == Input.Buttons.LEFT){
			this.selectMouseButtonReleased(screenX, screenY);
		}
		if( button == Input.Buttons.RIGHT){
			this.doActionMouseButtonReleased(screenX, screenY);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	//Key presses
	public void leftPressed(){
		keys.put(Keys.LEFT, true);
	}
	
	public void rightPressed(){
		keys.put(Keys.RIGHT, true);
	}
	
	public void upPressed(){
		keys.put(Keys.UP, true);
	}
	
	public void downPressed(){
		keys.put(Keys.DOWN, true);
	}

	public void qPressed(){
		keys.put(Keys.Q, true);
	}

	public void wPressed() {
		keys.put(Keys.W, true);
	}

	public void ePressed() { keys.put(Keys.E, true); }

	public void RPressed() { keys.put(Keys.R, true); }

	public void setClickedMouseCoordinates(int x,int y){
		_lastMouseCoordinates.set(x, y, 0);
	}
	
	public void selectMouseButtonPressed(int x, int y){
		mouseButtons.put(Mouse.SELECT, true);
	}
	
	public void doActionMouseButtonPressed(int x, int y){
		mouseButtons.put(Mouse.DOACTION, true);
	}
	
	//Releases
	
	public void leftReleased(){
		keys.put(Keys.LEFT, false);
	}
	
	public void rightReleased(){
		keys.put(Keys.RIGHT, false);
	}
	
	public void upReleased(){
		keys.put(Keys.UP, false);
	}
	
	public void downReleased(){
		keys.put(Keys.DOWN, false);
	}
	
	public void qReleased(){
		keys.put(Keys.Q, false);
	}

	public void wReleased(){
		keys.put(Keys.W, false);
	}

	public void eReleased(){
		keys.put(Keys.E, false);
	}

	public void rReleased(){
		keys.put(Keys.R, false);
	}

	public void selectMouseButtonReleased(int x, int y){
		mouseButtons.put(Mouse.SELECT, false);
	}
	
	public void doActionMouseButtonReleased(int x, int y){
		mouseButtons.put(Mouse.DOACTION, false);
	}

	public static void clear(){
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.Q, false);
		keys.put(Keys.W, false);
		keys.put(Keys.E, false);
		keys.put(Keys.R, false);
	}
}
