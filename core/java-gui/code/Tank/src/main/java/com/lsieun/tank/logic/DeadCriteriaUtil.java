package com.lsieun.tank.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by liusen on 10/25/18.
 */
public class DeadCriteriaUtil {
    private static final Map<Missile, Set<MissileDeadCriteria>> missileMap;
    private static final Map<Tank, Set<TankDeadCriteria>> tankMap;
    private static final Map<Block, Set<BlockDeadCriteria>> blockMap;

    static {
        missileMap = new HashMap<Missile, Set<MissileDeadCriteria>>();
        tankMap = new HashMap<Tank, Set<TankDeadCriteria>>();
        blockMap = new HashMap<Block, Set<BlockDeadCriteria>>();
    }

    public void register(Missile missile, MissileDeadCriteria deadCriteria) {
        boolean exists = missileMap.containsKey(missile);
        if (exists) {
            Set<MissileDeadCriteria> set = missileMap.get(missile);
            set.add(deadCriteria);
        }
        else {
            //
            Set<MissileDeadCriteria> set = new HashSet<MissileDeadCriteria>();
            set.add(deadCriteria);
            missileMap.put(missile, set);
        }
    }

    public void register(Tank tank, TankDeadCriteria deadCriteria) {
        boolean exists = tankMap.containsKey(tank);
        if (exists) {
            Set<TankDeadCriteria> set = tankMap.get(tank);
            set.add(deadCriteria);
        }
        else {
            //
            Set<TankDeadCriteria> set = new HashSet<TankDeadCriteria>();
            set.add(deadCriteria);
            tankMap.put(tank, set);
        }
    }

    public void register(Block block, BlockDeadCriteria deadCriteria) {
        boolean exists = tankMap.containsKey(block);
        if (exists) {
            Set<BlockDeadCriteria> set = blockMap.get(block);
            set.add(deadCriteria);
        }
        else {
            //
            Set<BlockDeadCriteria> set = new HashSet<BlockDeadCriteria>();
            set.add(deadCriteria);
            blockMap.put(block, set);
        }
    }

}
