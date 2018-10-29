package com.lsieun.tank.action;

/**
 * 可以被损害的
 */
public interface Damageable {
    int getHealth();
    void setHealth(int health);

    void damage(int amount);
    int getMaxHealth();
}
