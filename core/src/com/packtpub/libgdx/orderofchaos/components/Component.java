package com.packtpub.libgdx.orderofchaos.components;


public interface Component {

    public static final String MESSAGE_TOKEN = ":::::";

    public static enum MESSAGE{
        CURRENT_POSITION,
        INIT_START_POSITION,
        CURRENT_DIRECTION,
        CURRENT_STATE,
        COLLISION_WITH_MAP,
        COLLISION_WITH_ENTITY,
        COLLISION_WITH_HITBOX,
        LOAD_ANIMATIONS,
        INIT_DIRECTION,
        INIT_STATE,
        INIT_SELECT_ENTITY,
        INIT_BOUNDING_BOX,
        ENTITY_SELECTED,
        ENTITY_DESELECTED
    }

    void dispose();
    void receiveMessage(String message);
}
