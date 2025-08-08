package org.bernardo.o6sherosrpg.levels.APIs;

public class XPLevelAPI {

    public int getXPToNextLevel(int level) {
        int baseXP = 500;

        return baseXP * level;
    }

    public int getTotalXPForLevel(int level) {
        int total = 0;

        for (int i = 1; i < level; i++) {
            total += getXPToNextLevel(i);
        }

        return total;
    }

    public double getProgressInLevel(double currentXP, int currentLevel) {
        int xpThisLevel = getXPToNextLevel(currentLevel);

        return Math.min(currentXP / xpThisLevel, 1.0);
    }

}
