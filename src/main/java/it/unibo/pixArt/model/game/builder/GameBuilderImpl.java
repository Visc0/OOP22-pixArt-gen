package it.unibo.pixArt.model.game.builder;

import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.game.GameImpl;
import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.timer.GameTimer;

public final class GameBuilderImpl implements GameBuilder {

    private GameType gameType;
    private GameTimer gameTimer;

    @Override
    public GameBuilder gameType(final GameType gameType) {
        this.gameType = gameType;
        return this;
    }

    @Override
    public GameBuilder gameTimer(final GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        return this;
    }

    @Override
    public Game build() {
        return new GameImpl(this.gameType, this.gameTimer);
    }
}
